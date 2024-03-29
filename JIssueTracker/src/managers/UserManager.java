/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import config.IssueTrackerConnectionProvider;
import entities.User;
import exceptions.UserCreationException;
import exceptions.UserNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for retrieve/store/update Users entities
 * 
 * @author Mychal
 */
public class UserManager extends IssueTrackerManager {

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS USERS;";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS USERS(ID INTEGER PRIMARY KEY AUTOINCREMENT"
            + ", LOGIN VARCHAR(50)"
            + ", PASSWORD VARCHAR(50));";
    private static final String INSERT_QUERY = "INSERT INTO USERS(LOGIN, PASSWORD) VALUES(?, ?);";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM USERS;";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM USERS WHERE ID=?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM USERS WHERE ID=?;";
    private static final String SELECT_BY_LOGIN_QUERY = "SELECT * FROM USERS WHERE LOGIN=?;";

    public static void dropTableIfExists(IssueTrackerConnectionProvider provider) throws SQLException {
        Statement statement = provider.createConnection().createStatement();
        statement.executeUpdate(DROP_TABLE_QUERY);
    }

    public static void createTableIfNotExists(IssueTrackerConnectionProvider provider) throws SQLException {
        Statement statement = provider.createConnection().createStatement();
        statement.executeUpdate(CREATE_TABLE_QUERY);
    }

    public UserManager(IssueTrackerConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    /**
     * Creates user and stores it in the database. Always correct User object is returned.
     * In case of failure UserCreationException is thrown.
     * @param userLogin
     * @param userPassword
     * @return created user
     * @throws UserCreationException, SQLException
     */
    public synchronized User createUser(String userLogin, String userPassword) throws UserCreationException, SQLException {
        userLogin = userLogin.trim();
        try {
            checkLoginAndPasswordNotEmpty(userLogin, userPassword);
            createConnection();
            if (!userWithGivenLoginExists(userLogin)) {
                PreparedStatement createStatement = connection.prepareStatement(INSERT_QUERY);
                createStatement.setString(1, userLogin);
                createStatement.setString(2, userPassword);
                createStatement.addBatch();
                createStatement.executeBatch();
                ResultSet generatedKeys = createStatement.getGeneratedKeys();
                try {
                    if (generatedKeys.next()) {
                        User result = new User(userLogin, userPassword);
                        result.setId(generatedKeys.getInt(1));
                        return result;
                    } else {
                        throw new UserCreationException("Could not create user.");
                    }
                } finally {
                    generatedKeys.close();
                }

            } else {
                throw new UserCreationException("User with login: " + userLogin + " already exists");
            }
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Retrieves all users.
     * @return list of all users
     * @throws SQLException 
     */
    public synchronized List<User> getAllUsers() throws SQLException {
        try {
            createConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(SELECT_ALL_QUERY);
            List<User> result = new ArrayList<User>();
            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                result.add(user);
            }
            resultSet.close();
            return result;
        } finally {
            closeConnection();
        }
    }

    /**
     * Returns user with specified id. If no such user exists UserNotFoundException is thrown.
     * @param id
     * @return
     * @throws UserNotFoundException
     * @throws SQLException 
     */
    public synchronized User getUserById(int id) throws UserNotFoundException, SQLException {
        try {
            createConnection();
            PreparedStatement selectStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            try {
                if (resultSet.next()) {
                    User user = createUserFromResultSet(resultSet);
                    return user;
                } else {
                    throw new UserNotFoundException();
                }
            } finally {
                resultSet.close();
            }
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Removes specified user from database.
     * @param user
     * @throws SQLException 
     */
    public synchronized void removeUser(User user) throws SQLException {
        try {
            createConnection();
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_USER_QUERY);
            deleteStatement.setInt(1, user.getId());
            deleteStatement.addBatch();
            deleteStatement.executeBatch();
        } finally {
            closeConnection();
        }
    }
    
    public boolean authenticate(String userName, String password) throws SQLException
    {
        createConnection();
        try
        {
            User user = getUserByLogin(userName);
            if(user == null)
                return false;
            return user.getPassword().equals(password);
        } finally
        {
            closeConnection();
        }
    }

    private User getUserByLogin(String login) throws SQLException {
        PreparedStatement selectStatement = connection.prepareStatement(SELECT_BY_LOGIN_QUERY);
        selectStatement.setString(1, login);
        ResultSet resultSet = selectStatement.executeQuery();
        User result = null;
        try {
            if (resultSet.next()) {
                result = createUserFromResultSet(resultSet);
            }
        } finally {
            resultSet.close();
        }
        return result;
    }

    private void checkLoginAndPasswordNotEmpty(String login, String password) throws UserCreationException {
        if (login.isEmpty() || password.isEmpty()) {
            throw new UserCreationException("Login and password cannot be empty");
        }
    }

    private boolean userWithGivenLoginExists(String login) throws SQLException {
        return (this.getUserByLogin(login) != null);
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User result = new User();
        result.setId(resultSet.getInt(1));
        result.setLogin(resultSet.getString(2));
        result.setPassword(resultSet.getString(3));
        return result;
    }
}

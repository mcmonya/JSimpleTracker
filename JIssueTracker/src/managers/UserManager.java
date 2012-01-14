/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import config.IssueTrackerConnectionProvider;
import entities.User;
import exceptions.UserCreationException;
import java.sql.Connection;
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
public class UserManager {
    private IssueTrackerConnectionProvider connectionProvider;

    public UserManager(IssueTrackerConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
    
    public User createUser(String userLogin, String userPassword) throws UserCreationException
    {
        userLogin = userLogin.trim();
        Connection connection = null;
        try
        {
            connection = connectionProvider.createConnection();
            if(!userWithGivenLoginExists(userLogin))
            {
                PreparedStatement createStatement = connection.prepareStatement("INSERT INTO USERS VALUES(?, ?)");
                createStatement.setString(1, userLogin);
                createStatement.setString(2, userPassword);
                createStatement.addBatch();
                createStatement.execute();
                ResultSet generatedKeys = createStatement.getGeneratedKeys();
                if(generatedKeys.next())
                {
                    User result = new User(userLogin, userPassword);
                    result.setId(generatedKeys.getInt(1));
                    return result;
                } else
                {
                    throw new UserCreationException("Could not create user.");
                }
            } else
            {
                throw new UserCreationException("User with login: " + userLogin + " already exists");
            }
        }catch(SQLException e)
        {
            throw new UserCreationException(e.getSQLState());
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                }catch(SQLException e)
                {}
            }
        }
    }
    
    public List<User> getAllUsers() throws SQLException
    {
        Connection connection = null;
        try
        {
            connection = connectionProvider.createConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM USERS");
            List<User> result = new ArrayList<User>();
            while(resultSet.next())
            {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setNick(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                result.add(user);
            }
            return result;
        }finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                }catch(SQLException e){}
            }
        }
    }
    
    public User getUserById(int id)
    {
        throw new UnsupportedOperationException();
    }
    
    public void removeUser(User user)
    {
        throw new UnsupportedOperationException();
    }
    
    private User getUserByLogin(String login)
    {
        throw new UnsupportedOperationException();
    }
    
    private void insertUserToDatabase(User user) throws SQLException
    {
        throw new UnsupportedOperationException();
    }
    
    private void checkLoginAndPasswordNotEmpty(String login, String password) throws UserCreationException
    {
        if(login.isEmpty() || password.isEmpty())
        {
            throw new UserCreationException("Login and password cannot be empty");
        }
    }
    
    private boolean userWithGivenLoginExists(String login)
    {
        return (this.getUserByLogin(login) != null);
    }
}

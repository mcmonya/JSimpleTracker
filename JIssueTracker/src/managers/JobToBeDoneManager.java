/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import config.IssueTrackerConnectionProvider;
import entities.JobToBeDone;
import entities.User;
import exceptions.UserNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CRUD of Jobs to be done
 *
 * @author Mychal
 */
public class JobToBeDoneManager extends IssueTrackerManager
{

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS JOBS_TO_BE_DONE(ID INTEGER PRIMARY KEY AUTOINCREMENT"
            + ", REQUESTOR_ID INTEGER"
            + ", PROGRAMMER_ID INTEGER"
            + ", TODO TEXT"
            + ", DATE_ADDED INTEGER"
            + ", PRIORITY INTEGER"
            + ", DONE INTEGER);";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS JOBS_TO_BE_DONE;";
    private static final String INSERT_QUERY = "INSERT INTO JOBS_TO_BE_DONE(REQUESTOR_ID, PROGRAMMER_ID"
            + ", TODO, DATE_ADDED, PRIORITY, DONE) VALUES(?, ?, ?, ?, ?, ?);";
    private static final String DELETE_QUERY = "DELETE FROM JOBS_TO_BE_DONE WHERE ID=?;";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM JOBS_TO_BE_DONE;";
    private static final String SELECT_JOBS_BY_DONE = "SELECT * FROM JOBS_TO_BE_DONE WHERE DONE=?;";
    private static final String SELECT_JOBS_ASSIGNED_TO_PROGRAMMER_QUERY = "SELECT * FROM JOBS_TO_BE_DONE WHERE PROGRAMMER_ID=?";
    private static final String SELECT_JOBS_AFTER_ID_QUERY = "SELECT * FROM JOBS_TO_BE_DONE WHERE ID>?;";
    private static final String UPDATE_JOB_QUERY = "UPDATE JOBS_TO_BE_DONE SET "
            + " REQUESTOR_ID=?"
            + ", PROGRAMMER_ID=?"
            + ", TODO=?"
            + ", PRIORITY=?"
            + ", DONE=? "
            + "WHERE ID=?;";
    
    
    public static void dropTableIfExists(IssueTrackerConnectionProvider provider) throws SQLException
    {
        Statement statement = provider.createConnection().createStatement();
        statement.executeUpdate(DROP_TABLE_QUERY);
    }

    public static void createTableIfNotExists(IssueTrackerConnectionProvider provider) throws SQLException
    {
        Statement statement = provider.createConnection().createStatement();
        statement.executeUpdate(CREATE_TABLE_QUERY);
    }

    public JobToBeDoneManager(IssueTrackerConnectionProvider connectionProvider)
    {
        super(connectionProvider);
    }

    public JobToBeDone createNewJob(User requestor, User programmer, String todo, int priority)
            throws SQLException
    {
        createConnection();
        try
        {
            JobToBeDone result = new JobToBeDone();
            result.setDateAdded(new Date());
            result.setPriority(priority);
            result.setProgrammer(programmer);
            result.setRequestor(requestor);
            result.setTodo(todo);
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setInt(1, requestor.getId());
            statement.setInt(2, programmer.getId());
            statement.setString(3, todo);
            statement.setLong(4, result.getDateAdded().getTime());
            statement.setInt(5, priority);
            statement.setBoolean(6, false);
            statement.addBatch();
            statement.executeBatch();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
            {
                result.setId(generatedKeys.getInt(1));
            }
            return result;
        } finally
        {
            closeConnection();
        }
    }

    public List<JobToBeDone> getAllJobs() throws SQLException
    {
        createConnection();
        List<JobToBeDone> result = new ArrayList<JobToBeDone>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next())
            {
                try
                {
                    JobToBeDone job = createJobFromResultSet(resultSet);
                    result.add(job);
                } catch (UserNotFoundException e)
                {
                }
            }
            return result;
        } finally
        {
            closeConnection();
        }
    }

    public List<JobToBeDone> getAllJobsAssignedToProgrammer(User programmer) throws SQLException
    {
        List<JobToBeDone> result = new ArrayList<JobToBeDone>();
        createConnection();
        try
        {
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_JOBS_ASSIGNED_TO_PROGRAMMER_QUERY);
            selectStatement.setInt(1, programmer.getId());
            ResultSet resultSet = selectStatement.executeQuery();
            while(resultSet.next())
            {
                try
                {
                    JobToBeDone job = createJobFromResultSet(resultSet);
                    result.add(job);
                } catch(UserNotFoundException e)
                {}
            }
            return result;
        } finally
        {
            closeConnection();
        }
    }

    public List<JobToBeDone> getAllJobsAfterId(int id) throws SQLException
    {
        List<JobToBeDone> result = new ArrayList<JobToBeDone>();
        createConnection();
        try
        {
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_JOBS_AFTER_ID_QUERY);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next())
            {
                try
                {
                    JobToBeDone job = createJobFromResultSet(resultSet);
                    result.add(job);
                } catch (UserNotFoundException e)
                {
                }
            }
        } finally
        {
            closeConnection();
        }
        return result;
    }
    

    public List<JobToBeDone> getAllUndoneJobs() throws SQLException
    {
        createConnection();
        try
        {
            return getAllJobsByDone(false);
        } finally
        {
            closeConnection();
        }
    }

    public List<JobToBeDone> getAllDoneJobs() throws SQLException
    {
        createConnection();
        try
        {
            return getAllJobsByDone(true);
        } finally
        {
            closeConnection();
        }
    }
    
    public void updateJob(JobToBeDone job) throws SQLException
    {
        createConnection();
        try
        {
            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_JOB_QUERY);
            updateStatement.setInt(1, job.getRequestor().getId());
            updateStatement.setInt(2, job.getProgrammer().getId());
            updateStatement.setString(3, job.getTodo());
            updateStatement.setInt(4, job.getPriority());
            updateStatement.setBoolean(5, job.isDone());
            updateStatement.setInt(6, job.getId());
            updateStatement.executeUpdate();
        }finally
        {
            closeConnection();
        }
    }
    
    /**
     * Removes specified job from database
     * @param job the job to be removed
     * @throws SQLException 
     */
    public void deleteJob(JobToBeDone job) throws SQLException
    {
        createConnection();
        try
        {
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY);
            deleteStatement.setInt(1, job.getId());
            deleteStatement.executeUpdate();
        } finally
        {
            closeConnection();
        }
    }
    
    private List<JobToBeDone> getAllJobsByDone(boolean done) throws SQLException
    {
        List<JobToBeDone> result = new ArrayList<JobToBeDone>();
        PreparedStatement statement = connection.prepareStatement(SELECT_JOBS_BY_DONE);
        statement.setBoolean(1, done);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            try
            {
                JobToBeDone job = createJobFromResultSet(resultSet);
                result.add(job);
            } catch(UserNotFoundException e)
            {
                
            }
        }
        return result;
    }

    private JobToBeDone createJobFromResultSet(ResultSet resultSet) throws SQLException, UserNotFoundException
    {
        UserManager userManager = new UserManager(connectionProvider);
        JobToBeDone result = new JobToBeDone();
        result.setId(resultSet.getInt(1));
        result.setRequestor(userManager.getUserById(resultSet.getInt(2)));
        result.setProgrammer(userManager.getUserById(resultSet.getInt(3)));
        result.setTodo(resultSet.getString(4));
        result.setDateAdded(new Date(resultSet.getLong(5)));
        result.setPriority(resultSet.getInt(6));
        result.setDone(resultSet.getBoolean(7));
        return result;
    }
}

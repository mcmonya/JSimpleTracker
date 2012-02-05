/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import config.IssueTrackerConnectionProvider;
import entities.JobToBeDone;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * CRUD of Jobs to be done
 * 
 * @author Mychal
 */
public class JobToBeDoneManager extends IssueTrackerManager{
      
    private static final String CREATE_TABLE_QUERY = "";
    private static final String DROP_TABLE_QUERY = "";
    private static final String INSERT_QUERY = "INSERT INTO JOBS_TO_BE_DONE()";
    private static final String DELETE_QURTY = "";
    private static final String SELECT_ALL_QUERY = "";
    
    
    public JobToBeDoneManager(IssueTrackerConnectionProvider connectionProvider)
    {
        super(connectionProvider);
    }
    
    public JobToBeDone createNewJob(User requestor, User programmer, String todo, int priority)
            throws SQLException
    {
        JobToBeDone result = new JobToBeDone();
        result.setDateAdded(new Date());
        result.setPriority(priority);
        result.setProgrammerId(programmer.getId());
        result.setRequestorId(requestor.getId());
        result.setTodo(todo);
        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        throw new UnsupportedOperationException();
    }
    
    public List<JobToBeDone> getAllJobs() throws SQLException
    {
        createConnection();
        try
        {
            throw new UnsupportedOperationException();
        }finally
        {
            closeConnection();
        }
    }
    
    public List<JobToBeDone> getAllJobsAssignedToProgrammer(User programmer)
    {
        throw new UnsupportedOperationException();
    }
    
    public List<JobToBeDone> getAllJobsByRequestor(User requestor)
    {
        throw new UnsupportedOperationException();
    }
    
    public List<JobToBeDone> getAllUndoneJobs()
    {
        throw new UnsupportedOperationException();
    }
    
    public List<JobToBeDone> getAllDoneJobs()
    {
        throw new UnsupportedOperationException();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissuetracker;

import communication.IssuesModel;
import config.IssueTrackerConnectionProvider;
import config.ProductionConnectionProvider;
import entities.JobToBeDone;
import entities.User;
import exceptions.ModelException;
import exceptions.UserCreationException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import managers.JobToBeDoneManager;
import managers.UserManager;

/**
 *
 * @author mychal
 */
public class Issues implements IssuesModel
{
    private static IssuesModel instance = new Issues();
   
    private UserManager userManager;
    private JobToBeDoneManager jobsManager;
    private Date lastOperationDate = new Date();
    
    public static IssuesModel getInstance()
    {
        return instance;
    }
    
    public Issues()
    {
        IssueTrackerConnectionProvider connectionProvider = new ProductionConnectionProvider();
        userManager = new UserManager(connectionProvider);
        jobsManager = new JobToBeDoneManager(connectionProvider);
    }

    @Override
    public JobToBeDone createJobToBeDone(User programmer, User requestor, String todo, int priority) throws ModelException
    {
        try
        {
            JobToBeDone result = jobsManager.createNewJob(requestor, programmer, todo, priority);
            stateChanged();
            return result;
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }

    @Override
    public User createUser(String userName, String password) throws ModelException
    {
        try
        {
            User result = userManager.createUser(userName, password);
            stateChanged();
            return result;
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        } catch(UserCreationException e)
        {
            throw new ModelException(e.toString());
        }
    }

    @Override
    public void deleteJob(JobToBeDone jobToDelete) throws ModelException
    {
        try
        {
            jobsManager.deleteJob(jobToDelete);
            stateChanged();
        }catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }

    @Override
    public List<JobToBeDone> getAllJobs() throws ModelException
    {
        try
        {
            List<JobToBeDone> result = jobsManager.getAllDoneJobs();
            stateChanged();
            return result;
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }

    @Override
    public List<JobToBeDone> getAllJobsAfterId(int id) throws ModelException
    {
        try
        {
            List<JobToBeDone> result = jobsManager.getAllJobsAfterId(id);
            stateChanged();
            return result;
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }

    @Override
    public List<User> getAllUsers() throws ModelException
    {
        try
        {
            List<User> result = userManager.getAllUsers();
            stateChanged();
            return result;
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }

    @Override
    public boolean isUpdateNeeded(Date lastUpdate)
    {
        return lastOperationDate.after(lastUpdate);
    }

    @Override
    public void updateJob(JobToBeDone jobToUpdate) throws ModelException
    {
        try
        {
            jobsManager.updateJob(jobToUpdate);
            stateChanged();
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }
    
    @Override
    public boolean authenticate(String userName, String password) throws ModelException
    {
        try
        {
            return userManager.authenticate(userName, password);
        } catch(SQLException e)
        {
            throw new ModelException(e.toString());
        }
    }
    
    private void stateChanged()
    {
        lastOperationDate = null;
        lastOperationDate = new Date();
    }
    
}

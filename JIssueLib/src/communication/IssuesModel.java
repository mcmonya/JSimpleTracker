/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import entities.JobToBeDone;
import entities.User;
import exceptions.ModelException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mychal
 */
public interface IssuesModel extends Serializable
{
    /**
     * 
     * @return all existing jobs
     * @throws ModelException in case of fail
     */
    public List<JobToBeDone> getAllJobs() throws ModelException;
    
    /**
     * 
     * @param id
     * @return all existing jobs creater after job id
     * @throws ModelException in case of fail
     */
    public List<JobToBeDone> getAllJobsAfterId(int id) throws ModelException;
    
    /**
     * Creates and persists new job into database
     * @param programmer
     * @param requestor
     * @param todo
     * @param priority
     * @return just created job
     * @throws ModelException in case of fail
     */
    public JobToBeDone createJobToBeDone(User programmer, User requestor, String todo, int priority) throws ModelException;
    
    /**
     * Creates and presists user into database
     * @param userName
     * @param password
     * @return Object representing created User
     * @throws ModelException in case of fail
     */
    public User createUser(String userName, String password) throws ModelException;
    
    /**
     * Updates specified job
     * @param jobToUpdate
     * @throws ModelException in case of fail
     */
    public void updateJob(JobToBeDone jobToUpdate) throws ModelException;
    
    /**
     * Deletes specified job
     * @param jobToDelete
     * @throws ModelException 
     */
    public void deleteJob(JobToBeDone jobToDelete) throws ModelException;
    
    /**
     * Clients may ask server if there was any action taken after last client refresh. To achieve this client must
     * remember in its state the time of last full update of data from server.
     * @param lastUpdate
     * @return true if client should fully update content(i.e call getAllJobs and getAllUsers)
     */
    public boolean isUpdateNeeded(Date lastUpdate);
    
    /**
     * Use to obtain list of all registered users
     * @return list of registered users
     * @throws ModelException in case of fail
     */
    public List<User> getAllUsers() throws ModelException;
    
    public boolean authenticate(String userName, String password) throws ModelException;
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import entities.JobToBeDone;
import java.util.List;

/**
 *
 * @author mychal
 */
public class GetAllIssuesResponse extends AbstractResponse
{
    private List<JobToBeDone> jobs;
    
    public void setJobs(List<JobToBeDone> jobs)
    {
        this.jobs = jobs;
    }
    
    public List<JobToBeDone> getAllJobs()
    {
        return jobs;
    }
}

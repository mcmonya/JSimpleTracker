/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient;

import entities.JobToBeDone;
import exceptions.ResponseException;
import java.util.List;

/**
 *
 * @author mychal
 */
public interface EntityProvider
{
    public List<JobToBeDone> getAllJobs() throws ResponseException; 
    public void removeJob(JobToBeDone job) throws ResponseException;
    public void updateJob(JobToBeDone job) throws ResponseException;
}

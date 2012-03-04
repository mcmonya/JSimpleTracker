/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient;

import entities.JobToBeDone;
import java.util.List;

/**
 *
 * @author mychal
 */
public interface EntityProvider
{
    public List<JobToBeDone> getAllJobs();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient;

import entities.JobToBeDone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mychal
 */
public class RemoteEntityProvider implements EntityProvider
{   
    @Override
    public List<JobToBeDone> getAllJobs()
    {
        List<JobToBeDone> res = new ArrayList<JobToBeDone>();
        res.add(new JobToBeDone());
        return res;
    }
}

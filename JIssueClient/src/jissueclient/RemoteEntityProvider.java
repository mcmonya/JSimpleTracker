/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient;

import communication.GetAllIssuesRequest;
import communication.GetAllIssuesResponse;
import communication.Request;
import communication.Response;
import entities.JobToBeDone;
import entities.User;
import exceptions.ResponseException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mychal
 */
public class RemoteEntityProvider implements EntityProvider
{       
    @Override
    public List<JobToBeDone> getAllJobs() throws ResponseException
    {
        Request request = new GetAllIssuesRequest(Configuration.getInstance().getUserName(), Configuration.getInstance().getPassword());
        GetAllIssuesResponse response = (GetAllIssuesResponse)sendRequest(request);
        return response.getAllJobs();
        /*List<JobToBeDone> res = new ArrayList<JobToBeDone>();
        JobToBeDone job = new JobToBeDone();
        job.setTodo("Zrób to maleńki");
        job.setProgrammer(new User("Michał", ""));
        job.setRequestor(new User("Boss", ""));
        res.add(job);
        job = new JobToBeDone();
        job.setTodo("Dawaj pinioncha");
        job.setRequestor(new User("Pracownik", ""));
        job.setProgrammer(new User("Szefo", ""));
        res.add(job);
        return res;*/
    }
    
    @Override
    public void removeJob(JobToBeDone job)
    {
        
    }
    
    @Override
    public void updateJob(JobToBeDone job)
    {
        
    }
    
    private Response sendRequest(Request request) throws ResponseException
    {
        try
        {
            Socket socket = new Socket(Configuration.getInstance().getHost(), Configuration.getInstance().getPort());
            ObjectOutput out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(request);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Object res = in.readObject();
            if (res == null || !(res instanceof Response))
            {
                throw new ResponseException("Serwer wysłał nieprawidłową lub pustą odpowiedź");
            }
            Response response = (Response) res;
            if (response.errorOccured())
            {
                throw new ResponseException(response.getErrorMessage());
            }
            return response;
        } catch (ClassNotFoundException e)
        {
            throw new ResponseException(e.toString());
        } catch(UnknownHostException e)
        {
            throw new ResponseException("Nieprawidłowy host");
        } catch(IOException e)
        {
            throw new ResponseException(e.toString());
        }
    }
}

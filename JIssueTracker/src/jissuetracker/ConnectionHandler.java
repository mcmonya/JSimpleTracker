/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissuetracker;

import communication.AuthenticationFailedResponse;
import communication.Request;
import communication.Response;
import exceptions.ModelException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author mychal
 */
public class ConnectionHandler implements Runnable
{
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Response response;
    private Request request;
    
    public ConnectionHandler(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            getRequest();
            produceResponse();
            sendResponse();
        } catch(IOException e)
        {
        } finally
        {
            try
            {
                socket.close();
            } catch(IOException e)
            {}
        }
    }
    
    private void getRequest() throws IOException
    {
        try
        {
            this.request = (Request)inputStream.readObject();
        } catch(ClassNotFoundException e)
        {
            this.request = null;
        }
    }
    
    private void produceResponse()
    {
        if(request == null)
            return;
        if(!authenticate())
            response = new AuthenticationFailedResponse();
        else
            response = request.produceResponse(Issues.getInstance());
    }
    
    private void sendResponse()
    {
        if(response == null)
            return;
        try
        {
            outputStream.writeObject(response);
        } catch(IOException e)
        {
        }
    }
    
    private boolean authenticate()
    {
        try
        {
            return Issues.getInstance().authenticate(request.getUserName(), request.getPassword());
        } catch(ModelException e)
        {
            return false;
        }
    }
}

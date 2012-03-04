/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissuetracker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mychal
 */
public class ConnectionDispatcher
{
    private Configuration configruation;
    private ServerSocket server;
    private ExecutorService executorService;
    
    public ConnectionDispatcher(Configuration configuration)
    {
        this.configruation = configuration;
        executorService = Executors.newFixedThreadPool(configuration.getConnectionPoolSize());
    }
    
    public void serve() throws IOException
    {
        server = new ServerSocket(configruation.getPort());
        while(true)
        {
            Socket s = server.accept();
            dispatch(s);
        }
    }
    
    private void dispatch(Socket socket)
    {
        Runnable handler = new ConnectionHandler(socket);
        executorService.submit(handler);
    }
}

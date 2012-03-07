/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissuetracker;

import java.io.*;

/**
 *
 * @author mychal
 */
public class Configuration implements Serializable
{
    private int port = 6666;
    private int connectionPoolSize = 5;    
    private static final Configuration instance = new Configuration();
    private static final String CONFIG_FILE_NAME = "config.dat";
    
    public static Configuration getInstance()
    {
        return instance;
    }
    
    private Configuration()
    {
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CONFIG_FILE_NAME));
            Object readObject = null;
            try
            {
                readObject = inputStream.readObject();
            } finally
            {
                inputStream.close();
            }
            if(readObject instanceof Configuration)
            {
                Configuration config = (Configuration)readObject;
                this.port = config.port;
                this.connectionPoolSize = config.connectionPoolSize;
            }
        } catch(FileNotFoundException e)
        {
        } catch(IOException e)
        {
        } catch(ClassNotFoundException e)
        {}
    }
    
    public int getPort()
    {
        return port;
    }
    
    public int getConnectionPoolSize()
    {
        return connectionPoolSize;
    }

    @Override
    protected void finalize() throws Throwable
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE_NAME));
        try
        {
            out.writeObject(instance);
        } finally
        {
            out.close();
        }
        super.finalize();
    }
    
}

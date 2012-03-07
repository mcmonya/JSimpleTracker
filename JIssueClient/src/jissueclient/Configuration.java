/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient;

/**
 *
 * @author mychal
 */
public class Configuration
{
    private static Configuration instance = new Configuration();
    private Configuration()
    {
        
    }
    
    public static Configuration getInstance()
    {
        return instance;
    }
    
    public String getHost()
    {
        return "localhost";
    }
    
    public int getPort()
    {
        return 6666;
    }
    
    public String getUserName()
    {
        return "user";
    }
    
    public String getPassword()
    {
        return "pass";
    }
    
    @Override
    protected void finalize() throws Throwable
    {
        
        super.finalize();
    }
}

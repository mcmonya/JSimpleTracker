/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissuetracker;

import java.io.IOException;


/**
 *
 * @author Mychal
 */
public class JIssueTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Configuration configuration = Configuration.getInstance();
        ConnectionDispatcher dispatcher = new ConnectionDispatcher(configuration);
        try
        {
            dispatcher.serve();
        } catch(IOException e)
        {
            
        }
    }
}

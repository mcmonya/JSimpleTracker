/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient;

import java.util.TimerTask;

/**
 *
 * @author mychal
 */
public class RefreshTimer extends TimerTask
{
    private TimerEventListener eventListener;
    
    public RefreshTimer(TimerEventListener eventListener)
    {
        this.eventListener = eventListener;
    }
    
    @Override
    public void run()
    {
        eventListener.refreshModel();
    }
    
}

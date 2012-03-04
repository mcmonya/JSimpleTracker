/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author mychal
 */
public class TrayIconContainer
{
    TrayIcon icon;
    TrayIconEventListener eventListener;
    public TrayIconContainer(TrayIconEventListener eventListener)
    {
        register();
        this.eventListener = eventListener;
    }
    
    public void errorOccured(String errorMessage)
    {
        if(icon == null)
            return;
        icon.displayMessage("ERROR", errorMessage, TrayIcon.MessageType.ERROR);
    }
    
    private Image provideIcon()
    {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("S", 5, 20);
        return image;
    }
    
    private PopupMenu providePopupMenu()
    {
        PopupMenu result = new PopupMenu();
        MenuItem miExitApplication = new MenuItem("Zamknij");
        miExitApplication.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        result.add(miExitApplication);
        return result;
    }
    
    private void register()
    {
        if (SystemTray.isSupported())
        {
            icon = new TrayIcon(provideIcon(), "JIssueClient");
            icon.addActionListener(new MainFrameHider());
            icon.setPopupMenu(providePopupMenu());
            try
            {
                SystemTray.getSystemTray().add(icon);
            } catch(AWTException e)
            {}
        }        
    }
    
    private class MainFrameHider implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            eventListener.changeVisible();
        }
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient.gui;

import java.util.Timer;
import javax.swing.JFrame;
import jissueclient.ErrorHandler;
import jissueclient.RefreshTimer;
import jissueclient.RemoteEntityProvider;
import jissueclient.TimerEventListener;

/**
 *
 * @author mychal
 */
public class MainFrame extends javax.swing.JFrame implements TrayIconEventListener, TimerEventListener, ErrorHandler
{
    private TrayIconContainer trayIcon = new TrayIconContainer(this);
    private Timer timer;
    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        initComponents();
        tableContent.setErrorHandler(this);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        gotoOnlineMode();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        onlineButton = new javax.swing.JToggleButton();
        contentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableContent = new jissueclient.gui.IssueTable();
        mainMenu = new javax.swing.JMenuBar();
        menuSettings = new javax.swing.JMenu();
        miSettings = new javax.swing.JMenuItem();
        miClose = new javax.swing.JMenuItem();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        onlineButton.setText("Połączony");
        onlineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlineButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addComponent(onlineButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(onlineButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableContent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableContentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableContent);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuSettings.setText("Program");

        miSettings.setText("Ustawienia");
        menuSettings.add(miSettings);

        miClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        miClose.setText("Zamknij");
        miClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCloseActionPerformed(evt);
            }
        });
        menuSettings.add(miClose);

        mainMenu.add(menuSettings);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        int column = tableContent.convertColumnIndexToView(4);
        tableContent.getColumnModel().getColumn(column).setPreferredWidth(getSize().width / 2);
    }//GEN-LAST:event_formComponentResized

    private void miCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miCloseActionPerformed
    {//GEN-HEADEREND:event_miCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miCloseActionPerformed

    private void tableContentMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tableContentMouseClicked
    {//GEN-HEADEREND:event_tableContentMouseClicked
        if(evt.getClickCount() == 2)
        {
            
        }
    }//GEN-LAST:event_tableContentMouseClicked

    private void onlineButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onlineButtonActionPerformed
    {//GEN-HEADEREND:event_onlineButtonActionPerformed
        if(onlineButton.isSelected())
            gotoOnlineMode();
        else
            gotoOfflineMode();
    }//GEN-LAST:event_onlineButtonActionPerformed

    @Override
    public void changeVisible()
    {
        setVisible(!isVisible());
    }
    
    @Override
    public void refreshModel()
    {
        tableContent.refreshData();
    }

    @Override
    public void handleError(String errorMessage)
    {
        trayIcon.errorOccured(errorMessage);
        gotoOfflineMode();
    }
    
    private void startTimer()
    {
        timer = new Timer();
        timer.schedule(new RefreshTimer(this), 1000, 1000*60);
    }
    
    private void stopTimer()
    {
        timer.cancel();
    }   
    
    private void gotoOnlineMode()
    {
        startTimer();
        onlineButton.setSelected(true);
    }
    
    private void gotoOfflineMode()
    {
        stopTimer();
        onlineButton.setSelected(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu menuSettings;
    private javax.swing.JMenuItem miClose;
    private javax.swing.JMenuItem miSettings;
    private javax.swing.JToggleButton onlineButton;
    private jissueclient.gui.IssueTable tableContent;
    // End of variables declaration//GEN-END:variables
}

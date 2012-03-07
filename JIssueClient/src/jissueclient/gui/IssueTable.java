/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient.gui;

import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;
import jissueclient.ErrorHandler;
import jissueclient.RemoteEntityProvider;

/**
 *
 * @author mychal
 */
public class IssueTable extends JTable implements ErrorHandler
{
    private IssueTableModel model = new IssueTableModel(new RemoteEntityProvider());
    private RowSorter<IssueTableModel> sorter;
    private ErrorHandler errorHandler;
            
    public IssueTable()
    {
        sorter = new TableRowSorter<IssueTableModel>();
        setModel(model);
        setRowSorter(sorter);
        setDoubleBuffered(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setAutoCreateRowSorter(true);
    }

    @Override
    public String getToolTipText(MouseEvent event)
    {
        int row = rowAtPoint(event.getPoint());
        return model.getToolTipText(row);
    }
    
    public void setErrorHandler(ErrorHandler errorHandler)
    {
        this.errorHandler = errorHandler;
        this.model.setErrorHandler(errorHandler);
    }

    @Override
    public void handleError(String errorMessage)
    {
        if(errorHandler != null)
            errorHandler.handleError(errorMessage);
    }
    
    public void refreshData()
    {
        model.invalidateModel();
    }
    
}

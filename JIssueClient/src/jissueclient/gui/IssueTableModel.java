/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient.gui;

import entities.JobToBeDone;
import exceptions.ResponseException;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import jissueclient.EntityProvider;
import jissueclient.ErrorHandler;

/**
 *
 * @author mychal
 */
public class IssueTableModel extends DefaultTableModel
{    
    private EntityProvider entityProvider;
    private List<JobToBeDone> jobs;
    private String[] columnHeaders={"ID", "Data dodania", "Dodał", "Przydzielone do", "Treść", "Priorytet", "Zrobione"};
    private Class[] columnClasses={Integer.class, Date.class, String.class, String.class, String.class, Integer.class, String.class};
    private ErrorHandler errorHandler;
    
    public IssueTableModel(EntityProvider entityProvider)
    {
        this.entityProvider = entityProvider;
        invalidateModel();
    }
    
    public String getToolTipText(int row)
    {
        JobToBeDone job = jobs.get(row);
        return job.getTodo();
    }
    
    @Override
    public int getColumnCount()
    {
        return columnHeaders.length;
    }
    
    public final void invalidateModel()
    {
        try
        {
            jobs = entityProvider.getAllJobs();
            fireTableDataChanged();
        } catch(ResponseException e)
        {
            handleError(e.toString());
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return columnHeaders[column];
    }

    @Override
    public int getRowCount()
    {
        if(jobs == null)
            return 0;
        return jobs.size();
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        JobToBeDone job = jobs.get(row);
        switch(column)
        {
            case 0:
            {
                return job.getId();
            }
            case 1:
            {
                return job.getDateAdded();
            }
            case 2:
            {
                return job.getRequestor().getLogin();
            }
            case 3:
            {
                return job.getProgrammer().getLogin();
            }
            case 4:
            {
                return job.getTodo();
            }
            case 5:
            {
                return job.getPriority();
            }
            case 6:
            {
                return job.isDone()?"TAK":"NIE";
            }default:
            {
                return null;
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClasses[columnIndex];
    }
    
    public void setErrorHandler(ErrorHandler errorHandler)
    {
        this.errorHandler = errorHandler;
    }
    
    private void handleError(String errorMessage)
    {
        if(errorHandler != null)
            errorHandler.handleError(errorMessage);
    }
    
}

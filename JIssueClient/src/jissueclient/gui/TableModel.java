/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissueclient.gui;

import entities.JobToBeDone;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import jissueclient.EntityProvider;

/**
 *
 * @author mychal
 */
public class TableModel extends DefaultTableModel
{    
    private EntityProvider entityProvider;
    private List<JobToBeDone> jobs;
    
    
    
    public TableModel(EntityProvider entityProvider)
    {
        this.entityProvider = entityProvider;
        invalidateModel();
    }
    
    @Override
    public int getColumnCount()
    {
        return 5;
    }
    
    public final void invalidateModel()
    {
        jobs = entityProvider.getAllJobs();
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column)
    {
        switch(column)
        {
            case 0:
            {
                return "ID";
            }
            case 1:
            {
                return "Dodał";
            }
            case 2:
            {
                return "Przydzielone do";
            }
            case 3:
            {
                return "Treść";
            } 
            case 4:
            {
                return "Priorytet";
            }
            default:
            {
                return "";
            }
        }
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
        if(column == 2)
            return "AA";
        else
            return "BB";
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return true;
    }
    
    
    
}

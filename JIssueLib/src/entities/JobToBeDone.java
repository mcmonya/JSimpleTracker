/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * This is entity class for a TODO entities. It is stored in database
 * 
 * @author Mychal
 */
public class JobToBeDone implements Serializable{
    private int id = -1;
    private User requestor;
    private User programmer;
    private String todo;
    private Date dateAdded;
    private int priority = 1;
    private boolean done = false;

    public JobToBeDone() {
        dateAdded = new Date();
    }
    
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public User getProgrammer() {
        return programmer;
    }

    public void setProgrammer(User programmer) {
        this.programmer = programmer;
    }

    public User getRequestor() {
        return requestor;
    }

    public void setRequestor(User requestor) {
        this.requestor = requestor;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}

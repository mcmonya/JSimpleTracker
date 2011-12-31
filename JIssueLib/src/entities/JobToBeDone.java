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
    private int id;
    private int requestorId;
    private int programmerId;
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

    /**
     * Sets the date of creation
     * 
     * @param dateAdded The date when an entity was created
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * 
     * @return id of the entity in database. -1 if not persisted yet.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this entity. Should be only set by manager while persisting/retrieving from database
     * @param id unique id respectively persisted/retrieved/not-yet-persisted entity
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of that entity. The higher priority is, the job should be done faster
     * @param priority priority of the job to be done
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getProgrammerId() {
        return programmerId;
    }

    /**
     * Sets the programmer's id whose is responsible for doing the job
     * @param programmerId 
     */
    public void setProgrammerId(int programmerId) {
        this.programmerId = programmerId;
    }

    public int getRequestorId() {
        return requestorId;
    }

    /**
     * Sets the requestor's id. Requestor's id is id of user who adds an item.
     * @param requestorId 
     */
    public void setRequestorId(int requestorId) {
        this.requestorId = requestorId;
    }

    /**
     * Represenst description of operation to be done
     * @return 
     */
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

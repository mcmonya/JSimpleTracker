/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jissuetracker.entities;
import java.sql.*;

/**
 *
 * @author Mychal
 */
public class Programmer {
    private String firstName="";
    private String lastName="";
    private int id=-1;

    public Programmer() {
        try
        {
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException e)
        {
            
        }
    }
    
    public Programmer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }
   
    
}

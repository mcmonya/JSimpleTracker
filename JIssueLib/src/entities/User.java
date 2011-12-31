/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.io.Serializable;

/**
 * This class represents single system user. Data about all users is stored in database.
 * Since the application is not security-critical passwords are stored in plain text
 * Wchich may possibly change in the future.
 * 
 * @author Mychal
 */
public class User implements Serializable{
    private String nick="";
    private String password="";
    private int id=-1;

    public User() {
    }
    
    public User(String nick, String password)
    {
        this.password = password;
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

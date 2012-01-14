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
    private String login="";
    private String password="";
    private int id=-1;

    public User() {
    }
    
    public User(String nick, String password)
    {
        this.password = password;
        this.login = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String nick) {
        this.login = nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 67 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 67 * hash + this.id;
        return hash;
    }
}

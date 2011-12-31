/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.User;

/**
 * Class used for retrieve/store/update Users entities
 * 
 * @author Mychal
 */
public class UserManager {
    private static UserManager instance = null;
    public void persist(User user)
    {
        
    }
    public static synchronized UserManager getInstance()
    {
        if(instance == null)
            instance = new UserManager();
        return instance;
    }
}

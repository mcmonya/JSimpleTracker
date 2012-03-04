/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 * Interface of server response.
 * @author mychal
 */
public interface Response extends Serializable
{   
   /**
    * If error has occured nothing can be assumed about particular response object. It should not be casted
    * to any other type. Error messages should be presented to user.
    * @return true if error list is not empty
    */
   public boolean errorOccured();
   
   /**
    * 
    * @return error message associated to given response
    */
   public String getErrorMessage();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 * Interface representing single request sent to server. It provides login and password of user sending request.
 * @author mychal
 */
public interface Request extends Serializable
{
    public String getUserName();
    public String getPassword();
    public Response produceResponse(IssuesModel model);
}

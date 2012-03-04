/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

/**
 *
 * @author mychal
 */
public abstract class AbstractRequest implements Request
{
    private String userName;
    private String password;
    
    public AbstractRequest(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public String getUserName()
    {
        return userName;
    }
    
    @Override
    public String getPassword()
    {
        return password;
    }
    
    @Override
    public abstract Response produceResponse(IssuesModel model);
}

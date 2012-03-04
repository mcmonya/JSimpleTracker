/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import exceptions.ModelException;

/**
 *
 * @author mychal
 */
public abstract class AbstractRequest implements Request
{
    private String userName;
    private String password;
    protected AbstractResponse response;
    protected IssuesModel model;
    
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
    public Response produceResponse(IssuesModel model)
    {
        this.model = model;
        this.response = createResponse();
        try
        {
            onProduceResponse();
        } catch(ModelException e)
        {
            this.response.reportError(e.toString());
        }
        return response;
    }
    
    protected abstract AbstractResponse createResponse();
    protected abstract void onProduceResponse() throws ModelException;
}

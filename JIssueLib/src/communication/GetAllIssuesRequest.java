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
public class GetAllIssuesRequest extends AbstractRequest
{
    
    public GetAllIssuesRequest(String userName, String password)
    {
        super(userName, password);
    }
    
    @Override
    protected AbstractResponse createResponse()
    {
        return new GetAllIssuesResponse();
    }

    @Override
    protected void onProduceResponse() throws ModelException
    {
        GetAllIssuesResponse result = (GetAllIssuesResponse)this.response;
        result.setJobs(model.getAllJobs());
    }
    
}

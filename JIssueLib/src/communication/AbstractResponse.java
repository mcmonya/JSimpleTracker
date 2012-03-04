/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mychal
 */
public abstract class AbstractResponse implements Response
{
    private List<String> errorMessages = new ArrayList<String>();

    public void reportError(String errorMessage)
    {
        errorMessages.add(errorMessage);
    }
    
    @Override
    public boolean errorOccured()
    {
        return !errorMessages.isEmpty();
    }

    @Override
    public String getErrorMessage()
    {
        if(!errorOccured())
            throw new IllegalStateException();
        Iterator<String> iterator = errorMessages.iterator();
        StringBuilder builder = new StringBuilder(iterator.next());
        while(iterator.hasNext())
        {
            builder.append("\n");
            builder.append(iterator.next());
        }
        return builder.toString();
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

/**
 *
 * @author mychal
 */
public class AuthenticationFailedResponse extends AbstractResponse
{
    public AuthenticationFailedResponse()
    {
        reportError("Nieprawidłowy login i/lub hasło");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author mychal
 */
public class ResponseException extends Exception
{

    /**
     * Creates a new instance of
     * <code>ResponseException</code> without detail message.
     */
    public ResponseException()
    {
    }

    /**
     * Constructs an instance of
     * <code>ResponseException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ResponseException(String msg)
    {
        super(msg);
    }
}

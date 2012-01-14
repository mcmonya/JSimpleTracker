/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Mychal
 */
public class UserCreationException extends Exception {

    /**
     * Creates a new instance of <code>UserCreationException</code> without detail message.
     */
    public UserCreationException() {
    }

    /**
     * Constructs an instance of <code>UserCreationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UserCreationException(String msg) {
        super(msg);
    }
}

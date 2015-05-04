/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.exception;

/**
 *
 * @author miguel
 */
public class BibliotecaException extends Exception {

    public BibliotecaException() {
    }

    public BibliotecaException(String message) {
        super(message);
    }

    public BibliotecaException(Throwable cause) {
        super(cause);
    }

    public BibliotecaException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

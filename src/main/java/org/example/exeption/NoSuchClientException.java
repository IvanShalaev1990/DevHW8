package org.example.exeption;

import java.util.NoSuchElementException;

public class NoSuchClientException extends NoSuchElementException {
    public NoSuchClientException(){}
    public NoSuchClientException(String message){
        super(message);
    }
    public NoSuchClientException(Throwable cause){
        super(cause);
    }
    public NoSuchClientException(String message,Throwable cause){
        super(message, cause);
    }
}

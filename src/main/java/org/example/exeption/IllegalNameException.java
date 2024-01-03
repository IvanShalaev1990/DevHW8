package org.example.exeption;

public class IllegalNameException extends IllegalArgumentException{
    public IllegalNameException(){}
    public IllegalNameException(String message){
        super(message);
    }
    public IllegalNameException(Throwable cause){
        super(cause);
    }
    public IllegalNameException(String message,Throwable cause){
        super(message, cause);
    }
}

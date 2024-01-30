package de.neuefische.team2.backend.exception;

public class NoSuchBookException extends Exception{
    public NoSuchBookException(String message){
        super(message);
    }
}

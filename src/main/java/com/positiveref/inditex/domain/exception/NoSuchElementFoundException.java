package com.positiveref.inditex.domain.exception;

public class NoSuchElementFoundException extends RuntimeException{
    public NoSuchElementFoundException(){
        super("Element not found");
    }
}

package com.rubatino.springreact.exceptions;

public class AuthenticationError extends RuntimeException{

    public AuthenticationError(String msg){
        super(msg);
    }
}

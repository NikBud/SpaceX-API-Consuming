package com.example.nbudeanski.spacex_api.exceptions;

public class NoSuchRocketException extends RuntimeException{
    public NoSuchRocketException(String message){
        super(message);
    }
}

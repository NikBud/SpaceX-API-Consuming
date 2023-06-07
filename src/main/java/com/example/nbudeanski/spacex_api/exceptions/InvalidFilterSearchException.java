package com.example.nbudeanski.spacex_api.exceptions;

public class InvalidFilterSearchException extends RuntimeException{

    public InvalidFilterSearchException(){
        super("Invalid filter search pattern!");
    }
}

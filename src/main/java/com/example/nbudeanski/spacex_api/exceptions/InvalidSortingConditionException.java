package com.example.nbudeanski.spacex_api.exceptions;

public class InvalidSortingConditionException extends RuntimeException {

    public InvalidSortingConditionException(String condition){
        super("Invalid sorting parameter: " + condition);
    }
}

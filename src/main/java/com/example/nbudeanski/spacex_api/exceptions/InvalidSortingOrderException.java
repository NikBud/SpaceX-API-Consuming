package com.example.nbudeanski.spacex_api.exceptions;

public class InvalidSortingOrderException extends RuntimeException {

    public InvalidSortingOrderException(String providedOrder) {
        super("Invalid sorting order provided: " + providedOrder);
    }
}

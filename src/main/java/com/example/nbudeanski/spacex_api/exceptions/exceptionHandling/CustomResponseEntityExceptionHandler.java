package com.example.nbudeanski.spacex_api.exceptions.exceptionHandling;

import com.example.nbudeanski.spacex_api.exceptions.InvalidFilterSearchException;
import com.example.nbudeanski.spacex_api.exceptions.InvalidSortingConditionException;
import com.example.nbudeanski.spacex_api.exceptions.InvalidSortingOrderException;
import com.example.nbudeanski.spacex_api.exceptions.NoSuchRocketException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchRocketException.class})
    public final ResponseEntity<ErrorDetails> handleNoSuchRocketException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidFilterSearchException.class, InvalidSortingConditionException.class, InvalidSortingOrderException.class})
    public final ResponseEntity<ErrorDetails> handleInvalidFilterAndSortConditions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

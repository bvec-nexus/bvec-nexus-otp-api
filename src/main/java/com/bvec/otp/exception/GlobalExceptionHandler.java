package com.bvec.otp.exception;

import com.bvec.otp.exception.model.ErrorModel;
import com.bvec.otp.exception.types.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorModel> handleInternalServerException(InternalServerException ex){
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorModel> handleException(Exception ex, HttpStatus status){
        ErrorModel errorModel = new ErrorModel(ex.getMessage(), status.value(), ex instanceof InternalServerException ?
                ((InternalServerException) ex).getErrorDetails() : null);

        return new ResponseEntity<>(errorModel, status);
    }
}

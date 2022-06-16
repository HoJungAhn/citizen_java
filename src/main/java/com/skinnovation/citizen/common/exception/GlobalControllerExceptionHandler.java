package com.skinnovation.citizen.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.skinnovation.citizen.common.exception.exceptions.InvalidInputException;
import com.skinnovation.citizen.common.exception.exceptions.NotFoundException;
import com.skinnovation.citizen.common.exception.exceptions.NotLoginException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
class GlobalControllerExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public HttpErrorInfo handleNotFoundExceptions(Exception ex, WebRequest request) {

        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request, ex);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public HttpErrorInfo handleInvalidInputException(Exception ex, WebRequest request) {

        return createHttpErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, request, ex);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginException.class)
    public HttpErrorInfo handleNotLoginException(Exception ex, WebRequest request) {

        return createHttpErrorInfo(HttpStatus.UNAUTHORIZED, request, ex);
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HttpErrorInfo handleRuntimeException(Exception ex, WebRequest request) {

        return createHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request, ex);
    }
    
    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, WebRequest request, Exception ex) {
        final String path = request.getContextPath();
        final String message = ex.getMessage();
        log.error(ex.toString(), ex);
        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new HttpErrorInfo(httpStatus, path, message);
    }    
    
}
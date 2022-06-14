package com.booking.recruitment.hotel.exception.handler;

import com.booking.recruitment.hotel.dto.GenericResponse;
import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.exception.ElementWithSameIDAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class HttpErrorResponseHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected GenericResponse handleInvalidRequest(final BadRequestException ex) {
        log.error("Bad Request {}", ex.getMessage());
        return new GenericResponse(ex.getMessage());
    }

    @ExceptionHandler(ElementWithSameIDAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected GenericResponse handleElementWithSameIdRequest(final ElementWithSameIDAlreadyExistsException ex) {
        log.error("Bad Request {}", ex.getMessage());
        return new GenericResponse(ex.getMessage());
    }

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected GenericResponse handleResourceNotFound(final ElementNotFoundException ex) {
        log.error("not found {}", ex.getMessage());
        return new GenericResponse(ex.getMessage());
    }

    // Any unhandled exceptions should simply return an empty 500 response.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected void handleUnknownError() {
        // Returns a 500 with no response body. Could add logging here, if necessary.
    }

}

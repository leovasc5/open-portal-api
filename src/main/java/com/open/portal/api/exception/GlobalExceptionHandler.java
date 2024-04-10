package com.open.portal.api.exception;

import com.open.portal.api.exception.http.BadRequestException;
import com.open.portal.api.exception.http.ConflictException;
import com.open.portal.api.exception.http.NoContentException;
import com.open.portal.api.exception.http.NotFoundException;
import com.open.portal.api.exception.http.PreconditionFailedException;
import com.open.portal.api.exception.http.ServiceUnavailableException;
import com.open.portal.api.exception.http.UnauthorizedException;
import com.open.portal.api.exception.http.UnprocessableEntityException;
import com.open.portal.api.exception.http.UnsupportedMediaException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoContentException.class) // 204
    public ResponseEntity<ErrorResponse> handleNoContentException(NoContentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BadRequestException.class) // 400
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class) // 401
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class) // 404
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class) // 409
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PreconditionFailedException.class) // 412
    public ResponseEntity<ErrorResponse> handlePreconditionFailed(PreconditionFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.PRECONDITION_FAILED);
    }    

    @ExceptionHandler(UnsupportedMediaException.class) // 415
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaException(UnsupportedMediaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(UnprocessableEntityException.class) // 422
    public ResponseEntity<ErrorResponse> UnprocessableEntityException(UnprocessableEntityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ServiceUnavailableException.class) // 503
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(ServiceUnavailableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
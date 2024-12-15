package edu.bootcamp.tma.exception;

import edu.bootcamp.tma.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskAlreadyExist.class)
    protected ResponseEntity<ErrorResponse> handleTaskAlreadyExistException(TaskAlreadyExist exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("BAD REQUEST");
        errorResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

package ru.khakimov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.khakimov.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> invalidArgumentHandle(MethodArgumentNotValidException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(badRequest)
                .body(
                        ApiErrorResponse.buildResponse(
                                "Invalid arguments",
                                badRequest.toString(),
                                ex
                        )
                );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> userAlreadyExists(UserAlreadyExistsException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(badRequest)
                .body(
                        ApiErrorResponse.buildResponse(
                                "Re-registration",
                                badRequest.toString(),
                                ex
                        )
                );
    }

}

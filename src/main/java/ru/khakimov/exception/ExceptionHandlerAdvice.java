package ru.khakimov.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.khakimov.dto.response.ApiErrorResponse;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandlerAdvice implements AuthenticationEntryPoint {
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
    public ResponseEntity<ApiErrorResponse> chatAlreadyExists(UserAlreadyExistsException ex) {
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

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

    }
}

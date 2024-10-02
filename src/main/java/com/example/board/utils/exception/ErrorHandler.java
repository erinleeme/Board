package com.example.board.utils.exception;

import com.example.board.utils.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseDto<String> runtimeException(RuntimeException e) {
        return ResponseDto.fail(e.getMessage());
    }

    /*valid exception*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("methodArgumentNotValidException");
        return ResponseDto.fail(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<String> illegalArgumentException(IllegalArgumentException e) {
        System.out.println("illegalArgumentException");
        return ResponseDto.fail(Objects.requireNonNull(e.getMessage()));
    }
}

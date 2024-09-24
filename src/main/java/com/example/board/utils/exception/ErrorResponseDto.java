package com.example.board.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponseDto {
    private ErrorResponseDto(){}

    private int code;
    private String message;
}

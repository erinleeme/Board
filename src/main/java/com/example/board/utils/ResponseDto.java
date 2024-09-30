package com.example.board.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String status;
    private String message;
    private T data;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>("success", data);
    }

    public static <T> ResponseDto<T> fail(String message) {
        return new ResponseDto<>("fail", message);
    }
}

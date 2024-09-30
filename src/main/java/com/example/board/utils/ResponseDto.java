package com.example.board.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String status;
    private T data;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>("success", data);
    }

    public static <String> ResponseDto<String> fail(String message) {
        return new ResponseDto<>("fail", message);
    }
}

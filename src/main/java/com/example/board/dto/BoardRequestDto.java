package com.example.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BoardRequestDto {
    @NotNull(message = "제목을 작성해 주세요.")
    private String title;
    @NotNull(message = "내용을 작성해 주세요.")
    private String content;
}

package com.example.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class BoardRequestDto {

    private BoardRequestDto() {}

    @NotNull(message = "제목을 작성해 주세요.")
    private String title;
    @NotNull(message = "내용을 작성해 주세요.")
    private String content;
}

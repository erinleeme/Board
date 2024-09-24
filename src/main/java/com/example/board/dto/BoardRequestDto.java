package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@Builder
@Getter
public class BoardRequestDto {

    private BoardRequestDto() {}

    @NotBlank(message = "제목을 작성해 주세요.")
    private String title;
    @NotBlank(message = "내용을 작성해 주세요.")
    private String content;
}

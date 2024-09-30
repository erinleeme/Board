package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class BoardResponseDto {

    private BoardResponseDto () {}

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}

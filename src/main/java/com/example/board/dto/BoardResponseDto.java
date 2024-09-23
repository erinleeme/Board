package com.example.board.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class BoardResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
}

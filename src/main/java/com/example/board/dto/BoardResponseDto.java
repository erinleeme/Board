package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class BoardResponseDto {

    private BoardResponseDto () {}

    private String title;
    private String content;
    private LocalDateTime createdAt;
}

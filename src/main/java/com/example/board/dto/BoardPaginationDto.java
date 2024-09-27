package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BoardPaginationDto {
    private int size;
    private int page;
    private int start;

    private BoardPaginationDto() {}

    public BoardPaginationDto(int size, int page) {
        this.size = size;
        this.page = page;
        this.start = size * (page-1);
    }
}

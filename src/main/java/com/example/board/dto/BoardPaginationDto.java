package com.example.board.dto;

import lombok.Getter;

@Getter
public class BoardPaginationDto {
    private int size;
    private int page;
    private int start;

    public BoardPaginationDto(int size, int page) {
        this.size = size;
        this.page = page;
        start = size * (page-1);
    }
}

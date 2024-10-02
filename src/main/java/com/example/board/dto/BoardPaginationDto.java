package com.example.board.dto;

import com.example.board.utils.enums.SortKeyword;
import lombok.Data;

@Data
public class BoardPaginationDto {
    private int size;
    private int page;
    private int start;
    private SortKeyword sortKeyword;

    private BoardPaginationDto() {}

    public BoardPaginationDto(int size, int page, String sortKeyword){
        checkSortKeyword(sortKeyword);
        this.size = size;
        this.page = page;
        this.start = size * (page - 1);
    }

    private void checkSortKeyword(String sortKeyword) {
        try {
            this.sortKeyword = SortKeyword.valueOf(sortKeyword.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("유효하지 않은 정렬값입니다.");
        }
    }
}

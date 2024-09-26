package com.example.board.repository;

import com.example.board.dto.BoardPaginationDto;
import com.example.board.entity.Board;

import java.util.List;

public interface BoardCustomRepository {
    List<Board> getAllBoards(BoardPaginationDto boardPaginationDto);
}

package com.example.board.controller;

import com.example.board.dto.BoardPaginationDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.board.utils.ResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseDto<BoardResponseDto> createBoard(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto);
        return ResponseDto.success(boardResponseDto);
    }

    @GetMapping("/board")
    public ResponseDto<List<BoardResponseDto>> getAllBoards(@RequestParam(value = "size") int size, @RequestParam(value = "page") int page) {
        BoardPaginationDto boardPaginationDto = new BoardPaginationDto(size, page);
        List<BoardResponseDto> boardList = boardService.getAllBoards(boardPaginationDto);
        return ResponseDto.success(boardList);
    }

    @PatchMapping("/board/{boardId}")
    public ResponseDto<BoardResponseDto> updateBoard(@PathVariable("boardId") long boardId,
                                                     @RequestBody @Valid BoardRequestDto boardRequestDto) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(boardId, boardRequestDto);
        return ResponseDto.success(boardResponseDto);
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseDto<Long> deleteBoard(@PathVariable("boardId") long boardId) {
        Long deletedBoardId = boardService.deleteBoard(boardId);
        return ResponseDto.success(deletedBoardId);
    }
}

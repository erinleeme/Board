package com.example.board.controller;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.board.utils.ResponseDto;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseDto<BoardResponseDto> createBoard(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto);
        return ResponseDto.success(boardResponseDto);
    }
}

package com.example.board.controller;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @MockBean
    BoardService boardService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BoardRequestDto boardRequestDto;
    BoardResponseDto boardResponseDto;


    @Test
    public void createBoard_Success() throws Exception {
        boardRequestDto = BoardRequestDto.builder()
                .title("test board title")
                .content("test board content")
                .build();

        Board board = new Board(1L, boardRequestDto.getTitle(), boardRequestDto.getContent(), LocalDateTime.now(), LocalDateTime.now(), null);

        boardResponseDto = BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();

        given(boardService.createBoard(boardRequestDto)).willReturn(boardResponseDto);

        mockMvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardRequestDto)))
                .andExpect(status().isOk());
    }
}

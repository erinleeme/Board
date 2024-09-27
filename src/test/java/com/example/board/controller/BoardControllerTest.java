package com.example.board.controller;

import com.example.board.dto.BoardPaginationDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    BoardPaginationDto boardPaginationDto;
    String code;
    String message;

    @BeforeEach
    void beforeEach(){
        code = "$..code";
        message = "$..message";
    }


    @Test
    public void createBoard_Success() throws Exception {
        boardRequestDto = BoardRequestDto.builder()
                .title("test board title")
                .content("test board content")
                .build();

        Board board = new Board(1L, boardRequestDto.getTitle()
                , boardRequestDto.getContent()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , null);

        boardResponseDto = BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();

        given(boardService.createBoard(boardRequestDto)).willReturn(boardResponseDto);

        mockMvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createBoard_ValidError() throws Exception {
        boardRequestDto = BoardRequestDto.builder()
                .title("")
                .content("test board content")
                .build();

        mockMvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(message).value("제목을 작성해 주세요."));
    }

    @Test
    public void getAllBoards_Success() throws Exception{
        int size = 5;
        int page = 1;

        BoardPaginationDto boardPaginationDto = new BoardPaginationDto(size, page);

        List<BoardResponseDto> boardList = new ArrayList<>();
        for(int i=0 ; i<10 ; i++) {
            boardList.add(new BoardResponseDto("title"+i, "content"+i, LocalDateTime.now()));
        }

        when(boardService.getAllBoards(boardPaginationDto)).thenReturn(boardList);

        ResultActions actions = mockMvc.perform(get("/board")
                .param("size", "5")
                .param("page", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(boardList)));
    }
}

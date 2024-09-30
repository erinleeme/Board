package com.example.board.controller;

import com.example.board.dto.BoardPaginationDto;
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
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();

        given(boardService.createBoard(any(BoardRequestDto.class))).willReturn(boardResponseDto);

        mockMvc.perform(post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.id").value(boardResponseDto.getId()))
                .andExpect(jsonPath("$.data.title").value(boardResponseDto.getTitle()))
                .andExpect(jsonPath("$.data.content").value(boardResponseDto.getContent()))
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
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.message").value("제목을 작성해 주세요."))
                .andDo(print());
    }

    @Test
    public void getAllBoards_Success() throws Exception{
        int size = 5;
        int page = 1;

        BoardPaginationDto boardPaginationDto = new BoardPaginationDto(size, page);

        List<BoardResponseDto> boardList = new ArrayList<>();
        for(int i=0 ; i<10 ; i++) {
            boardList.add(new BoardResponseDto((long) i,"title", "content", LocalDateTime.now()));
        }

        when(boardService.getAllBoards(boardPaginationDto)).thenReturn(boardList);

        ResultActions actions = mockMvc.perform(get("/board")
                .param("size", "5")
                .param("page", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(boardList.size()))
                .andDo(print());
    }

    @Test
    public void updateBoard_Success() throws Exception {
        Long boardId = 1L;

        boardRequestDto = BoardRequestDto.builder()
                .title("test updated title")
                .content("test updated content")
                .build();

        Board board = new Board(
                boardId
                , boardRequestDto.getTitle()
                , boardRequestDto.getContent()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , null);

        boardResponseDto = BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();

        when(boardService.updateBoard(any(), any())).thenReturn(boardResponseDto);

        mockMvc.perform(patch("/board/"+boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardResponseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.id").value(boardResponseDto.getId()))
                .andExpect(jsonPath("$.data.title").value(boardResponseDto.getTitle()))
                .andExpect(jsonPath("$.data.content").value(boardResponseDto.getContent()))
                .andDo(print());
    }

    @Test
    public void deleteBoard_Success() throws Exception {
        Long boardId = 1L;

        when(boardService.deleteBoard(boardId)).thenReturn(boardId);

        mockMvc.perform(delete("/board/"+boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardResponseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").value(boardId))
                .andDo(print());
    }

    @Test
    public void deleteBoard_FailById() throws Exception {
        long boardId = 1L;

        doThrow(new RuntimeException("찾을 수 없는 게시판입니다. id : " + boardId)).when(boardService).deleteBoard(boardId);

        mockMvc.perform(delete("/board/"+boardId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}

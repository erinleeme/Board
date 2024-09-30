package com.example.board.service;

import com.example.board.dto.BoardPaginationDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        boardRepository.save(board);

        return BoardResponseDto.builder()
                .id(board.getId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .createdAt(board.getCreatedAt())
                .build();
    }

    public List<BoardResponseDto> getAllBoards(BoardPaginationDto boardPaginationDto) {
        List<Board> boardList = boardRepository.getAllBoards(boardPaginationDto);
        return boardList.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 게시판입니다. id : " + boardId));

        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        board.setUpdatedAt(LocalDateTime.now());

        return convertToResponseDto(board);
    }

    @Transactional
    public Long deleteBoard(long boardId) {
        if(!boardRepository.existsById(boardId)) {
            throw new RuntimeException("찾을 수 없는 게시판입니다. id : " + boardId);
        }
        boardRepository.deleteById(boardId);
        return boardId;
    }

    private BoardResponseDto convertToResponseDto(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getCreatedAt());
    }
}

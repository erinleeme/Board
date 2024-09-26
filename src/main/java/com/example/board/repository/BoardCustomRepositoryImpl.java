package com.example.board.repository;

import com.example.board.dto.BoardPaginationDto;
import com.example.board.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.board.entity.QBoard.board;

public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public BoardCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Board> getAllBoards(BoardPaginationDto boardPaginationDto) {
        return jpaQueryFactory.selectFrom(board)
                    .orderBy(board.createdAt.desc())
                    .offset(boardPaginationDto.getStart())
                    .limit(boardPaginationDto.getSize())
                    .fetch();
    }
}

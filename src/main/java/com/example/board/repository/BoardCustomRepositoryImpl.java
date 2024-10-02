package com.example.board.repository;

import com.example.board.dto.BoardPaginationDto;
import com.example.board.entity.Board;
import com.querydsl.jpa.impl.JPAQuery;
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
        JPAQuery<Board> query = jpaQueryFactory.selectFrom(board);

        if(boardPaginationDto.getSortKeyword().toString().equalsIgnoreCase("asc")) {
            System.out.print("asc");
            return query.orderBy(board.createdAt.asc())
                    .limit(boardPaginationDto.getSize())
                    .offset(boardPaginationDto.getStart())
                    .fetch();
        }
        System.out.print("desc");
        return query.orderBy(board.createdAt.desc())
                .limit(boardPaginationDto.getSize())
                .offset(boardPaginationDto.getStart())
                .fetch();
    }
}

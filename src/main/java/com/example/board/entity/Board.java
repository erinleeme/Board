package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(length = 255)
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

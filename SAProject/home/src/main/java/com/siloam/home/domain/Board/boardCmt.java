package com.siloam.home.domain.Board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class boardCmt {
    @Id @GeneratedValue
    private Long commentId;
    private Long boardId;
    private String body;
    private LocalDateTime regDate;
    private String regPrsn;
    private LocalDateTime updtDate;
    private String updtPrsn;

}

package com.siloam.home.domain.Board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class board
{
    @Id
    @GeneratedValue
    private Long id;
    private String writerId;
    private Long count;
    private String pwd;
    private BoardStatus openStatus;
    private LocalDateTime regDate;
    private LocalDateTime modiDate;
    private LocalDateTime modiId;
}

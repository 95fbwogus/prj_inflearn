package com.siloam.home.domain.Board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import com.siloam.home.domain.constant.BoardStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class board
{
    @Id
    @GeneratedValue
    private Long id;
    private String writerId;
    private int[] commentIds;
    private String body;
    private Long count;
    private String pwd;
    private BoardStatus openStatus;
    private LocalDateTime regDate;
    private String regPrsn;
    private LocalDateTime modiDate;
    private String updtPrsn;
}

package com.siloam.home.domain.board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import com.siloam.home.domain.constant.BoardStatus;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_boardmst")
public class Board
{
    @Id
    @GeneratedValue
    private Long id;
    private String category;
    private String writerId;
    private String body;
    private String boardPwd;
    private BoardStatus status;
    private LocalDateTime regDate;
    private String regPrsn;
    private LocalDateTime updtDate;
    private String updtPrsn;
}

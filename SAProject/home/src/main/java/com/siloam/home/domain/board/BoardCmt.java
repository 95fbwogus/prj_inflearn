package com.siloam.home.domain.board;

import com.siloam.home.domain.constant.CommentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_boardcmt")
public class BoardCmt {
    @Id
    @GeneratedValue
    private Long commentId;
    private Long boardId;
    private CommentStatus status;
    private String body;
    private LocalDateTime regDate;
    private String regPrsn;
    private LocalDateTime updtDate;
    private String updtPrsn;
}

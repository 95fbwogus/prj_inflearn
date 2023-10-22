package com.siloam.home.domain.member.impl;

import com.siloam.home.domain.constant.RoleType;
import com.siloam.home.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "tb_user01m01")
public class SiloamMember implements Member {

    @Id
    @Column(unique = true, nullable = false)
    private String userCode;
    private String userId;
    private String userPassword;
    private String userLevel;
    @Enumerated(EnumType.STRING)
    private RoleType userRoleType;
    private String userEmail;
    private String userTel;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastLogin;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createDate;
    private String createPerson;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateDate;
    private String updatePerson;

}

package com.siloam.home.domain.member.impl;

import com.siloam.home.domain.Constant.RoleType;
import com.siloam.home.domain.Constant.SysConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberTest {

    private List<SiloamMember> members = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder();

    public MemberTest() {
        for(int i = 0 ; i < 1000; i++) {
            SiloamMember member = new SiloamMember();
            String testId = String.format("%010d", i);

            member.setUserCode(testId);
            member.setUserId("fbwogus_" + i);
            member.setUserPassword("135456");

            sb.append("fwog");
            sb.append(i);
            sb.append("@naver.com");
            member.setUserEmail(sb.toString());
            sb.delete(0, sb.length());

            member.setUserLevel("0");
            member.setUserRoleType(RoleType.USER);
            member.setUserTel(testId);
            member.setCreatePerson(SysConstant.SYSTEM);
            member.setCreateDate(SysConstant.getSysTime());
            member.setLastLogin(SysConstant.getSysTime());
            members.add(member);
        }
    }
}

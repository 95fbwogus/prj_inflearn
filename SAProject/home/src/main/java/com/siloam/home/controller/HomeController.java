package com.siloam.home.controller;

import com.siloam.home.domain.constant.SysConstant;
import com.siloam.home.domain.member.impl.MemberTest;
import com.siloam.home.domain.member.impl.SiloamMember;
import com.siloam.home.repository.member.MemberRepository;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbccTemplate;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;

    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping ("/")
    @Transactional
    public String getHome(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        log.debug("@@@@@@@@@@@@getHome reached@@@@@@@@@@@@@@");
        //SiloamMember member = (SiloamMember) memberRepository.findMember("10220635");
        //SessionFactory factory = new Configuration().configure("persistence.xml") .addAnnotatedClass(SiloamMember.class) .buildSessionFactory();
        try(Connection connection = dataSource.getConnection()) {
            log.info("Start DB Connection");
            log.debug("class: " + String.valueOf(dataSource.getClass()));
            log.debug("URL: " + connection.getMetaData().getURL());
            log.debug("유저이름: " + connection.getMetaData().getUserName());
            log.debug("스키마: " + connection.getSchema());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.debug("###########################" + SysConstant.getSysTime());

        MemberTest memberTest = new MemberTest();
        List<SiloamMember> members = memberTest.getMembers();

        // em.remove(m) 해버리면 java.lang.IllegalArgumentException: Removing a detached instance com.siloam.home.domain.member.impl.SiloamMember#0000000000 오류남..
        //members.forEach(m -> em.remove(m));
//        members.forEach(m -> em.remove(em.contains(m) ? m : em.merge(m)));
        log.debug("=================== Persist Start ===================");
        members.forEach(m -> em.persist(m));
        log.debug("=================== Persist End ===================");
        SiloamMember cache1 = em.find(SiloamMember.class, "0000000057");
        log.debug("cache1.id : " + cache1.getUserId());
        log.debug("cache1.code : " + cache1.getUserCode());

        log.debug("@@@@@select@@@@@");
        List<SiloamMember> result = em.createQuery("SELECT m FROM SiloamMember as m", SiloamMember.class)
                .setFirstResult(1)
                .setMaxResults(150)
                .getResultList();

        result.forEach(m -> log.debug(m.getUserId()));


        em.close();
        return "main/home";
    }
}

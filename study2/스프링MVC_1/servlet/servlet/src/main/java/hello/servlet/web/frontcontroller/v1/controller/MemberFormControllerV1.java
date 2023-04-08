package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.basic.HelloServlet;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import lombok.Builder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

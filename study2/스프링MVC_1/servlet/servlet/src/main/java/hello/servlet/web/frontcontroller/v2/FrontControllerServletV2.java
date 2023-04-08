package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        System.out.println("FrontControllerServletV2 Constructor Start!");
        this.controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        this.controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        this.controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger.getLogger(">>> FrontControllerServletV2.service");
        System.out.println(">>> FrontControllerServletV2.serivce");
        String requestURI =request.getRequestURI();
        System.out.println(requestURI);
        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller == null) {
            System.out.println("NOT FOUND!");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        System.out.println("성공");
        MyView view = controller.process(request,response);
        view.render(request, response);
    }
}

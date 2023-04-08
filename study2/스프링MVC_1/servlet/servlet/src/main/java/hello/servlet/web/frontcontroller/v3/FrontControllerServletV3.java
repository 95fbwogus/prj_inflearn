package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        System.out.println("FrontControllerServletV2 Constructor Start!");
        this.controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV3());
        this.controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV3());
        this.controllerMap.put("/front-controller/v2/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(">>> FrontControllerServletV2.serivce");

        String requestURI =request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);

        if(controller == null) {
            System.out.println("NOT FOUND!");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap, 이 과정을 거쳐야 request의 param을 전부 맵으로 새로 만들어 보낼 수 있다.
        //그런데 이 과정이 너무 디테일하다. 다른 과정들은 넓은 범위의 컨트롤러를 가져오고 하는데...
        //이건 따로 메서드로 뽑아야 한다. ctrl + alt + M // Extract Method
        /*
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        */
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); //// 뷰의 논리 이름만 얻을 수 있다.

        //이것 또한 너무 디테일하다.
        //MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        MyView view = viewResolver(viewName);

        //view.render(request, response); //화면을 그릴 때 필요한 모델을 넣어줘야 한다.
        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

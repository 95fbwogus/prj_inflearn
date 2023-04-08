package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        /* 컨트롤러 V3를 구현하는 것이면 TRUE */
        System.out.println("handler instanceof ControllerV4 ? " + (handler instanceof ControllerV4));
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler; //supports에 의해서 ControllerV3를 구현한 것이 확실해짐
        Map<String, String> paramMap = createParamMap(request);

        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName); // 그냥 String으로 return 하면 오류 발생. 어댑터답게 일한다.
        mv.setModel(model); // 컨트롤러 process에서 필요한 model은 다 담아둔다.

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(pname -> paramMap.put(pname, (String) request.getParameter(pname)));

        return paramMap;
    }
}

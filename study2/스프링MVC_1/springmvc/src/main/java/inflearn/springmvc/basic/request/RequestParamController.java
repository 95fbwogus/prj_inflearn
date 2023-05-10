package inflearn.springmvc.basic.request;

import inflearn.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {} // age = {}", username, age);

        response.getWriter().write("OK");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam String memberName,
            @RequestParam int memberAge
    ) {
        /* 컨트롤러이면서, String을 반환하면 논리적 view 이름으로, view를 찾게 된다.
        * 이를 방지하기 위해서 RestController 혹은 해당 메소드에 ResponseBody를 붙여서 반환하는
        * 메시지를 http 메시지 바디에 박아버린다. */
        log.info("memberName = {} // memberAge = {}", memberName, memberAge);

        return "request-param-v2 OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            String username,
            int age
    ) {
        log.info("memberName = {} // memberAge = {}", username, age);
        return "request-param-v3 OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamV4(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) {
        log.info("memberName = {} // memberAge = {}", username, age);
        return "request-param-required OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "Guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("memberName = {} // memberAge = {}", username, age);
        return "request-param-required OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("memberName = {} // memberAge = {}", paramMap.get("username"), paramMap.get("age"));
        return "request-param-required OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData(username, age);
        log.info("username: {}, age: {}", helloData.getUsername(), helloData.getAge());
        log.info("username Entity: {}", helloData);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) {
        log.info("username: {}, age: {}", helloData.getUsername(), helloData.getAge());
        log.info("username Entity: {}", helloData);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2_1")
    public String modelAttributeV2_1(HelloData helloData) {
        log.info("username: {}, age: {}", helloData.getUsername(), helloData.getAge());
        log.info("username Entity: {}", helloData);
        return "OK";
    }
}

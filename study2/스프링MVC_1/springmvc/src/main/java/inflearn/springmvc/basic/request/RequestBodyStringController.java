package inflearn.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody : {}", messageBody);

        response.getWriter().write("request-body-string-v1");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyString(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody : {}", messageBody);

        writer.write("request-body-string-v2");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString(HttpEntity<String> httpEntity) throws IOException {
        //HttpMessage Convertor가 작동하여 아래 코드를 대신 만들어준다.
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String messageBody = httpEntity.getBody();
        log.info("messageBody : {}", messageBody);

        return new HttpEntity<>("request-body-string-v3");
    }
    @PostMapping("/request-body-string-v3_1")
    public HttpEntity<String> requestBodyString(RequestEntity<String> requestEntity) throws IOException {
        //HttpMessage Convertor가 작동하여 아래 코드를 대신 만들어준다.
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String messageBody = requestEntity.getBody();
        log.info("messageBody : {}", messageBody);

        return new ResponseEntity<>("request-body-string-v3_1", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyString(@RequestBody String messageBody) throws IOException {
        //HttpMessage Convertor가 작동하여 아래 코드를 대신 만들어준다.
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody : {}", messageBody);

        return "request-body-string-v4";
    }
}

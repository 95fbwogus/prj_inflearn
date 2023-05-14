package inflearn.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import inflearn.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/reqquest-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();

        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        HelloData helloData = objectMapper.readValue(msgBody, HelloData.class);

        log.info("msgBody : {}", msgBody);
        log.info("HelloData : {}", helloData);

        response.getWriter().write("reqquest-body-json-v1");
    }

    @PostMapping("/reqquest-body-json-v2")
    @ResponseBody
    public String requestBodyJsonV1(@RequestBody String msgBody) throws IOException {
        //ServletInputStream inputStream = request.getInputStream();
        //String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        HelloData helloData = objectMapper.readValue(msgBody, HelloData.class);

        log.info("msgBody : {}", msgBody);
        log.info("HelloData : {}", helloData);

        //response.getWriter().write("reqquest-body-json-v1");
        return "reqquest-body-json-v2";
    }

    @PostMapping("/request-body-json-v3")
    @ResponseBody
    public String requestBodyJsonV1(@RequestBody HelloData helloData) throws IOException {
        //ServletInputStream inputStream = request.getInputStream(); V1
        //String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); V1
        //HelloData helloData = objectMapper.readValue(msgBody, HelloData.class); V2

        //log.info("msgBody : {}", msgBody);
        log.info("HelloData : {}", helloData);

        //response.getWriter().write("reqquest-body-json-v1");
        return "request-body-json-v3";
    }

    @PostMapping("/request-body-json-v3_1")
    public HttpEntity<String> requestBodyJsonV_1(HttpEntity<HelloData> helloData) throws IOException {
        //ServletInputStream inputStream = request.getInputStream(); V1
        //String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); V1
        //HelloData helloData = objectMapper.readValue(msgBody, HelloData.class); V2

        //log.info("msgBody : {}", msgBody);
        log.info("HelloData : {}", helloData.getBody());

        //response.getWriter().write("reqquest-body-json-v1");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

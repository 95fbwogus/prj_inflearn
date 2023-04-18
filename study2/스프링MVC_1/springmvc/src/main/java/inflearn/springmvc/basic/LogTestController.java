package inflearn.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("sout");
        log.trace("trace log {}", name);
        log.debug("trace debug {}", name);
        log.info("trace info {}", name);
        log.warn("trace warn {}", name);
        log.error("trace error!!!! {}", name);

        return "log test end";
    }
}

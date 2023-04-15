package hello.servlet.web.springmvc.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {
    private final Logger logger = LoggerFactory.getLogger(SpringMemberFormControllerV1.class);
    
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        logger.info("들어왔어용");
        return new ModelAndView("new-form");
    }
}

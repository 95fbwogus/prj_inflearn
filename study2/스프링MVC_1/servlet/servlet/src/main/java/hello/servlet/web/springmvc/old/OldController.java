package hello.servlet.web.springmvc.old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") /* 스프링 빈의 이름으로 매핑하는 것 */
public class OldController implements Controller {

    private final Logger logger = LoggerFactory.getLogger("OldController");
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("OldController started!");
        return new ModelAndView("new-form");
    }
}

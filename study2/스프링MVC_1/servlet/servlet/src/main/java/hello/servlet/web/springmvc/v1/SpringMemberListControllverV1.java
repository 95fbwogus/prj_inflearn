package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllverV1 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();
    private final Logger logger = LoggerFactory.getLogger(SpringMemberListControllverV1.class);

    @RequestMapping("/springmvc/v1/members/list")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/springmvc/v1/members/list 시작~~~ 합니다.");
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }
}

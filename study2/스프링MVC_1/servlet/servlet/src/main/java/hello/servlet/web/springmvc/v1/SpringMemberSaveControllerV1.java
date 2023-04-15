package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    private final Logger logger = LoggerFactory.getLogger(SpringMemberSaveControllerV1.class);

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/springmvc/v1/members/save 시작~~~~~~ 합니다.");

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);

        return mv;
    }
}

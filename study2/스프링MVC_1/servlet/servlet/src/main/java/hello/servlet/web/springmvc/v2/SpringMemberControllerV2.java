package hello.servlet.web.springmvc.v2;

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

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private Logger logger = LoggerFactory.getLogger("SpringMemberControllerV2");
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        logger.info("new-form started");
        return new ModelAndView("new-form");
    }

    @RequestMapping("/list")
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
        logger.info("members started");
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        logger.info("save started");

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");

        mv.addObject("member", member);

        return mv;
    }
}

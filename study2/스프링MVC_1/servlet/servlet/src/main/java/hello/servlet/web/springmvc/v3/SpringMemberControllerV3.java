package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private Logger logger = LoggerFactory.getLogger("<<< springMemberControllerV3 Logger >>>");
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        logger.info("V3 new-form Controller started");
        return "new-form";
    }

    //@RequestMapping(value = "/list", method = RequestMethod.GET)
    @GetMapping("/list")
    public String members(Model model) {
        logger.info("V3 members Controller started");
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model)
    {
        logger.info("V3 save Controller started");
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);

        return "save-result";
    }
}

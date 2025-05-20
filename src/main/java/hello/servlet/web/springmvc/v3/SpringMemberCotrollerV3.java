package hello.servlet.web.springmvc.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberCotrollerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    @RequestMapping("/save")
    public ModelAndView save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model){
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }


}

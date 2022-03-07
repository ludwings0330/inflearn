package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberFormControllerV1 {

    @RequestMapping("/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView saveMember(HttpServletRequest request, HttpServletResponse response){
        final ModelAndView modelAndView = new ModelAndView("save-result");

        modelAndView.addObject("member", "member object");

        return modelAndView;
    }

    @RequestMapping
    public ModelAndView members() {
        final ModelAndView mv = new ModelAndView("members");

        mv.addObject("member", "members object");

        return mv;
    }
}

package mine.learn.spring.demo4springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
    @ResponseBody
    @RequestMapping("/first")
    public String first() {
        return "first controller";
    }
}

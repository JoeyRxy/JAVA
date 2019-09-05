package mine.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * QuickController
 */
@Controller
public class QuickController {
    @RequestMapping("/quick") // localhost:8080/quick
    @ResponseBody
    public String quick() {
        return "fuck SpringBoot";
    }
}
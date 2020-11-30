package rxy.learn.datascience.datasicencespringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("btn1_name", "City");
        model.addAttribute("btn2_name", "Country");

        return "index";
    }

}

package cn.rxy.trial.rxywebsitedemo.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = { "/index", "/" })
    public String index(HttpSession session, HttpServletResponse resp) {
        Object userid = session.getAttribute("userid");
        if (userid == null) UserController.removeStatCookie(resp);
        return "index";
    }

    @GetMapping(path = "/error")
    public String error() {
        return "error";
    }

}

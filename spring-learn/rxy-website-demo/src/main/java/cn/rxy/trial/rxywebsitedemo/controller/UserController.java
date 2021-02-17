package cn.rxy.trial.rxywebsitedemo.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(HttpSession session, HttpServletResponse resp) {
        if (session.getAttribute("userid") == null) removeStatCookie(resp);
        return "user";
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User puser, HttpSession session, HttpServletResponse resp) {
        User user = userService.login(puser);
        if (user == null)
            return null;
        session.setAttribute("userid", user.getUserid());
        session.setMaxInactiveInterval(1800);
        Cookie cookie1 = new Cookie("username", URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8));
        cookie1.setPath("/");
        resp.addCookie(cookie1);
        Cookie cookie2 = new Cookie("userid", user.getUserid());
        cookie2.setPath("/");
        resp.addCookie(cookie2);
        if (user.isAdmin()) {
            Cookie cookie3 = new Cookie("admin", "true");
            cookie3.setPath("/");
            resp.addCookie(cookie3);
        } else {
            Cookie cookie3 = new Cookie("admin", "false");
            cookie3.setPath("/");
            resp.addCookie(cookie3);
        }
        user.setPassword("");
        return user;
    }

    public static void removeStatCookie(HttpServletResponse resp) {
        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0);
        Cookie useridCookie = new Cookie("userid", "");
        useridCookie.setMaxAge(0);
        resp.addCookie(usernameCookie);
        resp.addCookie(useridCookie);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse resp) {
        session.removeAttribute("userid");
        removeStatCookie(resp);
    }

    @PostMapping("/register")
    @ResponseBody
    public User register(@RequestBody User user) {
        if (user.getUsername() == null || user.getUserid() == null || user.getPassword() == null) return null;
        String prePwd = user.getPassword();
        if (!userService.register(user))
            return null;
        user.setPassword(prePwd);
        return user;
    }

    @PostMapping("/resetpwd")
    @ResponseBody
    public User resetPassword(@RequestParam("token") String token, @RequestParam("newpwd") String newpwd,
            HttpSession session, HttpServletResponse resp) {
        Object userid = session.getAttribute("userid");
        if (userid == null)
            return null;
        User user = new User(userid.toString(), newpwd);
        if (userService.resetPwd(user, token)) {
            session.removeAttribute("userid");
            Cookie usernameCookie = new Cookie("username", "");
            usernameCookie.setMaxAge(0);
            Cookie useridCookie = new Cookie("userid", "");
            useridCookie.setMaxAge(0);
            resp.addCookie(usernameCookie);
            resp.addCookie(useridCookie);
            return user;
        } else
            return null;
    }

}

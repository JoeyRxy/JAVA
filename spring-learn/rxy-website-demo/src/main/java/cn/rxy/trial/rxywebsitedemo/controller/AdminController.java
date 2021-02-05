package cn.rxy.trial.rxywebsitedemo.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;
import cn.rxy.trial.rxywebsitedemo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public String index() {
        return "admin";
    }

    @PostMapping("/allusers")
    @ResponseBody
    public List<User> allUsers(HttpServletRequest req) {
        Object userid = req.getSession().getAttribute("userid");
        if (userid == null)
            return null;
        if (!userService.isAdmin(userid.toString()))
            return null;
        return userService.allUsers();
    }

    @PostMapping("/appointment")
    @ResponseBody
    public Map<Date, List<String>> getAppointmentBetween(@RequestParam("from") long from, @RequestParam("to") long to,
            HttpServletRequest req) {
        if (from > to)
            return null;
        Object userid = req.getSession().getAttribute("userid");
        if (userid == null)
            return null;
        if (!userService.isAdmin(userid.toString()))
            return null;
        return appointmentService.getAppointmentBetween(new Date(from), new Date(to));
    }

    @PostMapping("/cleanpwd")
    public boolean cleanpwd(@RequestParam("userid") String userid, @RequestParam("token") String token,
            HttpServletRequest req) {
        Object t = req.getSession().getAttribute("userid");
        if (!userService.isAdmin(t.toString()))
            return false;
        return userService.cleanPwd(new User(userid, token));
    }

}

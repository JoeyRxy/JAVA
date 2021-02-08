package cn.rxy.trial.rxywebsitedemo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private static final int PAGE_SIZE = 20;
    private static final long week = 7 * 86400000L;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public String index(@RequestParam(value = "mode", required = false) Boolean isUserMode,  Model model, HttpSession session) {
        Object userid = session.getAttribute("userid");
        if (userid == null || !userService.isAdmin(userid.toString())) return null;
        if (isUserMode == null || !isUserMode) {
            long time = System.currentTimeMillis() + 1000;
            model.addAttribute("appointmentMap",
                    appointmentService.getAppointmentBetween(new Date(time), new Date(time + week)));
            model.addAttribute("mode", false);
        } else {
            Page<User> users = userService.allUsersNotAdminByPage(0, PAGE_SIZE);
            List<User> ret = new ArrayList<>(PAGE_SIZE);
            users.forEach(user->{
                user.setPassword("");
                ret.add(user);
            });
            model.addAttribute("users", ret);
            model.addAttribute("mode", true);
        }
        return "admin";
    }

    @PostMapping("/user")
    @ResponseBody
    public List<User> allUsers(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, HttpSession session) {
        Object userid = session.getAttribute("userid");
        if (userid == null || !userService.isAdmin(userid.toString())) return null;
        Page<User> users = userService.allUsersByPage(page == null ? 0 : page.intValue(), size == null ? PAGE_SIZE : size.intValue());
        List<User> ret = new ArrayList<>(PAGE_SIZE);
        users.forEach(user->{
            user.setPassword("");
            ret.add(user);
        });
        return ret;
    }

    @PostMapping("/appointment")
    @ResponseBody
    public Map<Date, List<String>> getAppointmentBetween(@RequestParam(value = "from", required = false) Long from,
            @RequestParam(value = "to", required = false) Long to, HttpSession session) {
        Object userid = session.getAttribute("userid");
        if (userid == null || !userService.isAdmin(userid.toString())) return null;
        if (from == null || to == null) {
            long time = System.currentTimeMillis();
            return appointmentService.getAppointmentBetween(new Date(time), new Date(time + week));
        }
        if (from > to) return null;
        return appointmentService.getAppointmentBetween(new Date(from), new Date(to));
    }

    @PostMapping("/cleanpwd")
    public boolean cleanpwd(@RequestParam("userid") String userid, @RequestParam("token") String token,
            HttpSession session) {
        Object t = session.getAttribute("userid");
        if (t == null || !userService.isAdmin(t.toString())) return false;
        return userService.cleanPwd(new User(userid, token));
    }

}

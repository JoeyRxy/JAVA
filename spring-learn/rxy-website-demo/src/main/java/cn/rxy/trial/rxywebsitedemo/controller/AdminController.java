package cn.rxy.trial.rxywebsitedemo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import cn.rxy.trial.rxywebsitedemo.util.Pair;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final int PAGE_SIZE = 20;
    private static final long week = 86400000L << 3;

    private final UserService userService;

    private final AppointmentService appointmentService;

    public AdminController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public String index(
            @RequestParam(value = "mode", required = false) Boolean isUserMode,
            @RequestParam(value = "date", required = false) Long date,
            @RequestParam(value = "page", required = false) Integer page,
            Model model, HttpSession session, HttpServletResponse resp) {
        Object userid = session.getAttribute("userid");
        if (userid == null) { 
            UserController.removeStatCookie(resp);
            return "/user";
        }
        if (!userService.isAdmin(userid.toString())) return "/";
        if (isUserMode == null || !isUserMode) {
            long time = (date == null ? System.currentTimeMillis() : date) + 1000;
            List<Pair<Date, List<String>>> appointments = appointmentService.getAppointmentBetween(new Date(time), new Date(time + week));
            List<Pair<Date, List<Pair<String, String>>>> list = new ArrayList<>(appointments.size() + 2);
            for (Pair<Date,List<String>> pair : appointments) {
                List<String> useridList = pair.getVal();
                List<Pair<String, String>> _t = new ArrayList<>(useridList.size() + 2);
                for (String _userid : useridList) {
                    User _u = userService.search(_userid);
                    _t.add(new Pair<>(_userid, (_u == null) ? "" : ("用户名：" + _u.getUsername())));
                }
                list.add(new Pair<>(pair.getKey(), _t));
            }
            model.addAttribute("appointmentPairs",
                    list);
            model.addAttribute("mode", false);
            model.addAttribute("times", List.of(AppointmentController.times));
            model.addAttribute("hidden", true);
        } else {
            int _page;
            if (page == null || page == -1) _page = 0;
            else if (page == -2) _page = -1;
            else _page = page;
            Page<User> users = userService.allUsersNotAdminByPage(_page, PAGE_SIZE);
            List<User> ret = new ArrayList<>(PAGE_SIZE);
            users.forEach(user->{
                user.setPassword("");
                ret.add(user);
            });
            model.addAttribute("users", ret);
            model.addAttribute("mode", true);
            model.addAttribute("hidden", false);
            model.addAttribute("page", page == null ? 0 : page);
        }
        return "admin";
    }

    @PostMapping("/user")
    @ResponseBody
    public List<User> allUsers(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, HttpSession session, HttpServletResponse resp) {
        Object userid = session.getAttribute("userid");
        if (userid == null) {
            UserController.removeStatCookie(resp);
            return null;
        } 
        if(!userService.isAdmin(userid.toString())) return null;
        Page<User> users = userService.allUsersByPage(page == null ? 0 : page, size == null ? PAGE_SIZE : size);
        List<User> ret = new ArrayList<>(PAGE_SIZE);
        users.forEach(user->{
            user.setPassword("");
            ret.add(user);
        });
        return ret;
    }

    @PostMapping("/appointment")
    @ResponseBody
    public List<Pair<Date, List<String>>> getAppointmentBetween(@RequestParam(value = "from", required = false) Long from,
            @RequestParam(value = "to", required = false) Long to, HttpSession session, HttpServletResponse resp) {
        Object userid = session.getAttribute("userid");
        if (userid == null) {
            UserController.removeStatCookie(resp);
            return null;
        } 
        if (!userService.isAdmin(userid.toString())) return null;
        if (from == null || to == null) {
            long time = System.currentTimeMillis();
            return appointmentService.getAppointmentBetween(new Date(time), new Date(time + week));
        }
        if (from > to) return null;
        return appointmentService.getAppointmentBetween(new Date(from), new Date(to));
    }

    @PostMapping("/cleanpwd")
    @ResponseBody
    public boolean cleanPwd(@RequestParam("userid") String userid, @RequestParam("token") String token,
            HttpSession session, HttpServletResponse resp) {
        Object t = session.getAttribute("userid");
        if (t == null) {
            UserController.removeStatCookie(resp);
            return false;
        } 
        if(!userService.isAdmin(t.toString())) return false;
        return userService.cleanPwd(new User(userid, token));
    }

    @PostMapping("/search")
    @ResponseBody
    public List<User> search(
        @RequestParam(value = "byid", defaultValue = "false") Boolean byid, 
        @RequestParam(value = "userid", required = false) String userid, 
        @RequestParam(value = "username", required = false) String username,
        @RequestParam(value = "exact", defaultValue = "false") Boolean exact, 
        HttpSession session, HttpServletResponse resp) {
        Object t = session.getAttribute("userid");
        if (t == null) {
            UserController.removeStatCookie(resp);
            return null;
        } 
        if(!userService.isAdmin(t.toString())) return null;
        if (!byid && username == null) return null;
        else if (byid && (userid == null || userid.equals(""))) return null;
        else return userService.search(byid, new User(username, userid, ""), exact);
    }

}

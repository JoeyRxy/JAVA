package cn.rxy.trial.rxywebsitedemo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;
import cn.rxy.trial.rxywebsitedemo.service.UserService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private static final long week = 86400000L * 7;
    private static final long day = 86400000L;
    public static final String[] times = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", };

    private final UserService userService;
    private final AppointmentService appointService;

    public AppointmentController(UserService userService, AppointmentService appointService) {
        this.userService = userService;
        this.appointService = appointService;
    }

    @GetMapping("")
    public String index(Model model,HttpSession session, HttpServletResponse resp) {
        Object attr = session.getAttribute("userid");
        if (attr == null) {
            UserController.removeStatCookie(resp);
            return "/user";
        }
        String userid = attr.toString();
        if (userService.isAdmin(userid)) return "/admin";
        long time = System.currentTimeMillis() + 1000;
        List<Date> dates = new ArrayList<>(4);
        dates.add(new Date(time));
        for (int i = 0; i < 6; ++i) {
            time += day;
            dates.add(new Date(time));
        }
        model.addAttribute("mydates", dates);
        model.addAttribute("times", List.of(times));
        // //
        // List<String> appointment;
        // if (date != null)
        //     appointment = appointService.getAppointment(new Date(date));
        // else
        //     appointment = appointService.getTodayAppointment();
        // List<MContainer> list = new ArrayList<>(20);
        // if (appointment != null) {
        //     int idx = 0, index = appointment.indexOf(userid);
        //     if (index == -1) { // cur user hasn't appointed
        //         for (String user : appointment) 
        //             list.add(new MContainer(times[idx++], !("".equals(user)), "预约"));
        //     } else { // cur user has already appointed
        //         for (int i = 0; i < 0xf; i++) 
        //             list.add(new MContainer(times[i], true, "已有预约"));
        //         list.set(index, new MContainer(times[index], false, "取消"));
        //         model.addAttribute("index", index);
        //     }
        // } else for (int i = 0; i < 0xf; i++) list.add(new MContainer(times[i], false, "预约"));
        // model.addAttribute("list", list);
        return "appointment";
    }

    @PostMapping(path = { "/check", "/refresh" })
    @ResponseBody
    public List<String> getAppointment(@RequestParam("date") long date, HttpSession session, HttpServletResponse resp) {
        Object attr = session.getAttribute("userid");
        if (attr == null) {
            UserController.removeStatCookie(resp);
            return null;
        }
        return appointService.getAppointment(new Date(date));
    }

    @PostMapping("/appoint")
    @ResponseBody
    public boolean appoint(@RequestParam("date") long date, @RequestParam("time") int time, @RequestParam(value = "cancel", required = false) Boolean cancel, HttpSession session, HttpServletResponse resp) {
        Object userid = session.getAttribute("userid");
        if (userid == null) {
            UserController.removeStatCookie(resp);
            return false;
        }
        if (cancel == null || !cancel)
            if (date >= System.currentTimeMillis() + week) return false;
            else return appointService.appoint(new Date(date), time, userid.toString());
        else return appointService.cancelAppointment4Userid(new Date(date), time, userid.toString());
    }

}

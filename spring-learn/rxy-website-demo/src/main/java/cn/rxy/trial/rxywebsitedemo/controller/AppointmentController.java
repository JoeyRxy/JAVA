package cn.rxy.trial.rxywebsitedemo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;
import cn.rxy.trial.rxywebsitedemo.util.MContainer;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private static final long day = 86400000L;
    private static final String[] times = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", };

    @Autowired
    private AppointmentService appointService;

    @GetMapping("")
    public String index(@RequestParam(value = "date", required = false) Long date, Model model, HttpSession session) {
        Object attr = session.getAttribute("userid");
        if (attr == null)
            return "/user";
        String userid = attr.toString();
        long time = System.currentTimeMillis() + 1000;
        List<Date> dates = new ArrayList<>(4);
        dates.add(new Date(time));
        for (int i = 0; i < 6; ++i) {
            time += day;
            dates.add(new Date(time));
        }
        model.addAttribute("mydates", dates);
        //
        List<String> appointment;
        if (date != null)
            appointment = appointService.getAppointment(new Date(date));
        else
            appointment = appointService.getTodayAppointment();
        List<MContainer> list = new ArrayList<>(20);
        if (appointment != null) {
            int idx = 0, index = appointment.indexOf(userid);
            if (index == -1) { // cur user hasn't appointed
                for (String user : appointment) 
                    list.add(new MContainer(times[idx++], !("".equals(user))));
            } else { // cur user has already appointed
                for (int i = 0; i < 0xf; i++) 
                    list.add(new MContainer(times[i], true));
                model.addAttribute("index", index);
            }
        } else for (int i = 0; i < 0xf; i++) list.add(new MContainer(times[i], false));
        model.addAttribute("list", list);
        return "appointment";
    }

    @PostMapping(path = { "/check", "/refresh" })
    @ResponseBody
    public List<String> getAppointment(@RequestParam("date") long date, HttpSession session) {
        if (session.getAttribute("userid") == null)
            return null;
        return appointService.getAppointment(new Date(date));
    }

    @PostMapping("/appoint")
    @ResponseBody
    public boolean appoint(@RequestParam("date") long date, @RequestParam("time") int time, HttpSession session) {
        Object userid = session.getAttribute("userid");
        if (userid == null)
            return false;
        return appointService.appoint(new Date(date), time, userid.toString());
    }

}

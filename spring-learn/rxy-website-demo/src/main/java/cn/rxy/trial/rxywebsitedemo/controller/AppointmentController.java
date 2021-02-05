package cn.rxy.trial.rxywebsitedemo.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointService;

    @GetMapping("")
    public String index() {
        return "appointment";
    }

    @PostMapping(path = { "check", "refresh" })
    @ResponseBody
    public List<String> getAppointment(@RequestParam("date") long date, HttpServletRequest req) {
        Object userid = req.getSession().getAttribute("userid");
        if (userid == null) return null;
        List<String> appointment = appointService.getAppointment(new Date(date));
        return appointment;
    }

    @PostMapping("appoint")
    public boolean appoint(@RequestParam("data") long date, @RequestParam("time") int time, HttpServletRequest req) {
        Object userid = req.getSession().getAttribute("userid");
        if (userid == null)
            return false;
        return appointService.appoint(new Date(date), time, userid.toString());
    }

}

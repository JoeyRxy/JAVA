package cn.rxy.trial.rxywebsitedemo.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/bus")
public class BusLinesController {

    @GetMapping(path = "")
    public String bus() {
        return "bus-line";
    }

}

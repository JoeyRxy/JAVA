package cn.rxy.trial.rxywebsitedemo.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/map")
public class TreeBasicController {

    @GetMapping(path = "")
    public String path() {
        return "tree-basic";
    }

    @ResponseBody
    @GetMapping(path = "/flare")
    public String flare() throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("data/flare.json"), StandardCharsets.UTF_8);
        char[] cbuf = new char[2048 << 4];
        int len;
        while ((len = reader.read(cbuf)) != -1)
            builder.append(cbuf, 0, len);
        reader.close();
        //
        return builder.toString();
    }
}

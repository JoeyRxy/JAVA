package mine.learn.springboot.springconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mine.learn.springboot.springconfig.bean.Point;

/**
 * HelloController
 */
@RestController
public class HelloController {
    @Autowired
    private Point point;

    @RequestMapping("/config")
    public String name() {
        return "Hello\n" + point;
    }
}
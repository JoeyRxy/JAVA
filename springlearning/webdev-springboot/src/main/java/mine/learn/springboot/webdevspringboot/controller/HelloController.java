package mine.learn.springboot.webdevspringboot.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloController
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String contrl() {
        return "Hello";
    }

    @RequestMapping("/success")
    public String sucess(Map<String, Object> map) {
        map.put("hello", "nihao");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "success";
    }
}
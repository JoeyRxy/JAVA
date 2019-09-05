package mine.learn.spring.springboot2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuickController
 */
@RestController
public class QuickController {

    @RequestMapping("/quick2")
    public String quik() {
        return "Hello Spring Boot";
    }
}
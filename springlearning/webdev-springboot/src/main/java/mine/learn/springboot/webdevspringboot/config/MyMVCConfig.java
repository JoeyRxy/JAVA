package mine.learn.springboot.webdevspringboot.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MyMVCConfig
 */
public class MyMVCConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addViewControllers(registry);
    }
}
package cn.rxy.trial.rxywebsitedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.service.UserService;

@Component
public class InitRunner implements ApplicationRunner {

    @Autowired
    private UserService service;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("rxy", "rxy007", "ww5439qw3256y");
        admin.setAdmin(true);
        service.register(admin);
        service.register(new User("Test User 1","test1","test"));
        service.register(new User("Test User 2","test2","test"));
        service.register(new User("Test User 3","test3","test"));
        service.register(new User("Test User 4","test4","test"));
    }
    
}

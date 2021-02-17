package cn.rxy.trial.rxywebsitedemo;

import java.sql.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;
import cn.rxy.trial.rxywebsitedemo.service.UserService;

@Component
public class InitRunner implements ApplicationRunner {

    @Autowired
    private UserService userService;

    // @Autowired
    // private AppointmentService appointService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("rxy", "rxy007", "ws543k9qp026yt");
        admin.setAdmin(true);
        userService.register(admin);
        // long time = System.currentTimeMillis() + 100;
        // Random r = new Random(time);
        // for (int i = 0; i < 100; ++i) 
        //     userService.register(new User("Test User " + r.nextInt(40), "test" + i, "test"));
        
        // for (int i = 0; i < 100; ++i) 
        //     appointService.appoint(new Date(time + (r.nextInt(14) - 6) * 86400000L), r.nextInt(16), "test" + r.nextInt(100));
    }
    
}

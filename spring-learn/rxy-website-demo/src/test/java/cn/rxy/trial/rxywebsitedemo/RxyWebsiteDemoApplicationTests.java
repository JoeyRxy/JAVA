package cn.rxy.trial.rxywebsitedemo;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.repository.UserRepo;
import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;
import cn.rxy.trial.rxywebsitedemo.service.UserService;

@SpringBootTest
class RxyWebsiteDemoApplicationTests {

    @Autowired
    private AppointmentService appointService;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private UserService userService;

    // private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testAppointment() throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        List<String> appointment = appointService.getAppointment(date);
        System.out.println(appointment);
        int i = 0;
        for (String userid : appointment) {
            if (!userid.equals("")) {
                boolean canceled = appointService.cancelAppointment4Userid(date, i, userid);
                System.out.println(userid + " : canceled? " + canceled);
            }
            ++i;
        }
        List<String> appointment2 = appointService.getAppointment(date);
        System.out.println(appointment2);
    }

    @Test
    public void userTest() {
        User user;
        System.out.println(
                (user = userService.login(new User("rxy007", "test1234"))) == null ? "failed!" : user.getUserid());
        System.out.println(userService.isAdmin("rxy007"));
        List<User> list_page2 = userService.allUsersNotAdminByPage(0, 30).toList();
        System.out.println(list_page2.size());
        int idx = -1;
        for (User user2 : list_page2) {
            System.out.println((++idx) + ": " + user2);
        }
        System.out.println("=========================================");
        List<User> list_page4 = userService.allUsersNotAdminByPage(3, 30).toList();
        System.out.println(list_page4.size());
        idx = -1;
        for (User user2 : list_page4) {
            System.out.println((++idx) + ": " + user2);
        }
    }

    @Test
    public void testUserCount() {
        long countByAdminFalse = userRepo.countByAdminFalse();
        System.out.println(countByAdminFalse);
    }

    @Test
    public void testSearch() {
        List<User> users = userRepo.findAllByUseridContainingAndAdminFalse("est6");
        System.out.println(users);
        System.out.println("Test User 4:\n\t" + userRepo.findAllByUsernameContainingAndAdminFalse("User 4"));
        System.out.println("Test User 5:\n\t" + userRepo.findAllByUsernameContainingAndAdminFalse("User 5"));
        System.out.println("Test User 6:\n\t" + userRepo.findAllByUsernameContainingAndAdminFalse("User 6"));
    }

}

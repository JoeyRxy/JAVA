package cn.rxy.trial.rxywebsitedemo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.rxy.trial.rxywebsitedemo.service.AppointmentService;

@SpringBootTest
class RxyWebsiteDemoApplicationTests {

    @Autowired
    private AppointmentService appointService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testAppointment() throws ParseException {
        Date date1 = new Date(sdf.parse("2020-1-1").getTime());
        Date date2 = new Date(sdf.parse("2020-2-1").getTime());
        Date date3 = new Date(sdf.parse("2020-2-3").getTime());
        Date date4 = new Date(sdf.parse("2020-2-10").getTime());
        //
        String userid1 = "dfajierjf";
        String userid2 = "ferutg54";
        String userid3 = "487gwse";
        String userid4 = "f6egoe";
        String userid5 = "f47885tgha";
        //
        assert (appointService.appoint(date1, 3, userid1));
        assert (appointService.appoint(date1, 2, userid2));
        assert (appointService.appoint(date2, 3, userid1));
        assert (appointService.appoint(date2, 2, userid3));
        assert (appointService.appoint(date3, 11, userid3));
        assert (appointService.appoint(date3, 15, userid1));
        assert (appointService.appoint(date3, 10, userid4));
        assert (appointService.appoint(date4, 14, userid4));
        assert (appointService.appoint(date4, 1, userid1));
        assert (appointService.appoint(date4, 3, userid2));
        assert (appointService.appoint(date4, 5, userid3));
        assert (appointService.appoint(date4, 15, userid5));
        assert (appointService.appointToday(3, userid2));
        assert (appointService.appointToday(2, userid1));
        assert (appointService.appointToday(7, userid3));
        //
        System.out.println(appointService.getAppointment(date1));
        System.out.println(appointService.getTodayAppointment());
        System.out.println(appointService.getAppointment(date4));
        System.out.println(appointService.getAppointmentBetween(date1, date4));
    }

}

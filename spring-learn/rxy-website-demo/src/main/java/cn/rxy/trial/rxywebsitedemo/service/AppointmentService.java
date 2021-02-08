package cn.rxy.trial.rxywebsitedemo.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rxy.trial.rxywebsitedemo.entity.Appointment;
import cn.rxy.trial.rxywebsitedemo.repository.AppointmentRepo;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    public final List<String> getTodayAppointment() {
        return getAppointment(new Date(System.currentTimeMillis()));
    }

    public final List<String> getAppointment(Date date) {
        Appointment t = appointmentRepo.findByDate(date);
        if (t == null)
            return null;
        return List.of(t.getUser0id(), t.getUser1id(), t.getUser2id(), t.getUser3id(), t.getUser4id(), t.getUser5id(),
                t.getUser6id(), t.getUser7id(), t.getUser8id(), t.getUser9id(), t.getUseraid(), t.getUserbid(),
                t.getUsercid(), t.getUserdid(), t.getUsereid(), t.getUserfid());
    }

    public final Map<Date, List<String>> getAppointmentBetween(Date from, Date to) {
        if (from.after(to))
            return null;
        List<Appointment> list = appointmentRepo.findAllByDateBetween(from, to);
        if (list == null || list.size() == 0)
            return null;
        Map<Date, List<String>> ret = new HashMap<>();
        for (Appointment t : list) {
            ret.put(t.getDate(), List.of(t.getUser0id(), t.getUser1id(), t.getUser2id(), t.getUser3id(), t.getUser4id(),
                    t.getUser5id(), t.getUser6id(), t.getUser7id(), t.getUser8id(), t.getUser9id(), t.getUseraid(),
                    t.getUserbid(), t.getUsercid(), t.getUserdid(), t.getUsereid(), t.getUserfid()));
        }
        return ret;
    }

    public final int getTodayAppointment4User(String userid) {
        if (userid == null || userid.equals("")) return -1;
        int time = 0;
        for (String str : getTodayAppointment()) {
            if (userid.equals(str))
                return time;
            ++time;
        }
        return -1;
    }

    public final int getAppointment4User(Date date, String userid) {
        if (userid == null || userid.equals("")) return -1;
        int time = 0;
        for (String str : getAppointment(date)) {
            if (userid.equals(str))
                return time;
            ++time;
        }
        return -1;
    }

    public final Map<Date, Integer> getAppointment4UserBetween(Date from, Date to, String userid) {
        if (userid == null || userid.equals("")) return null;
        Map<Date, List<String>> appointments = getAppointmentBetween(from, to);
        Map<Date, Integer> ret = new HashMap<>();
        for (Entry<Date, List<String>> entry : appointments.entrySet()) {
            int time = 0;
            boolean found = false;
            for (String id : entry.getValue()) {
                if (userid.equals(id)) {
                    found = true;
                    break;
                }
                ++time;
            }
            ret.put(entry.getKey(), found ? time : -1);
        }
        return ret;
    }

    public final boolean appointToday(int time, String userid) {
        return appoint(new Date(System.currentTimeMillis()),time, userid);
    }

    public final boolean appoint(Date date, int time, String userid) {
        Appointment t = appointmentRepo.findByDate(date);
        if (userid == null) return false;
        if (t == null) {
            appointmentRepo.save(new Appointment(date, time, userid));
            return true;
        }
        if ( // ensure same user can appoint once one day
            userid.equals("") ||
            userid.equals(t.getUser0id()) ||
            userid.equals(t.getUser1id()) ||
            userid.equals(t.getUser2id()) ||
            userid.equals(t.getUser3id()) ||
            userid.equals(t.getUser4id()) ||
            userid.equals(t.getUser5id()) ||
            userid.equals(t.getUser6id()) ||
            userid.equals(t.getUser7id()) ||
            userid.equals(t.getUser8id()) ||
            userid.equals(t.getUser9id()) ||
            userid.equals(t.getUseraid()) ||
            userid.equals(t.getUserbid()) ||
            userid.equals(t.getUsercid()) ||
            userid.equals(t.getUserdid()) ||
            userid.equals(t.getUsereid()) ||
            userid.equals(t.getUserfid())
        ) return false; 
        switch (time) {
            case 0:
                if (!"".equals(t.getUser0id())) return false;
                appointmentRepo.updateUser0(date, userid);
                return true;
            case 1:
                if (!"".equals(t.getUser1id())) return false;
                appointmentRepo.updateUser1(date, userid);
                return true;
            case 2:
                if (!"".equals(t.getUser2id())) return false;
                appointmentRepo.updateUser2(date, userid);
                return true;
            case 3:
                if (!"".equals(t.getUser3id())) return false;
                appointmentRepo.updateUser3(date, userid);
                return true;
            case 4:
                if (!"".equals(t.getUser4id())) return false;
                appointmentRepo.updateUser4(date, userid);
                return true;
            case 5:
                if (!"".equals(t.getUser5id())) return false;
                appointmentRepo.updateUser5(date, userid);
                return true;
            case 6:
                if (!"".equals(t.getUser6id())) return false;
                appointmentRepo.updateUser6(date, userid);
                return true;
            case 7:
                if (!"".equals(t.getUser7id())) return false;
                appointmentRepo.updateUser7(date, userid);
                return true;
            case 8:
                if (!"".equals(t.getUser8id())) return false;
                appointmentRepo.updateUser8(date, userid);
                return true;
            case 9:
                if (!"".equals(t.getUser9id())) return false;
                appointmentRepo.updateUser9(date, userid);
                return true;
            case 0xa:
                if (!"".equals(t.getUseraid())) return false;
                appointmentRepo.updateUsera(date, userid);
                return true;
            case 0xb:
                if (!"".equals(t.getUserbid())) return false;
                appointmentRepo.updateUserb(date, userid);
                return true;
            case 0xc:
                if (!"".equals(t.getUsercid())) return false;
                appointmentRepo.updateUserc(date, userid);
                return true;
            case 0xd:
                if (!"".equals(t.getUserdid())) return false;
                appointmentRepo.updateUserd(date, userid);
                return true;
            case 0xe:
                if (!"".equals(t.getUsereid())) return false;
                appointmentRepo.updateUsere(date, userid);
                return true;
            case 0xf:
                if (!"".equals(t.getUserfid())) return false;
                appointmentRepo.updateUserf(date, userid);
                return true;
            default:
                return false;
        }
    }
    
}

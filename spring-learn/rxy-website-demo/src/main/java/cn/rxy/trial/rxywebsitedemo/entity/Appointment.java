package cn.rxy.trial.rxywebsitedemo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Appointment {

    private int id;
    private Date date;

    private String user0id = "";
    private String user1id = "";
    private String user2id = "";
    private String user3id = "";
    private String user4id = "";
    private String user5id = "";
    private String user6id = "";
    private String user7id = "";
    private String user8id = "";
    private String user9id = "";
    private String useraid = "";
    private String userbid = "";
    private String usercid = "";
    private String userdid = "";
    private String usereid = "";
    private String userfid = "";

    public Appointment() {
    }

    public Appointment(Date date, int time, String userid) {
        this.date = date;
        switch (time) {
            case 0x0: user0id = userid;
                break;
            case 0x1: user1id = userid;
                break;
            case 0x2: user2id = userid;
                break;
            case 0x3: user3id = userid;
                break;
            case 0x4: user4id = userid;
                break;
            case 0x5: user5id = userid;
                break;
            case 0x6: user6id = userid;
                break;
            case 0x7: user7id = userid;
                break;
            case 0x8: user8id = userid;
                break;
            case 0x9: user9id = userid;
                break;
            case 0xa: useraid = userid;
                break;
            case 0xb: userbid = userid;
                break;
            case 0xc: usercid = userid;
                break;
            case 0xd: userdid = userid;
                break;
            case 0xe: usereid = userid;
                break;
            case 0xf: userfid = userid;
                break;
            default:
                break;
        }
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser0id() {
        return user0id;
    }

    public void setUser0id(String user0id) {
        this.user0id = user0id;
    }

    public String getUser1id() {
        return user1id;
    }

    public void setUser1id(String user1id) {
        this.user1id = user1id;
    }

    public String getUser2id() {
        return user2id;
    }

    public void setUser2id(String user2id) {
        this.user2id = user2id;
    }

    public String getUser3id() {
        return user3id;
    }

    public void setUser3id(String user3id) {
        this.user3id = user3id;
    }

    public String getUser4id() {
        return user4id;
    }

    public void setUser4id(String user4id) {
        this.user4id = user4id;
    }

    public String getUser5id() {
        return user5id;
    }

    public void setUser5id(String user5id) {
        this.user5id = user5id;
    }

    public String getUser6id() {
        return user6id;
    }

    public void setUser6id(String user6id) {
        this.user6id = user6id;
    }

    public String getUser7id() {
        return user7id;
    }

    public void setUser7id(String user7id) {
        this.user7id = user7id;
    }

    public String getUser8id() {
        return user8id;
    }

    public void setUser8id(String user8id) {
        this.user8id = user8id;
    }

    public String getUser9id() {
        return user9id;
    }

    public void setUser9id(String user9id) {
        this.user9id = user9id;
    }

    public String getUseraid() {
        return useraid;
    }

    public void setUseraid(String useraid) {
        this.useraid = useraid;
    }

    public String getUserbid() {
        return userbid;
    }

    public void setUserbid(String userbid) {
        this.userbid = userbid;
    }

    public String getUsercid() {
        return usercid;
    }

    public void setUsercid(String usercid) {
        this.usercid = usercid;
    }

    public String getUserdid() {
        return userdid;
    }

    public void setUserdid(String userdid) {
        this.userdid = userdid;
    }

    public String getUsereid() {
        return usereid;
    }

    public void setUsereid(String usereid) {
        this.usereid = usereid;
    }

    public String getUserfid() {
        return userfid;
    }

    public void setUserfid(String userfid) {
        this.userfid = userfid;
    }

}

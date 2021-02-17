package cn.rxy.trial.rxywebsitedemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    private long id;
    private String username;
    private String userid;
    private String password;
    private boolean admin;
    private String note = "";

    public User() {
    }

    @Column(length = 1023)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    public User(String username, String userid, String password) {
        this.username = username;
        this.userid = userid;
        this.password = password;
    }

    public User(String userid, String password) {
        username = "";
        this.userid = userid;
        this.password = password;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 61, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 50, unique = true, nullable = false)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", userid=" + userid + "]";
    }
    

}

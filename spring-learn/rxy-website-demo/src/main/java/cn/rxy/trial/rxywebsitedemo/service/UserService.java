package cn.rxy.trial.rxywebsitedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.repository.UserRepo;
import cn.rxy.trial.rxywebsitedemo.util.BCrypt;

@Service
public class UserService {

    // private final static int log_rounds = 7;

    @Autowired
    private UserRepo userRepo;

    public User login(User user) {
        User user_db = userRepo.findByUserid(user.getUserid());
        if (user_db == null || !BCrypt.checkpw(user.getPassword(), user_db.getPassword()))
            return null;
        return user_db;
    }

    public boolean register(User user) {
        if (userRepo.existsByUserid(user.getUserid()))
            return false;
        String pwd = user.getPassword();
        if (pwd == null || pwd.equals(""))
            return false;
        user.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt(7)));
        // user.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt(log_rounds)));
        userRepo.save(user);
        return true;
    }

    /**
     * 管理员将该用户对应的密码设为特定的密码，之后告诉该用户这个密码，让该用户先使用该密码进行验证，然后修改密码
     * 
     * @see {@link #resetPwd(User)}
     * @param user
     * @return
     */
    public boolean cleanPwd(User user) {
        User user_db = userRepo.findByUserid(user.getUserid());
        if (user_db == null)
            return false;
        user_db.setPassword(user.getPassword());
        userRepo.save(user_db);
        return true;
    }

    public boolean resetPwd(User user, String verifiedCode) {
        User user_db = userRepo.findByUserid(user.getUserid());
        if (user_db.getPassword().equals(verifiedCode))
            return false;
        String pwd = user_db.getPassword();
        if (pwd == null || pwd.equals(""))
            return false;
        user_db.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt(7)));
        userRepo.save(user);
        return true;
    }

    public boolean isAdmin(String userid) {
        User user = userRepo.findByUserid(userid);
        if (user == null)
            return false;
        return user.isAdmin();
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

}

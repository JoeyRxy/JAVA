package cn.rxy.trial.rxywebsitedemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cn.rxy.trial.rxywebsitedemo.entity.User;
import cn.rxy.trial.rxywebsitedemo.repository.UserRepo;
import cn.rxy.trial.rxywebsitedemo.util.BCrypt;

@Service
public class UserService {

    // private final static int log_rounds = 7;

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

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
     * @see {@link #resetPwd(User, String)}
     * @param user
     * @return boolean
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
        user_db.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt(17)));
        userRepo.save(user);
        return true;
    }

    public boolean isAdmin(String userid) {
        User user = userRepo.findByUserid(userid);
        if (user == null)
            return false;
        return user.isAdmin();
    }

    public Page<User> allUsersByPage(int page, int size) {
        if (size > 0)
            if (page >= 0)
                return userRepo.findAll(PageRequest.of(page, size));
            else {
                long count = userRepo.count();
                int lastPage = (int) (count / size);
                if (count == ((long) lastPage * size))
                    --lastPage;
                return userRepo.findAll(PageRequest.of(lastPage, size));
            }
        return null;
    }

    public Page<User> allUsersNotAdminByPage(int page, int size) {
        if (size > 0)
            if (page >= 0)
                return userRepo.findAllByAdminFalse(PageRequest.of(page, size));
            else {
                long count = userRepo.countByAdminFalse();
                int lastPage = (int) (count / size);
                if (count == ((long) lastPage * size))
                    --lastPage;
                return userRepo.findAllByAdminFalse(PageRequest.of(lastPage, size));
                // return userRepo.findAllByAdminFalse(PageRequest.of(0, size,
                // Sort.by("id").descending()));
            }
        return null;
    }

    public final long count(boolean admin) {
        if (!admin)
            return userRepo.countByAdminFalse();
        else
            return userRepo.count();
    }

    public final User search(String userid) {
        return userRepo.findByUseridAndAdminFalse(userid);
    }

    public final List<User> search(boolean byid, User user, boolean exact) {
        if (!exact) {
            if (!byid) {
                List<User> users = userRepo.findAllByUsernameContainingAndAdminFalse(user.getUsername());
                users.forEach(t -> t.setPassword(""));
                return users;
            } else if (byid) {
                List<User> users = userRepo.findAllByUseridContainingAndAdminFalse(user.getUserid());
                users.forEach(t -> t.setPassword(""));
                return users;
            } else return null;
        } else {
            if (!byid) {
                List<User> users = userRepo.findAllByUsernameAndAdminFalse(user.getUsername());
                users.forEach(t -> t.setPassword(""));
                return users;
            } else if (byid) {
                User u = userRepo.findByUseridAndAdminFalse(user.getUserid());
                u.setPassword("");
                return List.of(u);
            } else return null;
        }
    }

}

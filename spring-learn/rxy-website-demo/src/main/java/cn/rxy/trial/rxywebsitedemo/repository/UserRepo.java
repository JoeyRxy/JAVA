package cn.rxy.trial.rxywebsitedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.rxy.trial.rxywebsitedemo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
    boolean existsByUserid(String userid);

    String findPasswordByUserid(String userid);

    User findByUserid(String userid);
    
}

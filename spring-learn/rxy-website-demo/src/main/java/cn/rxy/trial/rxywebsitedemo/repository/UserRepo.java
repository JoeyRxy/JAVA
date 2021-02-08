package cn.rxy.trial.rxywebsitedemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.rxy.trial.rxywebsitedemo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUserid(String userid);

    User findByUserid(String userid);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByAdminFalse(Pageable pageble);

}

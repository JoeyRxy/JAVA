package cn.rxy.trial.rxywebsitedemo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.rxy.trial.rxywebsitedemo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUserid(String userid);

    User findByUserid(String userid);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByAdminFalse(Pageable pageable);

    long countByAdminFalse();

    List<User> findAllByUsernameContainingAndAdminFalse(String usernamePart);

    List<User> findAllByUsernameAndAdminFalse(String username);

    List<User> findAllByUseridContainingAndAdminFalse(String useridPart);

    User findByUseridAndAdminFalse(String userid);
    
}

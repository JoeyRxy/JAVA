package cn.rxy.trial.rxywebsitedemo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.rxy.trial.rxywebsitedemo.entity.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment u ORDER BY u.id DESC LIMIT 1", nativeQuery = true)
    Appointment getTodayAppointment();

    Appointment findByDate(Date date);

    List<Appointment> findAllByDateBetween(Date from, Date to);

    List<Appointment> findAllByDateGreaterThanEqual(Date from);

    Page<Appointment> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user0id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser0(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user1id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser1(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user2id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser2(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user3id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser3(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user4id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser4(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user5id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser5(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user6id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser6(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user7id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser7(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user8id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser8(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.user9id = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUser9(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.useraid = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUsera(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.userbid = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUserb(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.usercid = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUserc(Date date, String userid);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.userdid = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUserd(Date date, String userid);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.usereid = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUsere(Date date, String userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appointment u SET u.userfid = ?2 WHERE u.date = ?1", nativeQuery = true)
    void updateUserf(Date date, String userid);
    
}

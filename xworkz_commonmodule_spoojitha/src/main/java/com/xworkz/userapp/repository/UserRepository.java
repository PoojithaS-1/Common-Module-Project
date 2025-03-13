package com.xworkz.userapp.repository;

import com.xworkz.userapp.entity.UserEntity;

import java.time.LocalDateTime;

public interface UserRepository {
    boolean saveUser(UserEntity entity);
    UserEntity fetchPasswordByEmail(String email);
    UserEntity findByEmail(String email);
    int updateByEmail(String email, String name, String phoneNumber, String location, Integer age, String password);
    void updateFailedAttempts(String email, int attempts);
    void lockAccount(String email, LocalDateTime lockTime);
    void resetAttempts(String email);
    void updatePassword(String email, String newPassword);

    UserEntity findByPhoneNumber(String phoneNumber);
    UserEntity findByName(String name);

    void deleteByEmail(String email);
}

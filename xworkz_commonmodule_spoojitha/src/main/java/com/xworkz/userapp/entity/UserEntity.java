package com.xworkz.userapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_info")
@NamedQuery(name = "UserEntity.updateFailedAttempts", query = "UPDATE UserEntity u SET u.failedAttempts = :attempts WHERE u.email = :email")
@NamedQuery(name = "getPasswordByEmail", query = "SELECT entity FROM UserEntity entity WHERE entity.email = :email")
@NamedQuery(name = "UserEntity.updateByEmail", query = "UPDATE UserEntity u SET u.name = :name, u.phoneNumber = :phoneNumber,  u.age = :age, u.password = :password WHERE u.email = :email")
@NamedQuery(name = "UserEntity.deleteByEmail", query = "DELETE FROM UserEntity u WHERE u.email = :email")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String location;
    private String gender;
    private String dOB;
    private Integer age;

    private int failedAttempts = 0;
    private boolean accountLocked = false;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime lockTime;


}

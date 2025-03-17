package com.xworkz.userapp.entity;

import com.xworkz.userapp.constant.LocationEnum;
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
@NamedQueries({
        @NamedQuery(name = "UserEntity.getPasswordByEmail",
                query = "SELECT u FROM UserEntity u WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.findByEmail",
                query = "SELECT u FROM UserEntity u WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.updateByEmail",
                query = "UPDATE UserEntity u SET u.name = :name, u.phoneNumber = :phoneNumber, " +
                        "u.location = :location, u.age = :age, u.password = :password WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.updateFailedAttempts",
                query = "UPDATE UserEntity u SET u.failedAttempts = :attempts WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.lockAccount",
                query = "UPDATE UserEntity u SET u.accountLocked = true, u.lockTime = :lockTime WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.resetAttempts",
                query = "UPDATE UserEntity u SET u.failedAttempts = 0, u.accountLocked = false, u.lockTime = null WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.updatePassword",
                query = "UPDATE UserEntity u SET u.password = :password WHERE u.email = :email"),

        @NamedQuery(name = "UserEntity.findByPhoneNumber",
                query = "SELECT u FROM UserEntity u WHERE u.phoneNumber = :phoneNumber"),

        @NamedQuery(name = "UserEntity.findByName",
                query = "SELECT u FROM UserEntity u WHERE u.name = :name"),

        @NamedQuery(name = "UserEntity.deleteByEmail",
                query = "DELETE FROM UserEntity u WHERE u.email = :email")
})

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private LocationEnum location;
    private String gender;
    private String dOB;
    private Integer age;

    private int failedAttempts = 0;
    private boolean accountLocked = false;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime lockTime;


}

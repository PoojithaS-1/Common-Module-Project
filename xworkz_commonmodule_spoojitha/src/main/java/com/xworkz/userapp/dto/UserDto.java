package com.xworkz.userapp.dto;

import com.xworkz.userapp.entity.LocationEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class UserDto {
        private Integer id;
//        @Null
//        @Size(min = 3 , max = 5, message = "UserName should be greater than 3 and less than 5")
        private String name;
//        @Email
        private String email;
        private String password;
        private transient String confirmPassword;
        private String phoneNumber;
        private String location;
        private String gender;
        private String dOB;
        private Integer age;
        private int failedAttempts;

}
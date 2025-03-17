package com.xworkz.userapp.dto;

import com.xworkz.userapp.constant.LocationEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
        private LocationEnum location;
        private String gender;
        private String dOB;
        private Integer age;
        private int failedAttempts;

}
package sungil.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhone;
    private Gender userGender;
    private Date createAt;
}

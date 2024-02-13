package sungil.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairRegistration {
    private Integer repairRegistrationIdx;
    private String customerUserId;
    private String problemTitle;
    private String problemComment;
    private Date createAt;
}

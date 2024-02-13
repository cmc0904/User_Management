package sungil.management.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class RepairView {
//    SELECT r.idx, r.customerUserId, r.problemTitle, r.problemComment, r.createAt, u.name, u.address, u.email, u.phone, u.gender, rr.adminId, rr.visitDate
    private int idx;
    private String customerUserId;
    private String problemTitle;
    private String problemComment;
    private LocalDateTime createAt;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String gender;
    private String adminId;
    private LocalDateTime visitDate;
}

package sungil.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairResult {
    private int idx;
    private int repairIdx;
    private String adminId;
    private LocalDateTime visitDate;

    public RepairResult(int repairIdx, String adminId, LocalDateTime visitDate) {
        this.repairIdx = repairIdx;
        this.adminId = adminId;
        this.visitDate = visitDate;
    }
}

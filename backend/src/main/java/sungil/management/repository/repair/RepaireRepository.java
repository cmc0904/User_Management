package sungil.management.repository.repair;

import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;

import java.util.List;

public interface RepaireRepository {

    // 접수
    void insertRepaire(RepairRegistration repairRegistration);

    // 현황 확인
    List<RepairView> getAllRepairStatus();

    // 현황 확인
    List<RepairView> getRepairStatusByUserId(String userId);

    // 접수 처리
    void insertRepairResult(RepairResult repairResult);


}

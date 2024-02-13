package sungil.management.service.repair;

import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;

import java.util.List;
import java.util.Map;

public interface RepairService {

    Map<String, String> registrationRepair(RepairRegistration repairRegistration);

    List<RepairView> getAllRepairStatus();
    List<RepairView> getRepairStatusByUserId(String userId);
    Map<String, String> processRegistration(RepairResult repairResult);
}

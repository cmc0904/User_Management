package sungil.management.service.repair;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairView;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepairServiceImplTest {

    private final RepairService repairService;

    @Autowired
    public RepairServiceImplTest(RepairService repairService) {
        this.repairService = repairService;
    }

    @Test
    void registrationRepair() {
        RepairRegistration repairRegistration = new RepairRegistration(null, "anscks2350U", "as", "as", new Date(2024, 2, 13));

        repairService.registrationRepair(repairRegistration);

        Assertions.assertThat(repairRegistration.getRepairRegistrationIdx()).isNotNull();

    }

    @Test
    void getAllRepairStatus() {
        Assertions.assertThat(repairService.getAllRepairStatus()).isNotNull();
    }

    @Test
    void getRepairStatusByUserId() {
        RepairRegistration repairRegistration = new RepairRegistration(null, "anscks2350U", "as", "as", new Date(2024, 2, 13));

        List<RepairView> repairViews = repairService.getRepairStatusByUserId("anscks2350U");

        Assertions.assertThat(repairViews).isNotNull();

    }

}
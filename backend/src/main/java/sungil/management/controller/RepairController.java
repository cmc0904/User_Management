package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.RepairRegistration;
import sungil.management.domain.RepairResult;
import sungil.management.domain.RepairView;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.service.repair.RepairService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repair")
public class RepairController {

    private final JwtTokenValidator jwtTokenValidator;
    private final RepairService repairService;

    @Autowired
    public RepairController(JwtTokenValidator jwtTokenValidator, RepairService repairService) {
        this.jwtTokenValidator = jwtTokenValidator;
        this.repairService = repairService;
    }


    @PostMapping("/registrationrepair")
    public ResponseEntity<Map<String, String>> registration(@RequestHeader("Authorization") String authorizationHeader, @RequestBody RepairRegistration repairRegistration) {
        repairRegistration.setCustomerUserId(jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)));
        return ResponseEntity.ok(repairService.registrationRepair(repairRegistration));
    }

    @GetMapping("/getRepairStatus")
    public ResponseEntity<List<RepairView>> getRepairStatus() {
        return ResponseEntity.ok(repairService.getAllRepairStatus());
    }


    @PostMapping("/processrepair")
    public ResponseEntity<Map<String, String>> proccessRegistration(@RequestBody RepairResult repairResult) {
        return ResponseEntity.ok(repairService.processRegistration(repairResult));
    }

    @GetMapping("/getRepairStatusByUserId")
    public ResponseEntity<List<RepairView>> getRepairStatusBy(@RequestHeader("Authorization") String authorizationHeader, String userId) {
        return ResponseEntity.ok(repairService.getRepairStatusByUserId(userId == null ? jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)) : userId));
    }
}

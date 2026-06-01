package war_monitor.controller;


import org.springframework.web.bind.annotation.*;
import war_monitor.entity.Facility;
import war_monitor.entity.FacilityStatus;
import war_monitor.service.FacilityService;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    // 전체 목록
    @GetMapping
    public List<Facility> getAllFacilities(){
        return facilityService.getAllFacilities();
    }

    // id 하나로 조회
    @GetMapping("/{id}")
    public Facility getFacility(@PathVariable Long id){
        return facilityService.getFacilityById(id);
    }

    @PatchMapping("/{id}/status")
    public Facility updateFacilityStatus(@PathVariable Long id, @RequestBody FacilityStatus status){
        return facilityService.updateFacilityStatus(id, status);
    }
}

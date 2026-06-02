package war_monitor.controller;

import org.springframework.web.bind.annotation.*;
import war_monitor.dto.VehicleResponseDto;
import war_monitor.dto.VehicleStatusUpdateRequest;
import war_monitor.entity.VehicleStatus;
import war_monitor.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleResponseDto> getAllVehicles() {
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public VehicleResponseDto getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PatchMapping("/{id}/status")
    public VehicleResponseDto updateVehiclesStatus(@PathVariable Long id, @RequestBody VehicleStatusUpdateRequest request) {
        return vehicleService.updateVehicleStatus(id, request.getVehicleStatus());
    }
}

package war_monitor.controller;

import org.springframework.web.bind.annotation.*;
import war_monitor.entity.Vehicle;
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
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable Long id){
        return vehicleService.getVehicleById(id);
    }

    @PatchMapping("/{id}/status")
    public Vehicle updateVehiclesStatus(@PathVariable Long id , @RequestBody VehicleStatus status){
        return vehicleService.updateVehicleStatus(id , status);
    }
}

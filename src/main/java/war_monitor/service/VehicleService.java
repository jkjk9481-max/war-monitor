package war_monitor.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import war_monitor.entity.Vehicle;
import war_monitor.entity.VehicleStatus;
import war_monitor.exception.NotFoundException;
import war_monitor.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }


    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 이동수단이 없습니다"));
    }

    @Transactional
    public Vehicle updateVehicleStatus(Long id , VehicleStatus status){
        Vehicle vehicle = getVehicleById(id);
        vehicle.updateStatus(status);
        return vehicleRepository.save(vehicle);
    }
}

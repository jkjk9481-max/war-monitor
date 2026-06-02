package war_monitor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import war_monitor.dto.VehicleResponseDto;
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

    public List<VehicleResponseDto> getAllVehicle() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> toResponseDto(vehicle))
                .toList();
    }

    public VehicleResponseDto getVehicleById(Long id) {
        Vehicle vehicle = findVehicleEntityById(id);

        return toResponseDto(vehicle);
    }

    @Transactional
    public VehicleResponseDto updateVehicleStatus(Long id, VehicleStatus status) {
        Vehicle vehicle = findVehicleEntityById(id);
        vehicle.updateStatus(status);
        Vehicle saved = vehicleRepository.save(vehicle);

        return toResponseDto(saved);
    }

    private VehicleResponseDto toResponseDto(Vehicle vehicle) {
        return new VehicleResponseDto(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getType(),
                vehicle.getStatus()
        );
    }

    private Vehicle findVehicleEntityById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 이동수단을 찾을 수 없습니다."));
    }
}

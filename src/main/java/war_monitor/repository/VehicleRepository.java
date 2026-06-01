package war_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import war_monitor.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle , Long> {
}

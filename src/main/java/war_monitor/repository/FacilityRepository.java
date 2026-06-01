package war_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import war_monitor.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility , Long> {

}

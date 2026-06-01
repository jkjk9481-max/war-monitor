package war_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import war_monitor.entity.AttackEvent;

public interface AttackEventRepository extends JpaRepository<AttackEvent , Long> {
}

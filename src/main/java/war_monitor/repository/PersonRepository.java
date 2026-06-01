package war_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import war_monitor.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long>{
}

package war_monitor.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import war_monitor.dto.PersonResponseDto;
import war_monitor.entity.Person;
import war_monitor.entity.PersonStatus;
import war_monitor.exception.NotFoundException;
import war_monitor.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public PersonService(PersonRepository personRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.personRepository = personRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public List<PersonResponseDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(person -> toResponseDto(person))
                .toList();
    }

    public PersonResponseDto getPersonById(Long id) {
        Person person = findPersonEntityById(id);

        return toResponseDto(person);
    }

    @Transactional
    public PersonResponseDto updatePersonStatus(Long id, PersonStatus personStatus) {
        Person person = findPersonEntityById(id);
        person.updatePersonStatus(personStatus);
        simpMessagingTemplate.convertAndSend("/topic/person", person);
        Person saved = personRepository.save(person);

        return toResponseDto(saved);
    }

    private PersonResponseDto toResponseDto(Person person) {
        return new PersonResponseDto(
                person.getId(),
                person.getName(),
                person.getRole(),
                person.getPersonStatus(),
                person.getSide()
        );
    }

    private Person findPersonEntityById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 인물을 찾을 수 없습니다."));
    }
}

package war_monitor.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import war_monitor.entity.Person;
import war_monitor.entity.PersonStatus;
import war_monitor.exception.NotFoundException;
import war_monitor.repository.PersonRepository;

import java.util.List;

@Service

public class PersonService {

    private final PersonRepository personRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public PersonService(PersonRepository personRepository ,  SimpMessagingTemplate simpMessagingTemplate) {
        this.personRepository = personRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }


    public Person getPersonById(Long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 인물이 아닙니다"));
    }

    @Transactional
    public Person updatePersonStatus(Long id, PersonStatus personStatus){
        Person person = getPersonById(id);
        person.updatePersonStatus(personStatus);
        simpMessagingTemplate.convertAndSend("/topic/person" ,  person);
        return personRepository.save(person);
    }
}

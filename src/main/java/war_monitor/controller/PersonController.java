package war_monitor.controller;


import org.springframework.web.bind.annotation.*;
import war_monitor.entity.Person;
import war_monitor.entity.PersonStatus;
import war_monitor.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @PatchMapping("/{id}/status")
    public Person updatePersonStatus(@PathVariable Long id, @RequestBody PersonStatus status){
        return personService.updatePersonStatus(id , status);
    }
}

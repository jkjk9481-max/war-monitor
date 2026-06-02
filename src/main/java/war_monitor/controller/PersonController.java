package war_monitor.controller;

import org.springframework.web.bind.annotation.*;
import war_monitor.dto.PersonResponseDto;
import war_monitor.dto.PersonStatusUpdateRequest;
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
    public List<PersonResponseDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonResponseDto getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PatchMapping("/{id}/status")
    public PersonResponseDto updatePersonStatus(@PathVariable Long id, @RequestBody PersonStatusUpdateRequest request) {
        return personService.updatePersonStatus(id, request.getPersonStatus());
    }
}

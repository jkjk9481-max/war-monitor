package war_monitor.dto;

import lombok.Getter;
import war_monitor.entity.PersonStatus;

@Getter
public class PersonResponseDto {

    private Long id;
    private String name;
    private String role;
    private PersonStatus personStatus;
    private String side;

    public PersonResponseDto(Long id, String name, String role, PersonStatus personStatus, String side) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.personStatus = personStatus;
        this.side = side;
    }
}

package war_monitor.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role; // 직책

    @Enumerated(EnumType.STRING)
    private PersonStatus personStatus; // 상태


    private String side; // 정부군인지 반란군인지


    public void updatePersonStatus(PersonStatus personStatus){
        this.personStatus = personStatus;
    }


}

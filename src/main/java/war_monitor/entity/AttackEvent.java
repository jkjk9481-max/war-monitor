package war_monitor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter

public class AttackEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ManyToOne은 공격은 여러개인데 시설은 1개 타격한다는 의미
    @ManyToOne
    @JoinColumn(name = "facility_id") // DB에 facility_id 컬럼으로 저장
    // DB 테이블에 facility_id 라는 컬럼 만들어서 어떤 시설인지 번호로 알려준다
    private Facility facility;

    @Enumerated(EnumType.STRING)
    private AttackType attackType; // 공격 유형

    private LocalDateTime attackTime; // 공격 시간

    public AttackEvent(Facility facility, AttackType attackType, LocalDateTime attackTime) {
        this.facility = facility;
        this.attackType = attackType;
        this.attackTime = attackTime;
    }

    public AttackEvent(){

    }

}


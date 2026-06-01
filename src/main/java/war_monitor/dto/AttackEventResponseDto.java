package war_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import war_monitor.entity.AttackType;
import war_monitor.entity.FacilityStatus;

import java.time.LocalDateTime;


@Getter

public class AttackEventResponseDto {

    private Long id;

    private String facilityName;

    private AttackType attackType;

    private LocalDateTime attackTime;

    private FacilityStatus facilityStatus;

    public AttackEventResponseDto(Long id, String facilityName,
                                  AttackType attackType, LocalDateTime attackTime , FacilityStatus facilityStatus) {
        this.id = id;
        this.facilityName = facilityName;
        this.attackType = attackType;
        this.attackTime = attackTime;
        this.facilityStatus = facilityStatus;
    }
}

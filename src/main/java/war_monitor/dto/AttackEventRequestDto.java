package war_monitor.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import war_monitor.entity.AttackType;

@Getter
public class AttackEventRequestDto {

    @NotNull(message = "facilityId는 필수입니다.")
    private Long facilityId;

    @NotNull(message = "attackType은 필수입니다.")
    private AttackType attackType;
}

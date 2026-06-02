package war_monitor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import war_monitor.entity.PersonStatus;

@Getter
public class PersonStatusUpdateRequest {

    @NotNull(message = "인원 상태는 필수입니다")
    private PersonStatus personStatus;
}

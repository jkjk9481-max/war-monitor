package war_monitor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import war_monitor.entity.FacilityStatus;

@Getter
public class FacilityStatusUpdateRequest {

    @NotNull(message = "시설 상태는 필수입니다")
    private FacilityStatus status;
}

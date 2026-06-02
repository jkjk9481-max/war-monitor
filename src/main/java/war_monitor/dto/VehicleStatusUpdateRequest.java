package war_monitor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import war_monitor.entity.VehicleStatus;

@Getter
public class VehicleStatusUpdateRequest {

    @NotNull(message = "차량 상태는 필수입니다")
    private VehicleStatus vehicleStatus;
}

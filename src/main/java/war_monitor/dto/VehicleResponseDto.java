package war_monitor.dto;

import lombok.Getter;
import war_monitor.entity.VehicleStatus;

@Getter
public class VehicleResponseDto {

    private Long id;
    private String name;
    private String type;
    private VehicleStatus status;

    public VehicleResponseDto(Long id, String name, String type, VehicleStatus status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }
}

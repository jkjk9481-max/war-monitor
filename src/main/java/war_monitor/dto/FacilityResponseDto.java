package war_monitor.dto;

import lombok.Getter;
import war_monitor.entity.FacilityStatus;

@Getter
public class FacilityResponseDto {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private FacilityStatus status;

    public FacilityResponseDto(Long id, String name, Double latitude, Double longitude, FacilityStatus status) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }
}

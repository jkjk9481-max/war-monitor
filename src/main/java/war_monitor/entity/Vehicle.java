package war_monitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 차량 이름 - 탱크 , 장갑차 , 헬기 등등
    private String type; // 종류 - 지상 / 공중 / 해상

    @Enumerated(EnumType.STRING)
    private VehicleStatus status; // 차량 상태

    public Vehicle(String name, String type, VehicleStatus status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public void updateStatus(VehicleStatus status){
        this.status = status;
    }
}

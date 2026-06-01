package war_monitor.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동증가
    private Long id;

    private String name; // 시설 이름
    private Double latitude; // 위도
    private Double longitude; // 경도
    @Enumerated(EnumType.STRING)
    private FacilityStatus status; // 시설 상태

    public Facility(String name, Double latitude, Double longitude, FacilityStatus status) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    // JPA는 기본 생성자가 **필수**
    // JPA가 DB에서 데이터 꺼낼 때
    // 빈 객체 먼저 만들고 값을 채워넣는데
    // 기본 생성자 없으면 빈 객체를 못 만들든다

    public void updateStatus(FacilityStatus status){
        this.status = status;
    }

}

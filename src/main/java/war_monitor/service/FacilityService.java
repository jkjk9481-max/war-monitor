package war_monitor.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import war_monitor.entity.Facility;
import war_monitor.entity.FacilityStatus;
import war_monitor.exception.NotFoundException;
import war_monitor.repository.FacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> getAllFacilities(){
        return facilityRepository.findAll();
    }

    // **Optional이 뭐냐면
    // DB에서 찾았는데 없을 수도 있다
    // 그럴 때 null 대신 Optional로 감싸서 반환
    //"있을 수도 있고 없을 수도 있어" 라는 뜻
    public Facility getFacilityById(Long id){
        return facilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("시설을 찾을 수 없습니다."));
    }

    @Transactional
    public Facility updateFacilityStatus(Long id, FacilityStatus status){
        // 1.id로 시설찾기
       Facility facility = getFacilityById(id);
       // 2.상태 변경
        facility.updateStatus(status);
        // 3. 저장
        return facilityRepository.save(facility);
    }
}

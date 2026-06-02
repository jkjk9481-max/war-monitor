package war_monitor.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import war_monitor.dto.FacilityResponseDto;
import war_monitor.entity.Facility;
import war_monitor.entity.FacilityStatus;
import war_monitor.exception.NotFoundException;
import war_monitor.repository.FacilityRepository;

import java.util.List;


@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<FacilityResponseDto> getAllFacilities(){
        return facilityRepository.findAll()
                .stream()
                .map(facility -> toResponseDto(facility))
                .toList();
    }

    // **Optional이 뭐냐면
    // DB에서 찾았는데 없을 수도 있다
    // 그럴 때 null 대신 Optional로 감싸서 반환
    //"있을 수도 있고 없을 수도 있어" 라는 뜻
    public FacilityResponseDto getFacilityById(Long id){
       Facility facility = facilityRepository.findById(id).orElseThrow(()->
                new NotFoundException("시설을 찾을 수 없습니다"));

        return toResponseDto(facility);
    }

    @Transactional
    public FacilityResponseDto updateFacilityStatus(Long id, FacilityStatus status){
        // 1.id로 시설찾기
       Facility facility = findFacilityEntityById(id);
       // 2.상태 변경
        facility.updateStatus(status);

        Facility saved = facilityRepository.save(facility);
        // 3. 저장
        return toResponseDto(saved);
    }


    // Entity를 Response DTO로 바꾸는 변환 메서드
    private FacilityResponseDto toResponseDto(Facility facility){
        return new FacilityResponseDto(
                facility.getId(),
                facility.getName(),
                facility.getLatitude(),
                facility.getLongitude(),
                facility.getStatus()
        );
    }

    private Facility findFacilityEntityById(Long id){
        return facilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("시설을 찾을 수 없습니다"));
    }
}

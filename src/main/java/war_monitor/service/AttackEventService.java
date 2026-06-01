package war_monitor.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import war_monitor.dto.AttackEventRequestDto;
import war_monitor.dto.AttackEventResponseDto;
import war_monitor.entity.AttackEvent;
import war_monitor.entity.Facility;
import war_monitor.entity.FacilityStatus;
import war_monitor.exception.NotFoundException;
import war_monitor.repository.AttackEventRepository;
import war_monitor.repository.FacilityRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttackEventService{

    private final AttackEventRepository attackEventRepository;
    private final FacilityRepository facilityRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public AttackEventService(AttackEventRepository attackEventRepository, FacilityRepository facilityRepository , SimpMessagingTemplate messagingTemplate) {
        this.attackEventRepository = attackEventRepository;
        this.facilityRepository = facilityRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public List<AttackEventResponseDto> getAllAttackEvents(){
        return attackEventRepository.findAll()
                .stream()
                .map(attackEvent -> toResponseDto(attackEvent))
                // 목록 안에 있는 AttackEvent 하나하나를 toResponseDto 메서드에 넣어서 AttackEventResponseDto로 바꾼다
                .toList();
    }

    public AttackEventResponseDto getAttackEventById(Long id){
        AttackEvent attackEvent = attackEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 시설 공격 타깃이 아닙니다"));

        return toResponseDto(attackEvent);
    }

    @Transactional
    public AttackEventResponseDto createAttackEvent(AttackEventRequestDto request) {
        // 1.facilityId로 시설 찾기
        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new NotFoundException("시설을 찾을 수 없습니다"));

        // 2.AttackEvent 엔티티 만들기
        AttackEvent attackEvent = new AttackEvent(facility, request.getAttackType(), LocalDateTime.now()); // 현재 시간

        // 3. DB에 저장
        AttackEvent saved = attackEventRepository.save(attackEvent);

        facility.updateStatus(request.getAttackType().getResultStatus());
        facilityRepository.save(facility);

        // 4.ResponseDto으로 변환해서 반환
        AttackEventResponseDto response = toResponseDto(saved);

        // 5. WebSocket으로 알림 보내기
        messagingTemplate.convertAndSend("/topic/attack" , response);

        // 6. 반환
        return  response;
    }

    private AttackEventResponseDto toResponseDto(AttackEvent attackEvent){
        return new AttackEventResponseDto(
                attackEvent.getId(),
                attackEvent.getFacility().getName(),
                attackEvent.getAttackType(),
                attackEvent.getAttackTime(),
                attackEvent.getFacility().getStatus()

                //private 응답DTO toResponseDto(엔티티 entity) {
                //    return new 응답DTO(
                //        entity에서 필요한 값 꺼내기
                //    );
                //}
        );
    }
}

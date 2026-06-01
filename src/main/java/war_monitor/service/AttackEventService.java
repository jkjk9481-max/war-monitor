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

    public List<AttackEvent> getAllAttackEvents(){
        return attackEventRepository.findAll();
    }

    public AttackEvent getAttackEventById(Long id){
        return attackEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 시설 공격 타깃이 아닙니다"));
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
        AttackEventResponseDto response = new AttackEventResponseDto(
                saved.getId(),
                saved.getFacility().getName() ,
                saved.getAttackType() ,
                saved.getAttackTime() ,
                saved.getFacility().getStatus()
        );

        // 5. WebSocket으로 알림 보내기
        messagingTemplate.convertAndSend("/topic/attack" , response);

        // 6. 반환
        return  response;
    }
}

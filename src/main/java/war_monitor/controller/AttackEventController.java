package war_monitor.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import war_monitor.dto.AttackEventRequestDto;
import war_monitor.dto.AttackEventResponseDto;
import war_monitor.entity.AttackEvent;
import war_monitor.service.AttackEventService;

import java.util.List;

@RestController
@RequestMapping("/api/attacks")
public class AttackEventController {

    private final AttackEventService attackEventService;

    public AttackEventController(AttackEventService attackEventService) {
        this.attackEventService = attackEventService;
    }

    @GetMapping
    public List<AttackEvent> getAllAttacks(){
        return attackEventService.getAllAttackEvents();
    }

    @GetMapping("/{id}")
    public AttackEvent getAttack(@PathVariable Long id){
        return attackEventService.getAttackEventById(id);
    }

    @PostMapping
    // @Valid는 요청한 본문을 DTO로 바꾼 뒤 검증을 실행합니다
    public AttackEventResponseDto createAttack(@Valid @RequestBody AttackEventRequestDto request){
        return attackEventService.createAttackEvent(request);
    }
}

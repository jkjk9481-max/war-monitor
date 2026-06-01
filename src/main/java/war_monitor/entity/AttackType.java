package war_monitor.entity;

public enum AttackType {

    BOMBING(FacilityStatus.UNDER_ATTACK)  , // 폭파
    INFILTRATION(FacilityStatus.OCCUPIED) , // 침투
    DESTRUCTION(FacilityStatus.DESTROYED) , // 파괴
    CYBER(FacilityStatus.UNDER_ATTACK); // 사이버 공격

    private final FacilityStatus resultStatus;
    // 이 공격 유형이 시설에 미치는 상태

    AttackType(FacilityStatus resultStatus){
        this.resultStatus = resultStatus;
    }

    public FacilityStatus getResultStatus(){
        return resultStatus;
    }
}

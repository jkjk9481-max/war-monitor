# Caverlin War Monitor

카버린 전시 상황을 모니터링하는 Spring Boot 기반 데모 프로젝트입니다.

프론트엔드는 백엔드 API와 WebSocket 동작을 눈으로 확인하기 위한 간단한 데모 UI입니다. 핵심 구현은 Spring Boot API, JPA 도메인 로직, WebSocket 이벤트 전파 흐름에 있습니다.

## 기술 스택

- Backend: Java 17, Spring Boot 3.2.5, Spring Data JPA, WebSocket
- Database: PostgreSQL, H2 runtime dependency
- Frontend: HTML, JavaScript, Leaflet.js, SockJS, STOMP
- Build: Gradle

## 주요 기능

- 시설 목록 조회
- 시설 상태 변경
- 공격 이벤트 등록
- 공격 타입에 따른 시설 상태 자동 변경
- WebSocket을 통한 공격 이벤트 실시간 알림
- 인물과 차량 상태 조회 및 변경
- Leaflet 기반 지도 데모 화면

## 핵심 흐름: POST /api/attacks

공격 이벤트를 등록하는 API입니다. 사용자가 데모 화면에서 시설과 공격 타입을 선택하면 프론트엔드는 다음 요청을 보냅니다.

```http
POST /api/attacks
Content-Type: application/json

{
  "facilityId": 1,
  "attackType": "BOMBING"
}
```

처리 순서는 다음과 같습니다.

1. `AttackEventController`가 `POST /api/attacks` 요청을 받습니다.
2. 요청 본문은 `AttackEventRequestDto`로 매핑됩니다.
3. `AttackEventService.createAttackEvent()`가 `facilityId`로 시설을 조회합니다.
4. 시설이 있으면 `AttackEvent`를 생성하고 현재 시간을 저장합니다.
5. `attackType`에 연결된 결과 상태로 `Facility` 상태를 변경합니다.
6. 응답 데이터를 `AttackEventResponseDto`로 만듭니다.
7. `/topic/attack` WebSocket 채널로 공격 이벤트를 전송합니다.
8. 프론트엔드는 메시지를 받아 공격 로그와 지도 마커 색상을 갱신합니다.

## AttackEventRequestDto 역할

`AttackEventRequestDto`는 공격 등록 요청에서 클라이언트가 서버로 보내는 값을 담는 객체입니다. Entity가 아니라 API 요청 전용 DTO입니다.

필드:

| 필드 | 타입 | 의미 | 필수 여부 |
| --- | --- | --- | --- |
| `facilityId` | `Long` | 어떤 시설을 공격할지 찾기 위한 시설 ID | 필수 |
| `attackType` | `AttackType` | 등록할 공격 종류 | 필수 |

가능한 `attackType` 값:

| 값 | 의미 | 시설 결과 상태 |
| --- | --- | --- |
| `BOMBING` | 폭격 | `UNDER_ATTACK` |
| `INFILTRATION` | 침투 | `OCCUPIED` |
| `DESTRUCTION` | 파괴 | `DESTROYED` |
| `CYBER` | 사이버 공격 | `UNDER_ATTACK` |

주의할 점:

- `facilityId`는 공격 이벤트 ID가 아니라 시설 ID입니다.
- `GET /api/attacks/{id}`의 `{id}`는 시설 ID가 아니라 공격 이벤트 ID입니다.
- 현재 요청 DTO에는 `@NotNull` 같은 입력 검증이 아직 없습니다.

## API 목록

| Method | URL | 설명 |
| --- | --- | --- |
| `GET` | `/api/facilities` | 시설 전체 조회 |
| `PATCH` | `/api/facilities/{id}/status` | 시설 상태 변경 |
| `GET` | `/api/attacks` | 공격 이벤트 전체 조회 |
| `GET` | `/api/attacks/{id}` | 공격 이벤트 단건 조회 |
| `POST` | `/api/attacks` | 공격 이벤트 등록 |
| `GET` | `/api/persons` | 인물 현황 조회 |
| `PATCH` | `/api/persons/{id}/status` | 인물 상태 변경 |
| `GET` | `/api/vehicles` | 차량 현황 조회 |
| `PATCH` | `/api/vehicles/{id}/status` | 차량 상태 변경 |

## 실행 방법

PostgreSQL에 `war_monitor` 데이터베이스를 준비하고 `application.properties`의 접속 정보를 환경에 맞게 수정합니다.

```bash
./gradlew bootRun
```

Windows PowerShell에서는 다음 명령을 사용할 수 있습니다.

```powershell
.\gradlew.bat bootRun
```

애플리케이션 실행 후 브라우저에서 `http://localhost:8080`으로 접속하면 데모 화면을 볼 수 있습니다.

## 앞으로 보완할 부분

- `AttackEventRequestDto`에 `@Valid`, `@NotNull` 검증 추가
- Entity를 직접 반환하는 조회 API를 Response DTO로 변경
- 예외 응답 형식 통일
- 서비스 테스트 추가
- 개발용 H2 프로필과 운영용 PostgreSQL 프로필 분리
- 초기 데이터 정리

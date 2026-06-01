INSERT INTO FACILITY (name, latitude, longitude, status)
VALUES ('국회의사당', 37.5313, 126.9143, 'NORMAL');

INSERT INTO FACILITY (name, latitude, longitude, status)
VALUES ('방송국', 37.5131, 126.9769, 'NORMAL');

INSERT INTO FACILITY (name, latitude, longitude, status)
VALUES ('대통령궁', 37.5894, 126.9748, 'NORMAL');

INSERT INTO FACILITY (name, latitude, longitude, status)
VALUES ('군사기지', 37.4449, 127.1388, 'NORMAL');

INSERT INTO FACILITY (name, latitude, longitude, status)
VALUES ('인터넷시설', 37.5172, 127.0473, 'NORMAL');


INSERT INTO ATTACK_EVENT (facility_id, attack_type, attack_time)
VALUES (1, '폭격', '2026-03-22T14:23:00');

INSERT INTO ATTACK_EVENT (facility_id, attack_type, attack_time)
VALUES (2, '사이버공격', '2026-03-22T15:02:00');

INSERT INTO ATTACK_EVENT (facility_id, attack_type, attack_time)
VALUES (3, '보안시스템다운', '2026-03-22T15:08:34');





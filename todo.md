# mongoo-life-api MVP Todo

## 1. Project Baseline
- [x] `application.yaml` 프로필 구조 설계
- [x] Docker Compose로 MySQL local 환경 준비
- [x] JPA ddl-auto 전략 결정
  - local: create 또는 update 검토
  - prod: validate 권장

## 2. Common Foundation
- [x] `global.response.ApiResponse` 설계
- [x] `global.exception.ErrorCode` 설계
- [ ] `global.exception.CustomException` 설계
- [x] `global.exception.GlobalExceptionHandler` 설계
- [ ] Validation 예외 응답 포맷 설계
- [] 인증/인가 예외 응답 포맷 설계
- [x] `BaseTimeEntity` 설계
- [ ] 공통 enum 저장 전략 결정
  - [ ] Java enum 사용
  - [ ] DB는 `VARCHAR`
- [ ] 시간 계산 테스트를 위한 `Clock` Bean 도입 검토

## 3. Package Structure

- [ ] 기본 패키지 구조 생성

```text
com.mongoo.life
├── global
│   ├── config
│   ├── security
│   │   ├── jwt
│   │   ├── oauth
│   │   └── handler
│   ├── exception
│   ├── response
│   ├── entity
│   └── util
└── domain
    ├── user
    ├── auth
    ├── character
    ├── care
    ├── farm
    ├── seed
    ├── pond
    └── collection
```

- [ ] 각 도메인 내부 구조 기준 정리

```text
domain/{domain}
├── controller
├── service
├── repository
├── entity
├── dto
└── type
```

## 4. User / Auth Domain

- [x] `User` Entity 설계
- [x] `Provider` enum 설계
- [ ] `RefreshToken` Entity 설계
- [ ] `UserRepository` 설계
- [ ] `RefreshTokenRepository` 설계
- [ ] `RefreshTokenStore` 인터페이스 설계
  - [ ] MVP: DB 구현체
  - [ ] Future: Redis 구현체 확장 포인트
- [ ] 회원가입 API 설계
- [ ] 로그인 API 설계
- [ ] Access Token 발급 설계
- [ ] Refresh Token 발급 및 DB 저장 설계
- [ ] 토큰 재발급 API 설계
- [ ] 로그아웃 API 설계
  - [ ] Refresh Token revoke 처리
- [ ] 회원가입 후 초기 데이터 생성 정책 결정
  - [ ] Home 생성
  - [ ] FarmSlot 3개 생성
  - [ ] 초기 Seed 지급 여부 결정

## 5. Security

- [ ] `SecurityConfig` 설계
- [x] `JwtTokenProvider` 설계
- [x] `JwtAuthenticationFilter` 설계
- [ ] `CustomUserDetails` 설계
- [x] `CustomUserDetailsService` 설계
- [ ] 인증 실패 핸들러 설계
- [ ] 인가 실패 핸들러 설계
- [ ] CORS 정책 설계
- [ ] 공개 API와 인증 API 경로 분리
  - [ ] `/api/auth/**`
  - [ ] OAuth2 endpoints
  - [ ] Swagger 도입 시 경로 TODO
- [ ] JWT secret, 만료 시간 설정 분리

## 6. Google OAuth2

- [ ] OAuth2 client 설정 구조 설계
- [ ] Google user info 매핑 설계
- [ ] 기존 유저 로그인 처리
- [ ] 신규 유저 자동 생성 처리
- [ ] OAuth2 success handler 설계
- [ ] OAuth2 로그인 성공 후 JWT 전달 방식 결정
  - [ ] redirect query parameter
  - [ ] 임시 code 교환 방식은 Future TODO
- [ ] OAuth2 실패 핸들러 설계

## 7. Character / Care

- [ ] 캐릭터 Entity 이름 결정
  - [ ] 추천: `MongooCharacter`
- [ ] `MongooCharacter` Entity 설계
- [ ] `CharacterEvolutionScore` Entity 설계
- [ ] `CareLog` Entity 설계
- [ ] `CharacterStage` enum 설계
  - [ ] EGG
  - [ ] CHILD
  - [ ] ADULT
- [ ] `CharacterStatus` enum 설계
  - [ ] ACTIVE
  - [ ] LEFT
- [ ] `CareActionType` enum 설계
- [ ] `CareTargetType` enum 설계
- [ ] 캐릭터 생성 API 설계
- [ ] 내 캐릭터 조회 API 설계
- [ ] 상태 감소 계산 로직 설계
  - [ ] 포만감: 1시간당 -5
  - [ ] 청결도: 1시간당 -2
  - [ ] 행복도: 포만감 또는 청결도 50 이하일 때 1시간당 -2
  - [ ] 오프라인 감소 최대 24시간
- [ ] 떠남 계산 로직 설계
  - [ ] 마지막 접속 후 24시간마다 life_count -1
  - [ ] 5일 미접속 시 LEFT
- [ ] 우유 주기 API 설계
  - [ ] 30분 쿨타임
  - [ ] 포만감 +8
  - [ ] 행복도 +5
  - [ ] milk_count +1
- [ ] 씻기기 API 설계
  - [ ] 2시간 쿨타임
  - [ ] 청결도 +30
  - [ ] 행복도 +3
- [ ] 알에서 유년기 진화 로직 설계
  - [ ] 우유 5회
  - [ ] 생성 후 최소 6시간 경과

## 8. Home / Farm / Seed

- [ ] `Home` Entity 설계
- [ ] `FarmSlot` Entity 설계
- [ ] `Seed` Entity 설계
- [ ] `FarmSlotStatus` enum 설계
  - [ ] EMPTY
  - [ ] PLANTED
  - [ ] HARVESTABLE
  - [ ] WITHERED
- [ ] `CropType` / `SeedType` enum 설계
  - [ ] CARROT
  - [ ] TOMATO
  - [ ] MUSHROOM
- [ ] 작물 메타데이터 설계
  - [ ] 성장 시간
  - [ ] 포만감 증가
  - [ ] 행복도 변화
  - [ ] 진화 점수 변화
  - [ ] EXP 증가
- [ ] 밭 조회 API 설계
- [ ] 씨앗 목록 조회 API 설계
- [ ] 씨앗 심기 API 설계
- [ ] 작물 성장 상태 계산 로직 설계
- [ ] 작물 수확 API 설계
- [ ] 시든 작물 제거 API 설계
- [ ] 수확 즉시 자동 섭취 여부 결정
  - [ ] 기획 루프상 자동 섭취 추천

## 9. Pond / Fishing

- [ ] 낚시 쿨타임 저장 위치 결정
  - [ ] 추천: `MongooCharacter.lastFishedAt`
- [ ] `FishType` enum 설계
  - [ ] CLAM
  - [ ] ANCHOVY
  - [ ] ROCK_BREAM
  - [ ] HIDDEN_FISH
- [ ] 물고기 확률 테이블 설계
  - [ ] 조개 60%
  - [ ] 멸치 30%
  - [ ] 돌돔 9%
  - [ ] 히든 물고기 1%
- [ ] 낚시 API 설계
- [ ] 10분 쿨타임 검증
- [ ] 실패 없음 정책 반영
- [ ] 획득 즉시 자동 섭취
- [ ] 포만감/EXP 반영
- [ ] care log 기록
- [ ] 랜덤 로직 테스트 가능성 확보
  - [ ] `Random` 또는 별도 picker 주입 검토

## 10. Evolution / Collection

- [ ] 성체 진화 조건 설계
  - [ ] growth_exp >= 100
  - [ ] 행복도 기준 70 이상
  - [ ] 청결도 기준 50 이상
- [ ] 평균 상태값 계산 여부 결정
  - [ ] MVP 추천: 현재 상태값 기준
  - [ ] Future: 상태 히스토리 또는 집계 테이블
- [ ] character type 결정 로직 설계
  - [ ] active_score
  - [ ] dark_score
  - [ ] nature_score
  - [ ] water_score
- [ ] 성체 진화 API 또는 자동 진화 시점 결정
  - [ ] 조건 충족 시 행동 API 이후 즉시 진화 추천
- [ ] 도감 등록 로직 설계
- [ ] 내 도감 조회 API 설계
- [ ] 중복 도감 등록 방지

## 11. API Design

- [ ] API prefix 결정
  - [ ] 추천: `/api`
- [ ] Auth API 경로 설계
- [ ] User API 경로 설계
- [ ] Character API 경로 설계
- [ ] Farm API 경로 설계
- [ ] Seed API 경로 설계
- [ ] Pond API 경로 설계
- [ ] Collection API 경로 설계
- [ ] 요청/응답 DTO 명명 규칙 정리
- [ ] 공통 응답 포맷 적용

```json
{
  "success": true,
  "data": {},
  "message": null
}
```

## 12. Testing

- [ ] 공통 예외 응답 테스트
- [ ] 회원가입 테스트
- [ ] 로그인 테스트
- [ ] JWT 발급/검증 테스트
- [ ] Refresh Token 저장/재발급 테스트
- [ ] OAuth2 매핑 로직 테스트
- [ ] 상태 감소 계산 테스트
- [ ] 우유 쿨타임 테스트
- [ ] 씻기기 쿨타임 테스트
- [ ] 알에서 유년기 진화 테스트
- [ ] 작물 성장 판정 테스트
- [ ] 작물 수확 테스트
- [ ] 낚시 쿨타임 테스트
- [ ] 낚시 확률 테스트
- [ ] 성체 진화 조건 테스트
- [ ] 도감 등록/조회 테스트

## 13. MVP Exclusions

아래 기능은 MVP에서 구현하지 않는다. 필요하면 확장 포인트만 남긴다.

- [ ] 친구
- [ ] 교배
- [ ] 훔치기
- [ ] 집 꾸미기
- [ ] 상점
- [ ] 가방
- [ ] PWA
- [ ] 푸시 알림
- [ ] 캐릭터 성격/대사 세부화
- [ ] Redis Refresh Token 저장

## 14. Open Decisions

- [ ] 유저당 캐릭터를 1마리만 허용할지, 여러 마리 히스토리를 허용할지 결정
  - 추천: MVP는 현재 ACTIVE 캐릭터 1마리만 허용하되, DB는 히스토리 확장 가능하게 유지
- [ ] 진화 조건의 행복도/청결도 "평균"을 현재 상태값으로 볼지 결정
  - 추천: MVP는 현재 상태값 기준
- [ ] 작물 시듦 시간 정책 결정
  - ERD에는 `withered_at`이 있으나 기획서에 명확한 시듦 기준 시간이 없음
- [ ] 초기 씨앗 지급 수량 결정
- [ ] 캐릭터 이름 변경 가능 여부 결정
- [ ] Google OAuth2 로그인 후 프론트 redirect URL 결정
- [ ] Access Token / Refresh Token 만료 시간 결정

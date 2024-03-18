<!--Header-->
# 와이어 프레임
![image](https://user-images.githubusercontent.com/74723818/231402480-8fb57812-4419-468c-aa75-a4295f172a3c.png)  
# 앱 적용 예시 이미지
![image](https://user-images.githubusercontent.com/74723818/231403855-d4186812-1078-4ec8-9175-687ca0a43ef6.png)  
---
# 1. new_instagram_server
- ProjectName : **new instagram server**
- DevelopPeriod : *2024.02.05 ~ 진행중*
---
# 2. Info
- 목표
  네카라쿠배 4년차 현직 개발자와 함께하는 인스타그램 서버만들기 챌린지 이후 프로젝트로 진행한다.
  API 가이드라인을 따라 계층형 아키텍처에서 헥사고날 아키텍처로 변환하여 instagram 서버를 구현한다.
  
  | **시스템 구조도 아키텍쳐** |

  | **Hexgonal Architecture** | 
  ![image](https://github.com/CordHouse/new_instagram_server/assets/74723818/d4e34a95-5e03-4821-9b5a-636a618f8051)
  
  | **REST APIs** |  
  ![REST](https://user-images.githubusercontent.com/74723818/230903512-52c8f8ea-540b-4067-9daa-2048a8d6319d.png)

  | **ERD** |
  ![InstaERD](https://user-images.githubusercontent.com/74723818/230729128-6da89542-5256-4356-9e91-98e2b021f0d4.PNG)

  | **DB Schema** |  
  ![image](https://user-images.githubusercontent.com/74723818/231405300-ac08485b-172c-4129-9080-359b0f84e664.png)
  
- API 스펙
  - 글 <생성, 수정, 삭제>
  - 댓글 <생성, 수정, 삭제>
  - 답글 <생성, 수정, 삭제>
  - 피드 조회
  - 회원 가입
  - 로그인
  - 회원 탈퇴
  - 프로필 <조회, 수정>
  - 팔로우, 언팔로우
  - DM 목록 조회, DM 상세 내역 조회, DM 전송
  - 토큰 재발급
---
# 3. Member
- **BackEnd**
 - [이지우](https://github.com/CordHouse)
---
# 4. Skill
```text
- Jwt 
- Security
- Git
 - Issue
 - Wiki
- Pagenation (cursor)
  - QueryDsl
  - Pageable
```
---
# 5. Config
```text
1. Jwt access_token = 120분
2. Jwt refresh_token = 7일
3. Security whiteList = 회원가입, 로그인, 프로필 조회
```
---
# 6. Memoir(회고록) & 가이드라인 & wiki
- [회고록](https://substantial-authority-c78.notion.site/d32d43bd36a94489b02b98f9d448b71b)
- [가이드라인](https://thoughtful-arch-8c2.notion.site/Spring-c83f01ab221a4166a2713120728aa552)
- [위키](https://github.com/CordHouse/new_instagram_server/wiki)

---
# 7. Convention
| **Git Convention** |
[위키-컨벤션](https://github.com/CordHouse/new_instagram_server/wiki/1.-Convention)

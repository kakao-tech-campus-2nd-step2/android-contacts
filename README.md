# KakaoTechCampus 2기 step2 
## 1주차 1단계 과제 - 연락처 추가

### 기능 설명
1. 전체 폼 디자인
2. 프로필 이미지 설정
3. 입력 필드 기능 
4. 전화번호 필수 입력 값 기능
5. 더보기 토글 기능 
6. 생일 선택 캘린더 팝업 기능
7. 성별 선택 기능
8. 취소 기능
9. 저장 기능

---

## 1주차 2단계 과제 - 연락처 목록

### 화면 3가지
> main 화면
> > 오른쪽 하단에 + 블록 버튼이 있는 연락처 조회 및 추가 시작 화면

> 연락처 추가 화면
> > 1단계에서 구현한 화면

> 연락처 세부사항 조회 화면
> > 추가된 연락처를 누룰 시, 해당 연락처 프로필 이미지, 이름, 전화번호 조회 가능

### 작동 방법
1. main 화면에서 오른쪽 하단의 + 버튼을 누르면 '연락처 추가 화면'으로 넘어간다.
2. 해당 '연락처 추가 화면'에서 이름과 전화번호(필수값)을 입력 후 '저장'을 누르면 다시 초기의 main 화면으로 돌아간다. 단, 이때 main 화면에는 추가한 연락처 목록이 조회된다.
3. 해당 연락처 목록을 클릭 시 '연락처 세부사항 조회 화면'으로 넘어간다.
4. 해당 '연락처 세부사항 조회 화면'에는 입력한 이름, 전화번호(필수값)이 있고, 그 외 입력한 값들이 있다면 그 정보들이 추가로 떠 있다.
5. 이후 뒤로가기 버튼을 누르면 다시 main 화면으로 돌아간다.
6. 앱을 종료 시 해당 데이터들은 모두 리셋된다.

### 기능 구현
1. 연락처 추가 기능 구현 - 1단계 구현 내용
2. main 화면 플로팅 버튼 및 텍스트 구현
3. 추가한 연락처 데이터 - contact 객체 - intent로 전달 기능 구현
4. 전달받은 객체 데이터를 Recyclerview로 표현
5. 연락처 추가 시 항목 리스트 조회 기능 구현
6. 추가된 연락처 스크롤 및 세부사항 화면 클릭 기능 구현
7. 연락처 세부 사항 조회 기능 구현
8. 세부 조회 데이터 조건 구현

---

### 전체 디렉토리 구조 (2단계 기준)
```bash
app
├── build
├── src
│   ├── androidTest
│   │   └── java
│   │       └── contacts
│   │           └── .gitkeep
│   ├── main
│   │   ├── java
│   │   │   └── contacts
│   │   │       ├── Contact.kt
│   │   │       ├── ContactAddActivity.kt
│   │   │       ├── ContactDetailActivity.kt
│   │   │       ├── ContactsAdapter.kt
│   │   │       └── MainActivity.kt
│   │   └── res
│   │       ├── drawable
│   │       │   ├── circle_background.xml
│   │       │   ├── ic_add.xml
│   │       │   ├── ic_launcher_background.xml
│   │       │   ├── ic_launcher_foreground.xml
│   │       │   ├── profile_image.png
│   │       │   ├── round_background.xml
│   │       │   └── round_corners.xml
│   │       └── layout
│   │           ├── activity_main.xml
│   │           ├── contact_add.xml
│   │           ├── contact_detail.xml
│   │           └── contact_item.xml
├── .gitignore
├── build.gradle.kts
└── proguard-rules.pro
```
### 구현 화면 
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/6cd67398-f9af-483a-8f5c-d2f50c154352" width="200" height="400"/>
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/bb312d57-7b6d-4c30-ab6a-febfadb8d7cc" width="200" height="400"/>
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/f522fb92-7139-469f-b7d2-fffdf0f75d9d" width="200" height="400"/>
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/ea04d308-2bb5-4387-9b2c-2e9853cb37ee" width="200" height="400"/>
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/b2e5f425-d791-4265-8b07-da94115e3044" width="200" height="400"/>
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/f6f155cd-7e36-4c83-9be9-24aa1165c2e6" width="200" height="400"/>
<img src="https://github.com/YJY1220/Programmers_prac/assets/93771689/fcc10402-f863-4a88-9b59-f36be7779d0e" width="200" height="400"/>

### 실행 영상 (전체 실행영상)
[https://youtu.be/re7rHcPNlhE](https://youtu.be/S2kDtQot_4Y)

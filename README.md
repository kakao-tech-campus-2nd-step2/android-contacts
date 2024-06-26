# android-contacts
## 📣 2단계 기능 업로드 (2024.06.26)
---
- 연락처 리스트 액티비티 추가하기
    - `ContactListActivity` 클래스와 `activity_contact_list.xml` 레이아웃 파일을 추가
- `activity_contact_list` 뷰 xml 제작하기
    - RecyclerView 활용
    - 연락처 목록은 스크롤 되어야함
    - 연락처 추가 버튼이 존재함
- 메인 액티비티에서 연락처 리스트 액티비티로 전환되도록 하기
- 리스트 액티비티에서 연락처 추가 버튼을 누르면 1단계에서 작성한 추가 화면으로 전환되도록 하기
- 추가 화면에서 연락처 저장 시 연락처 리스트에 추가하기
- 추가 화면에서 작성 중 뒤로가기 클릭 시 확인 팝업 뜨도록 하기
- `ContactItemActity` 클래스와 `activity_contact_item.xml` 추가하기
- `activity_contact_item` 의 뷰 xml 제작하기
- 리스트에서 연락처 목록 선택 시 상세 화면(`ContactItemActity`)으로 전환하기

## 📣 1단계 기능 업로드 (2024.06.25)
---
### 1. 스켈레톤 업로드

- Contact 모델 스켈레톤 코드 제작
    - 속성 : name : String , phoneNumber: String, email : String, birth: String, gender: String, memo: String
- ContactRepository 스켈레톤 코드 제작
    - 속성 : 전화번호부 리스트 contacts : mutableListOf<Contact>
    - 리스트에 전화번호를 추가, 삭제, 조회 기능
- ActivityContact 스켈레톤 코드 제작

### 2. 리소스 업로드

- 더보기 아이콘 업로드
- 디폴트 프로필 이미지 업로드
- 커스텀 버튼 드로어블 업로드

### 3. 뷰 제작

- activity_contact.xml 뷰 제작하기
    - name : EditText , phoneNumber: EditText, gender : RadioGroup, birth: EditText, button: Button

### 4. 기능 구현

- 액티비티 뷰 findViewById 하기
- 더보기 클릭 시 선택 요소(생일, 성별, 메모) 표시하기
- 생일 뷰 클릭 시 DatePickerDialog 띄우기
- 성별 뷰는 radio button 으로 제작되어 클릭 시 체크 표시 후 선택된 성별로 세팅하기
- 저장 버튼 클릭 시
    - 입력 폼 조건 확인하기
        - 이름과 전화번호가 입력되었나요?
    - 조건에 부합하지 않을 경우 토스트 메시지(조건 부합X) 띄우기
    - 조건에 부합할 경우
        - ContactRepository contacts List 에 저장하기
        - 토스트 메시지(저장 완료) 띄우기
- 취소 버튼 클릭 시
    - 토스트 메시지 토스트 메시지(취소) 띄우기
    - 모든 속성 값 초기화하기
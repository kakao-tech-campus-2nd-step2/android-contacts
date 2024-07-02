# android-contacts

## 화면구성

![화면(2)](https://github.com/yb0x00/android-contacts/assets/169421565/2097b2b6-f252-45cc-9e09-c425a33de1ee)

![화면(1)](https://github.com/yb0x00/android-contacts/assets/169421565/8d3fc119-3712-4b21-a967-54de464897c0)


[화면 구성 요소]
- 연락처 목록 화면(A) : activity_contact_list.xml
  - 연락처 추가 버튼
  - 저장된 연락처 목록
- 연락처 입력 화면(B) : activity_save_contact
  - 이미지
  - 이름, 전화번호, 이메일, 생일, 성별, 메모 입력란
- 연락처 상세 화면(C) : activity_contact_detail.xml
  - 이미지
  - 입력한 정보

## 구현할 기능 목록

- (A의) 연락처 추가 버튼을 누르면 연락처 입력 화면(B)이 나타남

    ✔️goToAddContact()

  
- 연락처 입력 화면(B)의 저장 버튼을 누르면 저장된 연락처가 (A의)목록에 추가됨

    ✔️saveData(), getData(), displayContact(), class PersonAdapter

  
- 연락처 목록은 스크롤됨 ✔️ScrollView 사용

  
- 추가된 연락처를 클릭하면 연락처 상세 화면(C)이 나타남

    ✔️onBindViewHolder(), display()


- 연락처 작성 중 뒤로가기 버튼을 누르면 "작성중인 내용이 있습니다. 정말 나가시겠습니까?" 확인 팝업이 나타남

    ✔️exitPopup()

- 추가된 연락처는 앱을 다시 실행하면 유지되지 않음

    ✔️clearData(), personList.clear()

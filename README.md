# android-contacts

- 카카오 테크 캠퍼스 과제(연락처 앱) 수행을 위한 저장소입니다.

## *Contents*
1. 홈 화면　　　　　　2. 연락처 추가 화면　　　　　3. 취소 / 뒤로가기 팝업

<img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/registerActivity.png width="200" height="350"><img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/mainActivity.png width="200" height="350"><img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/cancelEvent.png width="200" height="350">

4. 연락처 목록 화면　　　　　　5. 연락처 목록 화면(회전)

<img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/contactList.png width="200" height="350"><img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/listRotation.png width="500" height="200">

6. 상세화면1　　　　　7. 상세 화면2

<img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/detailActivity.png width="200" height="350"><img src=https://github.com/ichanguk/android-contacts/blob/ichanguk/img/detailActivity2.png width="200" height="350">

## feature

### 1단계 - 연락처 추가

1. 이름과 전화번호 입력 기능
    - 이름과 전화번호는 필수 값으로 입력하지 않은 경우 토스트 메시지 보여준다.

2. 전화번호 입력 기능
    - 숫자만 입력 가능

3. 더보기 기능
    - 더보기를 눌러 입력 폼을 확장할 수 있다.
    - 추가되는 입력 폼 : 생일, 성별, 메모

4. 성별 입력 기능
    - 성별을 둘 중 하나를 선택할 수 있다.

5. 저장 기능
    - 저장 버튼을 누르면 '저장이 완료 되었습니다' 라는 토스트 메시지를 보여준다.

6. 취소 기능
    - 취소 버튼을 누르면 '취소 되었습니다' 라는 토스트 메시지를 보여준다.

7. 생일 입력 기능
    - 생일을 달력에서 선택해서 입력할 수 있다.


### 2단계 - 연락처 목록

1. 연락처 등록 화면
    - 연락처 추가 버튼을 누르면 연락처 추가 화면으로 넘어감

2. 연락처를 저장하면 목록에 추가
    - 앱을 다시 실행하면 목록은 비어있음
    - 저장된 연락처가 많을 경우 목록 스크롤 가능

3. 연락처 목록의 연락처를 선택하면 상세 화면 출력

4. 연락처 작성 중 뒤로가기 / 취소 버튼을 누르면 확인 팝업 출력
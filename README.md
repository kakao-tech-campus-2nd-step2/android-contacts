# android-contacts

## Step1 연락처 추가

### Layout Requirements

**User input**

- Default: name, phone number, e-mail
    - only numbers can be input in phone number
- Addition: birthday, gender, memo
    - choose one of two genders

**Button**

- more
- cancel
- save

### Function List

**Additional Input**

더보기 버튼을 누르면 더보기 버튼과 입력 폼의 invisibility 값이 swap됨

- 생일, 성별, 메모 추가 입력 폼은 초기값으로 invisibility 속성의 값으로 gone을 가짐
- 더보기 버튼은 초기값으로 invisibility 속성의 값으로 visible을 가짐

**Cancel**

“취소 되었습니다”라는 토스트 메시지를 출력

**Verify required input**

저장 버튼을 눌렀을 경우

- 이름을 입력하지 않으면 “이름은 필수 값입니다”라는 토스트 메시지를 출력
- 전화번호 입력하지 않으면 “전화번호는 필수 값입니다”라는 토스트 메시지를 출력
- 필수 입력사항이 입력되면 “저장이 완료 되었습니다”라는 토스트 메시지를 출력
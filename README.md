# android-contacts

## Step1 _연락처 추가_

### Layout Requirements

**User input**

- Default: name, phone number, e-mail
  - only numbers can be input in phone number
- Addition: birthday, gender, memo
  - choose one of two genders

**Button**

- more details
- cancel
- save

### Function List

**Additional Input**

after clicking the more details button, the more details button and the additional input form's visibility value are swapped

- birthday, gender, memo input form has an default value of gone for the visibility attribute
- more details button has an default value of visible for the visibility attribute

**Cancel**

When cancel button is clicked, ouput toast message "취소 되었습니다"

**Verify required input**

When save button is clicked

- when phone number is not exist, output toast message "전화번호는 필수 값입니다"
- when name is not exist, output toast message "이름은 필수 값입니다"
- all input requirements was exist, then output toast message "저장이 완료 되었습니다"

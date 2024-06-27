# android-contacts

## Step1 _연락처 추가_

### Layout Requirements

**연락처 추가**

User input

- Default: name, phone number, e-mail
    - only numbers can be input in phone number
- Addition: birthday, gender, memo
    - choose one of two genders

Button

- more details
- cancel
- save

### Function List

**연락처 추가**

Additional Input

- after clicking the more details button, the more details button and the additional input form's
  visibility value are swapped
- birthday, gender, memo input form has an default value of gone for the visibility attribute
- more details button has an default value of visible for the visibility attribute

Cancel

- When cancel button is clicked, ouput toast message "취소 되었습니다"

Verify required input

- When save button is clicked
- when phone number is not exist, output toast message "전화번호는 필수 값입니다"
- when name is not exist, output toast message "이름은 필수 값입니다"
- all input requirements was exist, then output toast message "저장이 완료 되었습니다"

## Step2 _연락처 목록_

### Layout Requirements

**연락처 목록**

- call saved contacts in List view format
    - if registered contacts is many, should be scrolled
    - the item show the profile picture and name
- Bottom right button is clicked, user go to *연락처 추가*

**연락처 추가**

- No changes

**연락처 정보**

- owner profile picture is at the top
- output name and phone number

### Function List

**연락처 목록**

GetContactList

- Get Contact List in application
- Create one list view item per element

GotoContactOwnerInfo

- When list view item is clicked, go to contact owner’s information

GotoAddContact

- When clicked + button, go to *연락처 추가*

**연락처 추가**

Cancel

- When Cancel button is clicked, open the popup window and user can select stay or leave
- stay: user can continue to create
- leave: don’t save the information and close the window

Verify required input

- When Save button is clicked, function is executed
- when phone number is not exist, output toast message "전화번호는 필수 값입니다"
- when name is not exist, output toast message "이름은 필수 값입니다"
- all input requirements was exist, save the information and close the window

SaveOwnerInfo

- send owner name and phone number to mainActivity

**연락처 정보**

GetContactOwnerInfo

- get information about contact owner
- output owner name and phone number
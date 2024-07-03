# android-contacts 
[기능구현]
1. 연락처와 이름, 전화번호를 입력하는 기능
2. 저장 버튼을 누르면 저장이 완료되었다는 토스트 메세지를 볼 수 있다. 
3. 취소 버튼을 누르면 취소되었습니다 는 토스트 메세지를 보여준다.
4. 더보기를 눌러 입력 폼을 확장기능 구현
5. 성별은 둘 중 하나를 선택할 수 있다
6. 입력하지 않았을 시 토스트 메세지 "잘못된 입력입니다"가 뜨게 한다.
# 수정 후 기능 변화
1. 우선 RoomDB에서 오류가 많이 나서 일단 삭제하고 SharedPreference로 구현을 했습니다.
2. collection_xml의 내용을 멘토님의 조언을 받아 FrameLayout에서 ConstraintLayout으로 변경하여 
RecyclerView로 구현을 해보았습니다.
3. collection.kt의 오류를 수정하였습니다.
4. recyclerView와 ItemView가 collection.xml에 표시되게끔 만들었습니다.

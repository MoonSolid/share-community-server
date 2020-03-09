# 32_4 - 클라이언트의 데이터 관리 요청을 처리하기 



- src/main/java/com/eomcs/lms/ServerApp.java 변경

###  1: 서비스를 시작할 때 클라이언트의 연결을 기다리는 코드를 추가.

- ServerApp.java 변경
###  2: 클라이언트의 게시물 목록 요청(/board/list)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경
- ServerAppTest.java 추가
- Board.java 변경
  - 통신 테스트 할 때 게시물 필드 정보를 확인할 수 있도록 toString()을 오버라이딩 .

###  3: 클라이언트의 게시물 등록 요청(/board/add)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경

###  4: 클라리언트의 게시물 조회 요청(/board/detail)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경
  
###  5: 클라리언트의 게시물 변경 요청(/board/update)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경  
  
###  6: 클라리언트의 게시물 삭제 요청(/board/delete)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경  

###  7: 클라리언트의 수업 관리 요청(/lesson/*)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경  
  
###  8: 클라이언트의 회원 관리 요청(/member/*)을 처리.

- ServerApp.java 변경
  - processRequest() 메서드 변경  
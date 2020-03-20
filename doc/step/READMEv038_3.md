# 38_3 - 트랜잭션 적용 전: 코드 리팩토링하기



- src/main/java/com/eomcs/util/Prompt.java 추가
- src/main/java/com/eomcs/servlet/XxxServlet.java 변경

### 1: 클라이언트에게 입력 값을 요구하는 코드를 리팩토링.

- com.moonsolid.sc.Prompt 추가
  - 입력 값을 요구하는 코드를 메서드로 정의.
  - getXxx() 메서드 정의.
- com.moonsolid.sc.servlet.XxxServlet 변경
  - 입력 값을 요구하는 코드를 Prompt.getXxx() 호출로 대체.

### 2: 첨부파일 입력 코드를 리팩토링.

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 변경
  
  - 첨부파일 입력 부분을 별도의 메서드로 분리.
  
- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 변경
  - 첨부파일 목록을 출력하는 부분을 별도의 메서드로 분리.
  - 첨부파일 입력 부분을 별도의 메서드로 분리.
  
  ---
  
  

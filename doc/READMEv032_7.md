# 32_7 - 데이터 처리 코드를 별도의 클래스로 정의하여 객체화

## 

###  1: 게시글 데이터를 처리하는 DAO 클래스를 정의.

- com.moonsolid.sc.dao 패키지를 생성.
- com.moonsolid.sc.BoardObjectFileDao 클래스를 정의.

###  2: BoardObjectFileDao 객체를 적용.

- com.moonsolid.sc.DataLoaderListener 를 변경.
  - 게시물 데이터를 로딩하고 저장하는 기존 코드를 제거.
  - 대신에 BoardObjectFileDao 객체를 생성.
- com.moonsolid.sc.ServerApp 을 변경.
  
  - Map에서 BoardObjectFileDao를 꺼내 관련 커맨드 객체에 주입.
- BoardXxxServlet 을 변경.
  - 생성자에서 List 객체를 받는 대신에 BoardObjectFileDao 객체를 받.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 BoardObjectFileDao 객체를 통해 처리.
  
  
###  3: 일정 데이터를 처리하는 DAO 클래스를 정의하고 적용.

- com.moonsolid.sc.PlanObjectFileDao 클래스를 정의.
- com.moonsolid.sc.DataLoaderListener 를 변경.
  - 일정 데이터를 로딩하고 저장하는 기존 코드를 제거.
  - 대신에 LessonObjectFileDao 객체를 생성.
- com.moonsolid.sc.ServerApp 을 변경.
  - Map에서 PlanObjectFileDao를 꺼내 관련 커맨드 객체에 주입.
- PlanXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 PlanObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 PlanObjectFileDao 객체를 통해 처리.

###  4: 회원 데이터를 처리하는 DAO 클래스를 정의하고 적용.

- com.moonsolid.sc.MemberObjectFileDao 클래스를 정의.
- com.moonsolid.sc.DataLoaderListener 를 변경.
  - 회원 데이터를 로딩하고 저장하는 기존 코드를 제거.
  - 대신에 MemberObjectFileDao 객체를 생성.
- com.moonsolid.sc.ServerApp 을 변경.
  
  - Map에서 MemberObjectFileDao를 꺼내 관련 커맨드 객체에 주입.
- MemberXxxServlet 을 변경.
  - 생성자에서 List 객체를 받는 대신에 MemberObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 MemberObjectFileDao 객체를 통해 처리.
  
  
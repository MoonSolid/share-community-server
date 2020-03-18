# 37_2 - Application Server 구조로 변경: Servlet + DAO 적용 + 통신 규칙



### 1: MariaDB JDBC Driver를 프로젝트에 추가.

- build.gradle 변경
### 2:  DAO 클래스 생성

- com.moonsolid.sc.dao.mariadb 패키지 생성
- com.moonsolid.sc.dao.mariadb.BoardDaoImpl 추가
- com.moonsolid.sc.dao.mariadb.LessonDaoImpl 추가
- com.moonsolid.sc.dao.mariadb.MemberDaoImpl 추가

### 3: Connection 객체를 준비해서 DAO를 생성할 때 주입

- com.moonsolid.sc.DataLoaderListener 변경
  - Connection 객체 생성
  - mariadb 관련 DAO 객체 생성

### 4: '통신 규칙1'에 따라 동작하도록 BoardListServlet을 변경.

- com.moonsolid.sc.servlet.Servlet 변경
  - service(Scanner in, PrintStream out) 메서드 추가.
  - 기존 구현체가 영향 받지 않도록 default 로 선언.
- com.moonsolid.sc.servlet.BoardListServlet 변경
  - service(Scanner in, PrintStream out) 메서드 구현으로 변경.
  - '통신 규칙1'에 따라 클라이언트에게 결과를 응답.
  
### 5: 클라이언트의 '/board/list' 요청을 BoardListServlet으로 처리.

- com.moonsolid.sc.ServerApp 변경
  - 클라이언트 명령을 처리할 서블릿을 찾아 실행. 
  
### 6: 클라이언트의 '/member/list' 요청을 MemberListServlet으로 처리.

- com.moonsolid.sc.servlet.MemberListServlet 변경
  - 기존 service() 메서드를 service(Scanner in, PrintStream out)으로 변경.
  - '통신 규칙1'에 따라 응답하도록 변경.
- com.moonsolid.sc.ServerApp 변경
  - '/member/list' 요청을 처리할 MemberListServlet을 서블릿맵에 등록.
  
### 7: 클라이언트의 '/Plan/list' 요청을 PlanListServlet으로 처리.

- com.moonsolid.sc.servlet.PlanListServlet 변경
  - 기존 service() 메서드를 service(Scanner in, PrintStream out)으로 변경.
  - '통신 규칙1'에 따라 응답하도록 변경.
- com.moonsolid.sc.ServerApp 변경
  - '/plan/list' 요청을 처리할 PlanListServlet을 서블릿맵에 등록.
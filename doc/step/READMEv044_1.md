# 	44_1 - UI 객체에서 비즈니스 로직 분리

## 

### 1: PhotoBoardXxxServlet 에서 비즈니스 로직을 분리.

- com.moonsolid.sc.service 패키지 추가
- com.moonsolid.sc.service.PhotoBoardService 인터페이스 추가
- com.moonsolid.sc.service.PlanService 인터페이스 추가
- com.moonsolid.sc.service.impl.PhotoBoardServiceImpl 클래스 추가
- com.moonsolid.sc.service.impl.PlanServiceImpl 클래스 추가
- com.moonsolid.sc.servlet.PhotoBoardXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드를 서비스 객체로 이동.
- com.moonsolid.sc.DataLoaderListener 변경
  - 서비스를 객체를 생성.
- com.moonsolid.sc.ServerApp 변경
  - 서블릿에 서비스 객체를 주입.
  
### 2: BoardXxxServlet 에서 비즈니스 로직을 분리.

- com.moonsolid.sc.service.BoardService 인터페이스 추가
- com.moonsolid.sc.service.impl.BoardServiceImpl 클래스 추가
- com.moonsolid.sc.servlet.BoardXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드를 서비스 객체로 이동.
- com.moonsolid.sc.DataLoaderListener 변경
  - 서비스를 객체를 생성.
- com.moonsolid.sc.ServerApp 변경
  - 서블릿에 서비스 객체를 주입.
  
### 3: MemberXxxServlet 에서 비즈니스 로직을 분리.

- com.moonsolid.sc.service.MemberService 인터페이스 추가
- com.moonsolid.sc.service.impl.MemberServiceImpl 클래스 추가
- com.moonsolid.sc.servlet.MemberXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드를 서비스 객체로 이동.
- com.moonsolid.sc.servlet.LoginServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드를 서비스 객체로 이동.
- com.moonsolid.sc.DataLoaderListener 변경
  - 서비스를 객체를 생성.
- com.moonsolid.sc.ServerApp 변경
  - 서블릿에 서비스 객체를 주입.
  
### 4: PlanXxxServlet 에서 비즈니스 로직을 분리.

- com.moonsolid.sc.service.PlanService 인터페이스 변경
- com.moonsolid.sc.service.impl.PlanServiceImpl 클래스 변경
- com.moonsolid.sc.servlet.PlanXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드를 서비스 객체로 이동.
- com.moonsolid.sc.DataLoaderListener 변경
  - 서비스를 객체를 생성.
- com.moonsolid.sc.ServerApp 변경
  - 서블릿에 서비스 객체를 주입.
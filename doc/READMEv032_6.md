# 32_6 - 커맨드 패턴을 적용하여 요청 처리 메서드를 객체화



###  1: 커맨드 패턴의 인터페이스 정의.

- com.eomcs.lms.servlet 패키지를 생성.
- com.eomcs.lms.servlet.Servlet 인터페이스를 정의.

###  2: 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스를 정의.

- listBoard()를 BoardListServlet 클래스로 정의.
- addBoard()를 BoardAddServlet 클래스로 정의.
- detailBoard()를 BoardDetailServlet 클래스로 정의.
- updateBoard()를 BoardUpdateServlet 클래스로 정의.
- deleteBoard()를 BoardDeleteServlet 클래스로 정의.
- listMember()를 MemberListServlet 클래스로 정의.
- addMember()를 MemberAddServlet 클래스로 정의.
- detailMember()를 MemberDetailServlet 클래스로 정의.
- updateMember()를 MemberUpdateServlet 클래스로 정의.
- deleteMember()를 MemberDeleteServlet 클래스로 정의.

### 3.Plan 명령을 처리하는 PlanServlet 클래스 추가

- PlanListServlet 클래스 추가
- PlanAddServlet 클래스 추가
- PlanDetailServlet 클래스 추가
- PlanUpdateServlet 클래스 추가
- PlanDeleteServlet 클래스 추가

### 4.Lesson 요청 처리 메서드를 삭제

- listLesson()를 삭제
- addLesson()를 삭제
- detailLesson()를 삭제
- updateLesson()를 삭제
- deleteLesson() 를 삭제

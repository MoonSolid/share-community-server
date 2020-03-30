# 54_2 - 출력 콘텐트에 HTML 형식 적용





### 1: 게시글 목록을 출력할 때 HTML 형식으로 콘텐트를 출력.

- com.moonsolid.sc.servlet.BoardListServlet 변경

### 2: 웹브라우저에게 게시글 데이터 입력을 요구.

- com.moonsolid.sc.servlet.BoardAddFormServlet 추가
  - 웹브라우저에게 게시글 데이터 입력을 요구하는 HTML을 보내도록 변경.
- com.moonsolid.sc.servlet.BoardListServlet 변경
  - /board/addForm 을 요청하는 링크를 추가.


### 3: 웹브라우저가 보낸 데이터 받기

- com.moonsolid.sc.ServerApp 변경
  - request-uri에서 자원의 경로와 데이터를 분리.
  - 예) /board/add?title=aaaa
  - 자원의 경로: /board/add
  - 데이터: title=aaaa

### 4: 웹브라우저가 보낸 게시글 데이터 저장

- com.moonsolid.sc.servlet.BoardAddServlet 변경
  - 웹브라우저가 보낸 게시글을 입력.
  - 웹브라우저에게 게시글 입력 결과를 보내도록 변경.

### 5: 게시글 상세 정보를 출력하기

- com.moonsolid.sc.servlet.BoardDetailServlet 변경
  - 웹브라우저가 보낸 번호의 게시글을 가져오도록 변경.
  - 웹브라우저에게 게시글 상세 정보를 HTML 형식으로 만들어 보내도록 변경.
- com.moonsolid.sc.servlet.BoardListServlet 변경
  - /board/detail 을 요청하는 링크를 추가.  
  
### 6: 게시글 삭제하기

- com.moonsolid.sc.servlet.BoardDeleteServlet 변경
  - 웹브라우저가 보낸 번호의 게시글을 삭제.
  - 웹브라우저에게 게시글 삭제 결과를 HTML 형식으로 만들어 보내도록 변경.
- com.moonsolid.sc.servlet.BoardDetailServlet 변경
  - /board/delete 을 요청하는 링크를 추가.
  
### 7: 게시글 변경폼 만들기

- com.moonsolid.sc.servlet.BoardDetailServlet 변경
  - /board/updateForm 을 요청하는 링크를 추가.
- com.moonsolid.sc.servlet.BoardUpdateFormServlet 추가
  - 웹브라우저에게 게시글 데이터 변경을 요구하는 HTML을 보내도록 변경.

### 8: 게시글 변경하기

- com.moonsolid.sc.servlet.BoardUpdateServlet 변경
  - 웹브라우저가 보낸 게시글을 변경.
  - 웹브라우저에게 게시글 변경 결과를 보내도록 변경.
  
### 9: 회원 관리 서블릿을 모두 변경

- com.moonsolid.sc.dao.MemberDao 변경
  - default 메서드를 추상 메서드로 전환한다.
- com.moonsolid.sc.servlet.MemberAddFormServlet 추가
- com.moonsolid.sc.servlet.MemberXxxServlet 변경
- com.moonsolid.sc.ServerApp 변경

### 10: 일정 관리 서블릿을 모두 변경

- com.moonsolid.sc.dao.PlanDao 변경
  - default 메서드를 추상 메서드로 전환한다.
- com.moonsolid.sc.servlet.PlanAddFormServlet 추가
- com.moonsolid.sc.servlet.PlanXxxServlet 변경

### 11: 사진게시글 관리 서블릿을 모두 변경

- com.moonsolid.sc.servlet.PhotoBoardAddFormServlet 추가
- com.moonsolid.sc.servlet.PhotoBoardXxxServlet 변경

### 12: 로그인 서블릿을 모두 변경

- com.moonsolid.sc.servlet.LoginFormServlet 추가
- com.moonsolid.sc.servlet.LoginServlet 변경
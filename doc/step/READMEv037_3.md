# 37_3 - Application Server 구조로 변경: 통신 규칙2 추가 및 Servlet, DAO에 적용 

## 

### 1: 서버가 클라이언트에게 추가 데이터 입력을 요구할 수 있도록 통신 규칙을 변경. 
규칙2) 사용자 입력을 포함하는 경우
```
[클라이언트]                                        [서버]
서버에 연결 요청        -------------->           연결 승인
명령(CRLF)              -------------->           명령처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 2: '통신 규칙2'에 따라 게시글 번호를 입력 받을 수 있도록 BoardDetailServlet을 변경. 

- com.moonsolid.sc.servlet.BoardDetailServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현.
  - '통신 규칙2'에 따라 클라이언트에게 상세 조회할 게시글의 번호를 요구.
  - '통신 규칙1'에 따라 응답.
- com.moonsolid.sc.ServerApp 변경
  - '/board/detail' 명령을 처리할 서블릿을 맵에 추가.

### 3: '통신 규칙2'에 따라 게시글을 입력 받을 수 있도록 BoardAddServlet을 변경. 

- com.moonsolid.sc.servlet.BoardAddServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현.
  - '통신 규칙2'에 따라 클라이언트에게 게시글을 요구.
  - '통신 규칙1'에 따라 응답.
- com.moonsolid.sc.ServerApp 변경
  - '/board/add' 명령을 처리할 서블릿을 맵에 추가.

### 4: '통신 규칙2'에 따라 게시글을 변경할 수 있도록 BoardUpdateServlet을 변경. 

- com.moonsolid.sc.servlet.BoardUpdateServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현.
  - '통신 규칙2'에 따라 클라이언트에게 게시글 변경을 요구.
  - '통신 규칙1'에 따라 응답.
- com.moonsolid.sc.ServerApp 변경
  - '/board/update' 명령을 처리할 서블릿을 맵에 추가.
  
  
### 5: '통신 규칙2'에 따라 게시글을 삭제할 수 있도록 BoardDeleteServlet을 변경. 

- com.moonsolid.sc.servlet.BoardDeleteServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현.
  - '통신 규칙2'에 따라 클라이언트에게 게시글 번호를 요구.
  - '통신 규칙1'에 따라 응답.
- com.moonsolid.sc.ServerApp 변경
  - '/board/delete' 명령을 처리할 서블릿을 맵에 추가.
  
### 6: '통신 규칙2'에 따라 동작하도록  Servlet 클래스 변경. 

- com.moonsolid.sc.servlet.MemberXxxServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현.
  - '통신 규칙2'에 따라 변경.
  - '통신 규칙1'에 따라 응답.
- com.moonsolid.sc.servlet.PlanXxxServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현.
  - '통신 규칙2'에 따라 변경.
  - '통신 규칙1'에 따라 응답.
- com.moonsolid.sc.ServerApp 변경
  - '/member/*' 명령을 처리할 서블릿을 맵에 추가.
  - '/plan/*' 명령을 처리할 서블릿을 맵에 추가.
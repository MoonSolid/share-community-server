# 54_3 - Apache HttpComponents 라이브러리를 이용하여 웹서버 구현



### 1: Apache HttpComponents 라이브러리를 프로젝트에 적용.

- build.gradle 변경
  - 의존 라이브러리를 추가.

### 2: HTTP 요청을 받을 때 HttpComponents 라이브러리에 있는 클래스를 사용하여 처리.

- com.moonsolid.sc.ServerApp 변경

### 3: 서블릿의 request handler의 파라미터를 변경.

- com.moonsolid.sc.servlet.XxxSevlet 변경
  - request handler의 파라미터를 PrintStream 에서 PrintWriter로 변경.

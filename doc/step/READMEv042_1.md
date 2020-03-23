# 42_1 - 사용자 로그인 기능 추가

## 

### 1: 사용자 로그인 기능 추가.

- com.moonsolid.sc.dao.MemberDao 변경
  - 이메일과 암호를 가지고 사용자를 조회하는 메서드를 추가.
  - Member findByEmailAndPassword(String email, String password)
- com.moonsolid.sc.dao.mariadb.MemberDaoImpl 변경
  - MemberDao에 추가한 메서드를 구현.
  - insert(), update()의 SQL 문에서 암호를 입력하거나 변경할 때 
    password() SQL 함수로 인코딩하도록 SQL 문을 변경.
- com.moonsolid.sc.servlet.LoginServlet 추가
  - 사용자로부터 이메일과 암호를 입력받아 인증을 수행.
- com.moonsolid.sc.ServerApp 변경
  - "/auth/login" 명령을 처리할 LoginServlet 객체를 맵에 추가.
  
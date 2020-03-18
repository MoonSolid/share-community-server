# 37_4 - Application Server 구조로 변경: 검색 기능 추가 



## 

### 1: 회원 검색 기능을 추가.



- com.moonsolid.sc.dao.MemberDao 변경
  - findByKeyword() 메서드 추가
- com.moonsolid.sc.dao.mariadb.MemberDaoImpl 변경
  - findByKeyword() 메서드 구현
- com.moonsolid.sc.servlet.MemberSearchServlet 추가
  - 클라이언트에게 검색 키워드를 요청.
  - 클라이언트가 보낸 키워드를 가지고 회원을 검색.
  - 검색한 결과를 가지고 출력 내용을 생성.
  - 클라이언트에게 보낸다.
- com.moonsolid.sc.ServerApp 변경
  - '/member/search' 명령을 처리할 MemberSearchServlet 객체를 등록.
  
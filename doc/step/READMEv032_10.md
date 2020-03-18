# 32_10 - 인터페이스를 이용하여 DAO 호출 규칙을 통일하기 



###  1: BoardXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현.

- com.moonsolid.sc.dao.BoardDao 인터페이스 생성.
- com.moonsolid.sc.dao.BoardObjectFileDao 클래스를 변경.
  - BoardDao 인터페이스를 구현.
- com.moonsolid.sc.dao.json.BoardJsonFileDao 클래스를 변경.
  - BoardDao 인터페이스를 구현.
- com.moonsolid.sc.servlet.BoardXxxServlet 클래스를 변경.
  - DAO 레퍼런스 타입을 BoardDao 인터페이스로 변경.
- com.moonsolid.sc.DataLoaderListener 변경.
- com.moonsolid.sc.ServerApp 변경.

###  2: PlanXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현.

- com.moonsolid.sc.dao.PlanDao 인터페이스 생성.
- com.moonsolid.sc.dao.PlanObjectFileDao 클래스를 변경.
  - PlanDao 인터페이스를 구현.
- com.moonsolid.s.cdao.json.PlanJsonFileDao 클래스를 변경.
  - PlanDao 인터페이스를 구현.
- com.moonsolid.sc.servlet.PlanXxxServlet 클래스를 변경.
  - DAO 레퍼런스 타입을 PlanDao 인터페이스로 변경.
- com.moonsolid.sc.DataLoaderListener 변경.
- com.moonsolid.sc.ServerApp 변경.

###  3: MemberXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현.

- com.moonsolid.sc.dao.MemberDao 인터페이스 생성.
- com.moonsolid.sc.dao.MemberObjectFileDao 클래스를 변경.
  - MemberDao 인터페이스를 구현.
- com.moonsolid.sc.dao.json.MemberJsonFileDao 클래스를 변경.
  - MemberDao 인터페이스를 구현.
- com.moonsolid.sc.servlet.MemberXxxServlet 클래스를 변경.
  - DAO 레퍼런스 타입을 MemberDao 인터페이스로 변경.
- com.moonsolid.sc.DataLoaderListener 변경.
- com.moonsolid.sc.ServerApp 변경.



  

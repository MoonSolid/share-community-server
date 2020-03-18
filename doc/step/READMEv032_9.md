# 32_9 - 파일에 데이터를 저장할 때 JSON 형식을 사용



###  1: JSON 형식으로 데이터를 저장하고 로딩할 수퍼 클래스를 정의.

- com.moonsolid.sc.dao.json 패키지를 생성.
- com.moonsolid.sc.dao.json.AbstractJsonFileDao 클래스를 생성.

###  2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경.

- com.moonsolid.sc.dao.BoardObjectFileDao 변경.
  - 상속 받은 필드와 메서드를 사용.
  - indexOf()를 오버라이딩

### 3: PlanObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경.

- com.moonsolid.sc.dao.PlanObjectFileDao 변경.
  - 상속 받은 필드와 메서드를 사용.
  - indexOf()를 오버라이딩.

###  4: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- com.moonsolid.sc.dao.MemberObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

###  5: XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 서블릿 클래스를 변경.

- com.moonsolid.sc.servlet.BoardXxxServlet 변경.
- com.moonsolid.sc.servlet.PlanXxxServlet 변경.
- com.moonsolid.sc.servlet.MemberXxxServlet 변경.

###  6: 애플리케이션이 시작할 때 XxxObjectFileDao 대신 XxxJsonFileDao를 준비.

- com.moonsolid.sc.DataLoaderListener 변경.

###  7: XxxObjectFileDao 대신 XxxJsonFileDao를 서블릿 객체에 주입.

- com.moonsolid.sc.ServerApp 변경.

### 8.build.gradle 스크립트 수정

- Gson 라이브러리 추가

  ```
  https://mvnrepository.com/search?q=Gson 
  ```

  

  
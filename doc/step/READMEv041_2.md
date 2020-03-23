# 41_2 - 트랜잭션 관리자를 사용하는 코드를 캡슐화



## 

### 1: 트랜잭션 관리자를 사용하는 코드를 캡슐화하여 별도의 클래스로 분리.

- com.moonsolid.sql.TransactionTemplate 추가
  - 트랜잭션 관리자를 사용하는 코드를 메서드로 정의.
- com.moonsolid.sql.TransactionCallback 인터페이스 추가
  - TransactionTemplate이 작업을 실행할 때 호출할 메서드 규칙을 정의.
  
### 2: 트랜잭션을 사용할 곳에 TransactionTemplate을 적용.

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 변경
  - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate을 사용.
- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 변경
  - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate을 사용.
- com.moonsolid.sc.servlet.PhotoBoardDeleteServlet 변경
  - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate을 사용.
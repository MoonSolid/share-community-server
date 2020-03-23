# 43_2 - MyBatis + 트랜잭션 적용



### 1: SqlSession 프록시 추가.

- com.moonsolid.sc.SqlSessionProxy 클래스 추가
  - close()를 호출할 때 닫지 않도록 오버라이딩.
  
### 2: SqlSessionFactory 프록시 추가.

- com.moonsolid.sc.SqlSessionFactoryProxy 클래스 추가
  - 생성한 SqlSession 객체를 스레드에 보관하기 위해 ThreadLocal 필드를 추가.
  - openSession(boolean) 메서드 변경.

### 3: PlatformTransactionManager 변경.

- com.moonsolid.sql.PlatformTransactionManager 변경
  - Connection 대신 SqlSession을 다루도록 변경. 
- com.moonsolid.sql.DataLoaderListener 변경
  - PlatformTransactionManager를 준비할 때 DataSource 대신 SqlSessionFactory를 주입.
- com.moonsolid.sql.DataSource 삭제
- com.moonsolid.sql.ConnectionProxy 삭제

### 4: 스레드 작업을 종료했을 때 SqlSession을 닫도록 변경.

- com.moonsolid.sc.ServerApp 변경

### 5: DAO에서 commit()/rollback() 제거.

- com.moonsolid.sc.dao.mariadb.*DaoImpl 변경
  - commit() / rollback() 호출 코드 제거.

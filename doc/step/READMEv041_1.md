# 41_1 - Connection Pool 도입



### 1: DB 커넥션 풀 객체를 생성.

- com.moonsolid.sc.DataSource 추가
  - ConnectionFactory를 DataSource로 이름을 변경.
  
### 2: PlatformTransactionManager 를 변경.

- com.moonsolid.sc.PlatformTransactionManager 변경
  - ConnectionFactory 대신 DataSource를 사용하도록 변경.

### 3: DataSource를 사용하도록 DAO를 변경.

- com.moonsolid.sc.dao.mariadb.XxxDaoImpl 변경
  - ConnectionFactory 대신 DataSource를 사용하도록 변경.
  
### 4: DataSource를 DAO에 주입.

- com.moonsolid.sc.DataLoaderListener 변경
  - ConnectionFactory 대신 DataSource 객체를 생성.
  - DAO에 DataSource를 주입.
  
### 5: 클라이언트 요청을 처리한 후 Connection을 닫지 말고 반납.

- com.moonsolid.sc.ServerApp 변경
  - 클라이언트에게 응답한 후 스레드에서 커넥션 객체를 제거.
  - 제거된 커넥션 객체는 재사용하기 위해 닫지 않는다.
  

###  6:ConnectionFactory 삭제

- com.moonsolid.util.ConnectionFactory 삭제
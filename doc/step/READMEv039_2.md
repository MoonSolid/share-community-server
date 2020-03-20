# 39_2 - Connection 개별화하기: 커넥션 객체 생성에 Factory Method 패턴 적용



### 1: 커넥션을 생성할 때 팩토리 메서드를 사용.

- com.moonsolid.sc.ConnectionFactory 추가
  - Connection 객체를 생성하는 메서드를 추가.
- com.moonsolid.sc.DataLoaderListener 변경
  - ConnectionFactory 객체를 준비.
  - DAO 구현체에 ConnectionFactory 객체를 주입.
- com.moonsolid.sc.dao.mariadb.XxxDaoImpl 변경
  - 생성자에서 ConnectionFactory 객체를 받는다.
  - 직접 Connection 객체를 생성하는 대신에 
  ConnectionFactory 객체를 통해 Connection 얻는다.



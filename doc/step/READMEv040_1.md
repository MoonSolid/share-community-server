# 40_1 - Connection을 스레드에 보관하기: ThreadLocal을 사용하여 스레드에 값 보관



### 1: 커넥션 팩토리에서 생성한 Connection 객체를 스레드에 보관.

- com.moonsolid.sc.ConnectionFactory 변경
  - getConnection() 변경
    - 스레드에 보관된 Connection 객체가 없다면, 새로 생성하여 리턴.
    - 새로 생성한 Connection 객체는 스레드에 보관.
    - 스레드에 보관된 Connection 객체가 있다면 그 객체를 꺼내 리턴.
    
### 2: 클라이언트에 응답을 한 후 스레드에 보관된 Connection 객체를 제거.

- com.moonsolid.sc.ConnectionFactory 변경
  - Thread에 보관된 Connection 객체를 제거하는 메서드를 추가.
  - removeConnection()
- com.moonsolid.sc.DataLoaderListener 변경
  - ServerApp에서 ConnectionFactory를 사용할 수 있도록 맵에 보관하여 리턴.
- com.moonsolid.sc.ServerApp 변경
  - 클라이언트 요청을 처리한 후에 
    ConnectionFactory를 통해 Thread에서 Connection을 제거.
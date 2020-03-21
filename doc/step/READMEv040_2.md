# 40_2 - Connection을 스레드에 보관: 한 스레드에서 Connection 을 여러 번 사용할 때 발생하는 문제점 해결

## 

### 1: Connection 프록시 정의.

- com.moonsolid.sc.ConnectionProxy 추가
  - close()를 구현.
    - 호출되면 아무런 작업을 하지 않게 변경.
    - 커넥션을 닫지 않는다.
  - realClose() 추가.
    - 실제 커넥션을 닫는 작업을 한다.
  - 그외 메서드는 원래 Connection 객체에 위임.
### 2: ConnectionFactory가 ConnectionProxy 객체를 리턴.

- com.moonsolid.sc.ConnectionFactory 변경
  - getConnection() 변경	
  - 원래의 Connection 객체 대신에 ConnectionProxy를 리턴.
  
### 3: 스레드에서 Connection을 제거하기 전에 서버와의 연결을 끊도록 변경.

- com.moonsolid.util.ConnectionFactory 변경
  - removeConnection()이 스레드에서 제거한 Connection을 리턴하게 변경.
- com.moonsolid.sc.ServerApp 변경
  - ConnectionFactory에서 리턴 받은 Connection 객체에 대해 
    realClose()를 호출.
  
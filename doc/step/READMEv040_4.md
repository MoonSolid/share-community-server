# 40_4 - Connection을 스레드에 보관: 트랜잭션 관리자 추가

## 

### 1: 트랜잭션 제어 코드를 캡슐화.

- com.moonsolid.sc.PlatformTransactionManager 추가
  - 트랜잭션을 시작시키고, 커밋/롤백하는 메서드를 정의.
  
### 2: PhotoBoardAddServlet에 트랜잭션 관리자를 적용.

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 변경
  - PlatformTransactionManager를 주입.
  - 트랜잭션 관리자를 통해 트랜잭션을 제어.
  
### 3: PhotoBoardUpdateServlet에 트랜잭션 관리자를 적용.

- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 변경
  - PlatformTransactionManager를 주입.
  - 트랜잭션 관리자를 통해 트랜잭션을 제어.
  
### 4: PhotoBoardDeleteServlet에 트랜잭션 관리자를 적용.

- com.moonsolid.sc.servlet.PhotoBoardDeleteServlet 변경
  - PlatformTransactionManager를 주입.
  - 트랜잭션 관리자를 통해 트랜잭션을 제어.

### 5: DataLoaderListener에서 트랜잭션 관리자를 준비.

- com.moonsolid.sc.DataLoaderListener 변경
  - PlatformTransactionManager 객체를 준비.

### 6: 트랜잭션 관리자를 서블릿에 주입.

- com.moonsolid.sc.ServerApp 변경
  - 맵에서 PlatformTransactionManager 객체를 꺼내 서블릿 객체에 주입.
  
# 40_3 - Connection을 스레드에 보관: 트랜잭션 적용



### 메서드 별로 커넥션을 개별화 한 상태에서 트랜잭션을 적용

### 1: PhotoBoardAddServlet에 트랜잭션을 적용

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 변경
  - ConnectionFactory를 주입.
  - ConnectionFactory를 통해 Connection을 얻은 후에 트랜잭션을 제어.

### 2: PhotoBoardUpdateServlet에 트랜잭션을 적용.

- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 변경
  - ConnectionFactory를 주입.
  - ConnectionFactory를 통해 Connection을 얻은 후에 트랜잭션을 제어.
  
### 3: PhotoBoardDeleteServlet에 트랜잭션을 적용.

- com.moonsolid.sc.servlet.PhotoBoardDeleteServlet 변경
  - ConnectionFactory를 주입.
  - ConnectionFactory를 통해 Connection을 얻은 후에 트랜잭션을 제어.

### 4: 트랜잭션을 다뤄야 하는 서블릿 객체에 ConnectionFactory를 주입.

- com.moonsolid.sc.ServerApp 변경
  - PhotoBoardAddServlet, PhotoBoardUpdateServlet, PhotoBoardDeleteServlet 객체에
    ConectionFactory를 주입.

### 
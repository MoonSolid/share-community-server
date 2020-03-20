# 38_4 - 트랜잭션 적용 후: 사진 게시글 입력과 첨부 파일 입력을 한 단위로 다루도록변경



### 1: 사진 게시글 입력과 첨부파일 입력을 한 단위로 다루도록 변경 

사진 게시글과 첨부파일을 입력하는 코드를 트랜잭션으로 묶어 한 단위로 다룬다.

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 변경
  - 게시글 입력과 첨부파일 입력 부분을 실행하기 전에 수동 commit으로 설정.
  - 성공하면 commit(), 실패하면 rollback()  실행.
  
### 2: 사진 게시글 변경과 첨부파일 삭제, 입력을 한 단위로 다루도록 변경. 

- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 변경
  - 게시글 변경과 첨부파일 삭제,입력 부분을 실행하기 전에 수동 commit으로 설정.
  - 성공하면 commit(), 실패하면 rollback() 실행.

### 3: 사진 게시글 삭제와 첨부파일 삭제를 한 단위로 다루도록 변경. 

- com.moonsolid.sc.servlet.PhotoBoardDeleteServlet 변경
  - 게시글 삭제와 첨부파일 삭제를 실행하기 전에 수동 commit으로 설정.
  - 성공하면 commit(), 실패하면 rollback() 실행.
  
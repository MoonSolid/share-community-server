# 38_1 - 트랜잭션 적용 전: 사진 게시판 추가하기



### 1: `일정사진 게시판` 데이터를 다룰 DAO를 생성.

- com.moonsolid.sc.domain.PhotoBoard 추가
  - 사진 게시물의 데이터 타입을 정의.
- com.moonsolid.sc.dao.PhotoBoardDao 인터페이스 추가
  - 사진 게시물의 CRUD 관련 메서드 호출 규칙을 정의.
- com.moonsolid.sc.dao.mariadb.PhotoBoardDaoImpl 추가
  - PhotoBoardDao 인터페이스를 구현.
- com.moonsolid.sc.DataLoaderListener 변경
  - PhotoBoardDao 객체를 생성.

### 2: '/photoboard/list' 명령을 처리.

- com.moonsolid.sc.servlet.PhotoBoardListServlet 추가
    - 사진 게시물의 목록을 출력.
- com.moonsolid.sc.ServerApp 변경
    - `PhotoBoardListServlet` 객체를 생성하여 커맨드 맵에 보관.



### 3: '/photoboard/detail' 명령을 처리.

- com.moonsolid.sc.domain.PhotoBoard 변경
  - 일정 정보를 담을 Plan 타입의 인스턴스 필드를 추가.
- com.moonsolid.sc.dao.mariadb.PhotoBoardDaoImp 변경
  - findByNo(사진게시글번호) 메서드 변경.
  - 사진 게시글의 상세정보를 가져올 때 sc_photo와 sc_plan을 조인.
  - sc_photo 데이터는 PhotoBoard에 저장하고, sc_plan 데이터는 Plan에 저장. 
- com.moonsolid.sc.servlet.PhotoBoardDetailServlet 추가
    - 사진 게시물의 상세정보를 출력.
- com.moonsolid.sc.ServerApp 변경
    - `PhotoBoardDetailServlet` 객체를 생성하여 커맨드 맵에 보관.

### 4: '/photoboard/add' 명령을 처리.

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 추가
    - 사진 게시물을 입력.
- com.moonsolid.sc.ServerApp 변경
    - `PhotoBoardAddServlet` 객체를 생성하여 커맨드 맵에 보관.

### 5: '/photoboard/update' 명령을 처리.

- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 추가
    - 사진 게시물을 변경한다. 
- com.moonsolid.sc.ServerApp 변경
    - `PhotoBoardUpdateServlet` 객체를 생성하여 커맨드 맵에 보관.

### 6: '/photoboard/delete' 명령을 처리.

- com.moonsolid.sc.servlet.PhotoBoardDeleteServlet 추가
    - 사진 게시물을 삭제. 
- com.moonsolid.sc.ServerApp 변경
    - `PhotoBoardDeleteServlet` 객체를 생성하여 커맨드 맵에 보관.

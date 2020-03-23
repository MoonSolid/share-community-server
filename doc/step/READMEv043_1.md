# 43_1 - MyBatis SQL 맵퍼 프레임워크를 사용하여 JDBC 코드를 대체

## 

## 

### 1: 프로젝트에 MyBatis 라이브러리를 추가.

- build.gradle   
  - 의존 라이브러리 블록에서 `mybatis` 라이브러리를 등록.
- gradle을 이용하여 eclipse 설정 파일을 갱신.
  - `$ gradle eclipse`
- 이클립스에서 프로젝트를 갱신.
  
### 2: `MyBatis` 설정 파일을 준비.

- com/moonsolid/sc/conf/jdbc.properties
    - `MyBatis` 설정 파일에서 참고할 DBMS 접속 정보를 등록.
- com/moonsolid/sc/conf/mybatis-config.xml
    - DBMS 서버의 접속 정보를 갖고 있는 jdbc.properties 파일의 경로를 등록.
    - DBMS 서버 정보를 설정.
    - DB 커넥션 풀을 설정.

### 3: BoardDaoImpl 에 Mybatis를 적용.

- com.moonsolid.sc.dao.mariadb.BoardDaoImpl 클래스 변경
  - JDBC 코드를 Mybatis 클래스로 대체.
- com/moonsolid/sc/mapper/BoardMapper.xml 추가
- com/moonsolid/sc/conf/mybatis-config.xml 변경 
  - BoardMapper 파일의 경로를 등록.
- com.moonsolid.sc.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비.
  - BoardDaoImpl 에 주입.

### 4: MemberDaoImpl 에 Mybatis를 적용.

- com.moonsolid.sc.dao.mariadb.MemberDaoImpl 클래스 변경
  - JDBC 코드를 Mybatis 클래스로 대체.
- com/moonsolid/sc/mapper/MemberMapper.xml 추가
- com/moonsolid/sc/conf/mybatis-config.xml 변경 
  - MemberMapper 파일의 경로를 등록.
- com.moonsolid.sc.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비.
  - MemberDaoImpl 에 주입.

### 5: PlanDaoImpl 에 Mybatis를 적용.

- com.moonsolid.sc.dao.mariadb.PlanDaoImpl 클래스 변경
  - JDBC 코드를 Mybatis 클래스로 대체.
- com/moonsolid/sc/mapper/PlanMapper.xml 추가
- com/moonsolid/sc/conf/mybatis-config.xml 변경 
  - PlanMapper 파일의 경로를 등록.
- com.moonsolid.sc.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비.
  - PlanDaoImpl 에 주입.

### 6 PhotoBoardDaoImpl 에 Mybatis를 적용한다.

- com.moonsolid.sc.domain.PhotoBoard 클래스 변경
  - PhotoFile 목록 필드를 추가.
- com.moonsolid.sc.dao.mariadb.PhotoBoardDaoImpl 클래스 변경
- com/moonsolid/sc/mapper/PhotoBoardMapper.xml 추가
- com/moonsolid/sc/conf/mybatis-config.xml 변경 
  - PhotoBoardMapper 파일의 경로를 등록.
- com.moonsolid.sc.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비.
  - PhotoBoardDaoImpl 에 주입.
- com.moonsolid.sc.servlet.PhotoBoardDetailServlet 변경
  - PhotoFileDao 주입을 제거.
  - PhotoBoardDao로 첨부파일까지 모두 가져온다.
- com.moonsolid.sc.ServerApp 변경
  - PhotoBoardDetailServlet에 PhotoFileDao 주입을 제거.
  
### 7: PhotoFileDaoImpl 에 Mybatis를 적용.

- com.moonsolid.sc.dao.mariadb.PhotoFileDaoImpl 클래스 변경
- com/moonsolid/sc/mapper/PhotoFileMapper.xml 추가
- com/moonsolid/sc/conf/mybatis-config.xml 변경 
  - PhotoFileMapper 파일의 경로를 등록.
- com.moonsolid.sc.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비.
  - PhotoFileDaoImpl 에 주입.

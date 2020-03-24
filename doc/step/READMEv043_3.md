# 43_3 - MyBatis의 dynamic sql 문법 사용



### 1: `sql` 태그를 사용하여 공통 SQL 코드를 분리.

- src/main/resources/com/moonsolid/sc/mapper/*Mapper.xml
  
### 2: `foreach` 태그를를 사용하여 insert 문 생성

사진 게시글의 첨부파일을 입력할 때, 여러 값들을 한 번에 입력하기 

- com.moonsolid.sc.servlet.PhotoBoardAddServlet 변경
  - 파일 목록을 한 번에 insert 하기
- com.moonsolid.sc.servlet.PhotoBoardUpdateServlet 변경
  - 파일 목록을 한 번에 insert 하기  
- com.moonsolid.sc.dao.PhotoFileDao 변경
  - insert(PhotoFile) 메서드를 insert(PhotoBoard) 로 변경한다.
- com.moonsolid.sc.dao.mariadb.PhotoFileDaoImpl 변경
  - insert()를 변경한다.
- src/main/resources/com/moonsolid/sc/mapper/PhotoFileMapper.xml 변경
  - insertPhotoFile SQL 변경.
  - `foreach` 태그를 적용하여 여러 개의 값을 한 번에 insert .
  
### 3: `set` 태그를 사용하여 update할 때 일부 컬럼만 변경.

데이터를 변경할 때 일부 컬럼의 값만 변경

- src/main/resources/com/moonsolid/sc/mapper/PlanMapper.xml 변경
  - updatePlan SQL을 변경.
- com.moonsolid.util.Prompt 변경
  - 클라이언트가 보낸 값을 지정된 타입으로 변경할 수 없을 때의 예외를 처리.
- com.moonsolid.sc.servlet.PlanUpdateServlet 변경
  - 클라이언트가 값을 보내지 않은 항목은 빈문자열이나 null, 0으로 설정.
  - 이 경우 update 대상 컬럼에서 제외된다.
- src/main/resources/com/moonsolid/sc/mapper/MemberMapper.xml 변경
  - updateMember SQL을 변경한다.
- com.moonsolid.scservlet.MemberUpdateServlet 변경
  - 클라이언트가 값을 보내지 않은 항목은 빈문자열이나 null, 0으로 설정.
  - 이 경우 update 대상 컬럼에서 제외된다.
  
### 4: `where` 태그를 사용하여 검색 조건을 변경. 

일정을 검색(일정명, 내용, 장소, 메모, 비용)하는 기능을 추가.
검색 조건은 AND 연산으로 처리.

- src/main/resources/com/moonsolid/sc/mapper/PlanMapper.xml 변경
  - selectPlan SQL문을 변경.
  - `where` 태그를 적용하여 조건을 만족하는 데이터를 찾는다. 
- com.moonsolid.sc.dao.PlanDao 변경
  - findByKeyword() 메서드를 추가.
- com.moonsolid.sc.servlet.PlanSearchServlet 추가 
  - 검색 요청을 처리.
- com.moonsolid.sc.ServerApp 변경
  - PlanSearchServlet 객체 등록 





  

  

  

  
# 45_2 - Mybatis를 이용하여 DAO 구현체 자동 생성



### 1: BoardServiceImpl 에 대해서 Mybatis DAO 자동 생성기를 적용.

- com.moonsolid.sc.service.impl.BoardServiceImpl2 추가
  - BoardDao 구현체를 직접 주입하는 대신에 SqlSessionFactory를 주입.
  - BoardDao를 사용할 때 마다 SqlSession 객체를 통해 생성. 
- com.moonsolid.sc.DataLoaderListener 변경
  - BoardService 구현체를 BoardServiceImpl2로 교체.

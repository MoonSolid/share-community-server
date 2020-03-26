# 51_1 - Spring IoC 컨테이너와 MyBatis 프레임워크 연동



### 1: Mybatis를 Spring IoC 컨테이너와 연결할 때 사용할 의존 라이브러리 추가.

- `build.gradle` 변경
- Gradle 빌드 도구를 사용하여 라이브러리 파일을 프로젝트로 추가

### 2: Mybatis에서 관리했던 DB 커넥션풀(DataSource)을 Spring IoC 컨테이너로 이동.

Spring IoC 컨테이너를 도입하면, 가능한 대부분의 객체를 IoC 컨테이너에서 관리하도록 변경.
Mybatis가 사용하는 객체도 Spring IoC 컨테이너에서 관리하도록 관리 주체를 변경.

- com.moonsolid.sc.AppConfig 변경
  - DataSource 객체를 리턴하는 팩토리 메서드를 추가.
  
### 3: PlatformTransactionManager를 Spring 것으로 교체.

- com.moonsolid.sql.PlatformTransactionManager 삭제
- com.moonsolid.sql.TransactionTemplate 삭제
- com.moonsolid.sql.TransactionCallback 삭제
- com.moonsolid.sc.AppConfig 변경
  - 기존의 PlatformTransactionManager를 생성하는 메서드를 변경.
  - transactionManager()를 변경.  

### 4: 기존의 서비스 객체의 트랜잭션 관리자를 Spring의 것으로 교체.

- com.moonsolid.sc.service.impl.PhotoBoardServiceImpl 변경
  - 트랜잭션 관련 클래스를 Spring의 것으로 교체.
  - 트랜잭션을 다루는 코드를 Spring 사용법에 따라 변경.
  
### 5: Mybatis의 SqlSessionFactory를 Spring IoC 컨테이너 용으로 준비.

- com.moonsolid.sc.AppConfig 변경
  - `mybatis-spring` 라이브러리에서 제공하는 어댑터를 사용하여 SqlSessionFactory를 교체
  - sqlSessionFactory()를 변경.
- com.moonsolid.sc.conf.mybatis-config.xml 삭제
- com.moonsolid.sql.SqlSessionFactoryProxy 삭제
- com.moonsolid.sql.SqlSessionProxy 삭제

### 6: DAO 생성기를 Mybatis-Spring 어댑터로 교체.

- com.moonsolid.sc.AppConfig 변경
  - @MapperScan 애노테이션으로 설정.
  - 수동으로 DAO 객체를 만드는 팩토리 메서드를 모두 제거.
  - MybatisDaoFactory 객체를 생성하는 팩토리 메서드를 삭제.
- com.moonsolid.sql.MybatisDaoFactory 삭제
- com.moonsolid.sc.ServerApp 변경
  - SqlSessionFactory 사용 코드를 제거.
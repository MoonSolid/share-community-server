# 51_2 - Spring IoC 설정 파일(Java Config)을 분리

## 

### 1: 데이터베이스 관련 설정을 분리

- com.moonsolid.sc.DatabaseConfig 추가
  - AppConfig에서 데이터베이스 관련 객체 생성 코드를 이동.
- com.moonsolid.sc.AppConfig 변경
  
### 2: Mybatis 관련 설정을 분리

- com.moonsolid.sc.MybatisConfig 추가
  - AppConfig에서 Mybatis 관련 객체 생성 코드를 이동.
- com.moonsolid.sc.AppConfig 변경

### 3: Spring IoC 컨테이너를 생성할 때 Java Config를 모두 지정.

- com.moonsolid.sc.ContextLoaderListener 변경
  - Spring IoC 컨테이너 생성 코드를 변경.
  
### 4: @Configuration 애노테이션을 사용하여 Java Config 를 설정.

- com.moonsolid.sc.DatabaseConfig 변경
  - @Configuration 애노테이션을 추가.
- com.moonsolid.sc.MybatisConfig 변경
  - @Configuration 애노테이션을 추가.
- com.moonsolid.sc.ContextLoaderListener 변경
  - Spring IoC 컨테이너를 생성할 때 Java Config로 AppConfig 만 지정.
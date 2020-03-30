# 53_2 - Log4j 2.x 를 사용하여 애플리케이션 로그 처리



##  

### 1: Log4j 2.x 라이브러리를 추가.

- 라이브러리 정보 알아내기
- build.gradle
    - `log4j` 라이브러리 정보를 추가한다.


### 2: Log4j 설정 파일을 추가.

- src/main/resources/log4j2.xml 추가


### 3: 각 클래스의 로그 출력을 Log4j2로 전환.

- com.moonsolid.sc.ServerApp 변경
- com.moonsolid.sc.ContextLoaderListener 변경
- com.moonsolid.sc.AppConfig 변경
- com.moonsolid.sc.DatabaseConfig 변경
- com.moonsolid.sc.MybatisConfig 변경

### 4: Mybatis에 log4j2를 설정.

- com.moonsolid.sc.MybatisConfig 변경
  - org.apache.ibatis.logging.LogFactory.useLog4J2Logging() 호출
  -  log4j 기능을 활성화.




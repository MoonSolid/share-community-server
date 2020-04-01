# 55_1 - JavaEE 의 Servlet 기술 도입

## 



### 1: 서블릿 컨테이너를 설치 및 설정.

- tomcat.apache.org 사이트에서 서블릿 컨테이너를 다운로드 
- 특정 폴더에 압축을 풀고, 설정.
  - 관리자 ID/PWD를 등록.
    - $apache-tomcat/conf/tomcat-users.xml
  - 관리자 로그인을 활성화.
    - $apache-tomcat/conf/Catalina/localhost/manager.xml

### 2: JavaEE Servlet 기술을 사용하기 위한 라이브러리를 프로젝트에 적용.

- build.gradle 변경
  - search.maven.org 에서 'servlet-api'를 추가
  - 의존 라이브러리 옵션은 compileOnly로 설정.
  
  - 이클립스 설정 파일을 갱신.

### 3: JavaEE의 Servlet 기술을 사용하여 Spring IoC 컨테이너를 준비.

- com.moonsolid.sc.ContextLoaderListener 변경
  - Servlet 기술에게 제공하는 ServletContextListener를 구현.
  - Spring IoC 컨테이너를 준비. 
- com.moonsolid.sc.context 패키지 및 하위 클래스 삭제

### 4: 기존의 서블릿 클래스를 JavaEE의 Servlet 기술을 적용하여 변경.

- com.moonsolid.sc.servlet.XxxServlet 변경
- com.moonsolid.scServerApp 삭제

### 5: 웹애플리케이션을 빌드 .

- build.gradle 변경
  - 웹애플리케이션 배치 파일을 생성하기 위해 'war' 플러그인을 추가.
- 'gradle build'를 실행.
  - 'build/libs/bitcamp-project-server.war' 파일 생성.

### 6: 톰캣 서버에 배치.

- $apache-tomcat/webapps/ 폴더에 war 파일을 이동.
- 톰캣 서버를 다시 시작.
  - 톰캣 서버는 bitcamp-project-server.war 파일과 
    동일한 이름으로 폴더를 만들고 압축 해제.
  - 예) $apache-tomcat/webapp/bitcamp-project-server

### 7: URL 링크를 상대로 경로로 변경.

- com.moonsolid.sc.servlet.XxxServlet 변경
  - 절대 경로 대신 상대 경로로 변경.











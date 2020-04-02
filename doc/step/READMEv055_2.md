# 55_2 - 이클립스 웹 프로젝트로 전환

## 

### 1: 이클립스에 톰캣 서버 환경을 추가.

- Window 메뉴/Preferences/Server/Runtime Environment 설정 추가

### 2: 웹애플리케이션 테스트 서버 구축 

- Servers / New Server / Tomcat v9.0 Server at localhost

### 3: 웹프로젝트로 전환

- build.gradle 변경
  - 'eclipse' 플러그인 을 'eclipse-wtp' 플러그인으로 변경
  - 'war' 플러그인 추가 
- 프로젝트 갱신
### 4: 테스트 서버에 웹 프로젝트를 등록.

- Servers 
- 웹프로젝트 추가

### 5: 정적자원 폴더 추가

- src/main/webapp/ 폴더를 생성.

### 6: 웹애플리케이션 기본 HTML 파일 생성

- src/main/webapp/index.html 파일을 생성.

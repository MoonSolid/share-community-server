# 50_1 - Spring IoC 컨테이너 도입하기





### 1: Spring IoC 컨테이너 라이브러리 추가.

- Spring IoC 컨테이너의 라이브러리 정보 찾기
- `build.gradle` 변경
  - 빌드 설정 파일에 의존 라이브러리 정보 추가
- Gradle 빌드 도구를 사용하여 라이브러리 파일을 프로젝트로 가져오기

### 2: Spring IoC 컨테이너의 설정 정보를 제공하는 클래스 추가.

- com.moonsolid.sc.AppConfig 추가
  - Spring IoC 컨테이너가 객체를 생성하기 위해 탐색할 패키지를 설정.
  
  
### 3: Spring IoC 컨테이너를 생성.

- com.moonsolid.util.ApplicationContext 삭제
  - Spring IoC 컨테이너로 대체.
- com.moonsolid.sc.ContextLoaderListener 변경 
  - Spring 프레임워크에서 제공하는 클래스를 사용하여 IoC 컨테이너를 생성.
  - Spring IoC 컨테이너에 들어 있는 객체를 출력.
    - printBeans()를 추가.
- com.moonsolid.sc.ServerApp 변경
  - Spring IoC 컨테이너의 ApplicationContext로 교체.

### 4: @Component 애노테이션을 Spring 것으로 교체.

- com.moonsolid.util.Component 제거
- com.moonsolid.servlet.* 변경
  - Spring의 @Component로 교체.
- com.moonsolid.service.* 변경
  - Spring의 @Component로 교체.
- com.moonsolid.sc.ContextLoaderListener 변경
  - Spring의 @Component로 교체.
### 5: Spring IoC 컨테이너가 자동으로 생성할 수 없는 경우 설정 클래스에서 생성.

- com.moonsolid.sc.AppConfig 변경
  
  - DAO 객체를 생성하는 메서드를 추가.  
- com.moonsolid.sc.ContextLoaderListener 변경
  
  - IoC 컨테이너에 저장할 객체 생성 코드를 제거.
  
  
  
  

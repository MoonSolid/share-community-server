# 46_1 - 객체 생성을 자동화하는 IoC 컨테이너 추가



### 1: IoC 컨테이너 클래스 추가.

- com.moonsolid.util.ApplicationContext 클래스 생성
- 패키지명을 입력받아서 파일 시스템의 경로를 알아낸다.
- 패키지 폴더를 뒤져 모든 파일 이름을 가져온다.
- findFiles()를 추가.
- findFiles()를 findClasses()로 변경.
- listFiles()에 FileFilter를 주입.
- FileFilter에 중첩 파일 제거 조건을 추가.
- findClasses()의 두 번째 파라미터에 패키지 이름을 전달.
- 메서드 선언문에 예외 처리를 추가.
- isConcreteClass()를 추가한다.
- isConcreteClass()를 통해 concrete class를 구분.
- findClasses()에 타입 정보를 추가하는 코드 추가
- createObject()를 추가.
- 생성자 변경: 각 concrete class 에 대해 createObject()를 호출.
- getParameterValues()를 추가.
- 객체풀 역할을 수행할 필드 추가: HashMap<String,Object> objPool
- getParameterValue()를 추가.
- getParameterValue()를 변경.
- findAvailableClass()를 추가.
- isChildClass()를 추가.
- printBeans()를 추가.
- getBean()을 추가.
- getBeanName()을 추가.
- isConcreteClass()를 isComponentClass()로 변경. 
- @Component가 붙은 클래스만 찾아내서 객체를 생성.

### 2:DataLoaderListener 클래스를 ContextLoaderListener 로 변경

- com.moonsolid.sc.DataLoaderListener로변경
- com.moonsolid.sc.ContextLoaderListener 변경
- ApplicationContext 객체를 생성하여 맵에 보관.
- Mybatis 관련 객체를 별도의 Map에 보관하여 ApplicationContext 객체를 생성할 때 넘겨준다.
### 3: 애노테이션을 이용하여 생성된 객체의 이름을 관리.

- com.moonsolid.util.Component 애노테이션 추가
- com.moonsolid.lms.servlet.XxxServlet 변경
  - 클래스에 Component 애노테이션을 적용하여 이름을 지정.
### 4: @Component 애노테이션이 붙은 객체만 관리.

- com.moonsolid.sc.servlet.impl.XxxServiceImpl 변경
  
  - 클래스에 Component 애노테이션을 적용.
  
  

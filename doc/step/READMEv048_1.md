# 48_1 - 인터페이스 대신 애노테이션으로 메서드 구분





##   1: 명령어를 처리할 메서드를 지정할 때 사용할 애노테이션을 정의.

- com.moonsolid.util.RequestMapping 애노테이션 추가

### 2: 명령어를 처리할 메서드에 @RequestMapping 애노테이션을 추가.

- com.moonsolid.sc.servlet.Servlet 인터페이스 삭제
- com.moonsolid.sc.servlet.XxxServlet 변경
  - 메서드 선언부에 @RequestMapping 애노테이션을 추가.

### 3: 특정 애노테이션이 붙은 객체의 이름 목록을 리턴하는 메서드를 추가.

- com.moonsolid.util.ApplicationContext 변경
  - getBeanNamesForAnnotation()을 추가.
  
### 4: @Component 애노테이션이 붙은 객체를 찾도록 변경.

- com.moonsolid.sc.ContextLoaderListener 변경
  - ApplicationContext가 보관하고 있는 객체 중에서 @Component가 붙은 객체만 찾는다. 
  
### 5: @Component가 붙은 객체에서 @RequestMapping이 붙은 메서드가 있는지 찾도록 변경.

- com.moonsolid.sc.ContextLoaderListener 변경
  - @Component가 붙은 객체를 조사하여 @RequestMapping이 선언된 메서드 있는지 알아낸다.
  - getRequestHandler()를 추가.
  
### 6: request handler의 정보를 저장할 클래스를 정의.

- com.moonsolid.util.RequestHandler 추가
  - 객체 주소와, 메서드 정보, 객체 이름을 보관.
- com.moonsolid.sc.ContextLoaderListener 변경
  - request handler를 RequestHandler 객체에 저장.
  
### 7: request handler 목록을 보관할 클래스를 정의.

- com.moonsolid.util.RequestMappingHandlerMapping 추가
  - @RequestMapping 애노테이션이 붙은 메서드의 정보를 보관. 

### 8:  request handler를 목록에 보관.

- com.moonsolid.sc.ContextLoaderListener 변경
  - RequestHandler 객체를 RequestMappingHandlerMapping 객체에 보관.
  
### 9: RequestMappingHandlerMapping 에 보관된 객체를 사용하여 명령을 처리.

- com.moonsolid.sc.ServerApp 변경
  - 공유 맵에서 이 객체를 꺼낸다.
  - RequestMappingHandlerMapping 객체에서 명령을 처리할 메서드를 꺼낸다.
  - 그 메서드를 호출하여 클라이언트에게 응답.
  
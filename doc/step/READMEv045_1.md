# 45_1 - Java Proxy를 이용하여 DAO 구현체 자동 생성

#   

### 1: InvocationHandler에서 SQL을 찾기 쉽도록 DAO 인터페이스 메서드명과 SQL ID를 일치하도록 변경

- src/main/resources/com/moonsolid/sc/mapper/XxxMapper.xml 변경
  - namespace 값을 인터페이스 전체 이름(fully-qualified name)과 일치하도록 변경.
  - 메서드에서 사용할 SQL은 메서드 이름과 일치하도록 변경.
- com.moonsolid.sc.dao.MemberDao 변경
  - findByEmailAndPassword()의 파라미터를 Map 타입으로 변경.
- com.moonsolid.sc.service.impl.MemberServiceImpl 변경
  - findByEmailAndPassword()를 호출할 때 파라미터를 Map에 담아 넘기도록 변경. 

### 2: DAO 생성을 단순화시키는 팩토리 클래스를 정의.

- com.moonsolid.sql.MybatisDaoFactory 클래스 추가
  - DAO 프록시 객체를 생성하는 팩토리 메서드 createDao()를 정의.
  - 인터페이스에 따라 리턴 타입을 다르도록 제네릭을 적용.
  - InvocationHandler 구현체를 람다 문법을 사용하여 로컬 클래스로 정의. 

### 3: DAO 객체 생성에 프록시 생성기를 적용.

- com.moonsolid.sc.dao.* 에서 DAO 구현체 모두 제거
- com.moonsolid.sc.DataLoaderListener 변경
  - MybatisDaoFactory를 이용하여 DAO 구현 객체 생성.



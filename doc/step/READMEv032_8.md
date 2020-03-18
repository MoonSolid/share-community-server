# 32_8 - DAO 클래스의 공통점을 뽑아 수퍼 클래스로 정의(generalization 수행)



### 1: DAO의 공통점을 뽑아 수퍼 클래스로 정의.

- com.moonsolid.sc.dao.AbstractObjectFileDao 클래스를 생성.

###  2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경.

- com.moonsolid.sc.dao.BoardObjectFileDao 변경.
  - 상속 받은 필드와 메서드를 사용.
  - indexOf()를 오버라이딩 .

###  3: PlanObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경.

- com.moonsolid.sc.dao.PlanObjectFileDao 변경.
  - 상속 받은 필드와 메서드를 사용.
  - indexOf()를 오버라이딩 .

### 4: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경.

- com.moonsolid.sc.dao.MemberObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.





  

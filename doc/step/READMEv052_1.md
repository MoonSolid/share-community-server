# 52_1 - 애노테이션을 이용하여 트랜잭션 제어





### 1: 애노테이션으로 트랜잭션을 제어할 수 있도록 해당 기능을 활성화.

- com.moonsolid.sc.DatabaseConfig 변경
  - @Transactional 애노테이션 기능을 활성화시키기 위해서 
    @EnableTransactionManagement 애노테이션을 추가로 선언.


### 2: @Transactional 애노테이션으로 트랜잭션을 제어.

- com.moonsolid.sc.service.impl.PhotoBoardServiceImpl 변경
  - TransactionTemplate을 사용하는 대신에 @Transactional 애노테이션을 사용.
  - add(), update(), delete()을 변경.
  

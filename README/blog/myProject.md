## 혼자하는 실습 프록젝트

1. 여러 스레드가 DB 커넥션을 공유할 때의 문제점과 해결책 I

- 여러 스레드에서 DB 커넥션 객체를 공유할 때의 문제점 이해
- SQL 실행할 때 마다 Connection 생성하기
  - 이점: 다른 스레드의 commit/rollback 작업에 영향을 받지 않는다.
  - 단점: 여러 개의 데이터 변경(insert,update,delete) 작업을 하나의 트랜잭션으로 묶을 수 없다.


2.  여러 스레드가 DB 커넥션을 공유할 때의 문제점과 해결책 II

- 스레드 당 한 개의 DB 커넥션 사용하기
  - 다른 스레드의 commit/rollback 작업에 영향을 받지 않는다.
  - 트랜잭션을 사용할 수 있다.
- ThreadLocal을 사용하여 스레드 별로 Connection 객체를 유지시킨다.
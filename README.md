

## JPA

- sql, data 중심에서 객체 중심 프로그래밍이 가능해진다.
- 요즘에는 `mybatis` 보다 `JPA` 가 훨씬 많이 쓰인다. (국내에서는 아직 mybatis ...)


### application.properties에 신기한 설정?을 해주자
- `spring.jpa.show-sql=true` 로 실행되는 sql 볼 수 있음
- `spring.jpa.hibernate.ddl-auto=none` 를 `none` 값이 아닌  `create`로 설정하면 Entity를 보고 자동으로 테이블도 생성해준다.


### JPA는 인터페이스(표준)이고, 구현체로 hibernate를 사용

### ORM

Object-Relational(DB) mapping

### JPA는 모든 데이터 변경이 트랜잭션 안에서 실행되어야 한다. 

## Spring-data-jpa 는 jpa 사용을 쉽게 해주는 도구 

- `SpringDataJpaMemberRepository` 처럼 interface만 만들면 
- `spring-data-jpa`가 자동으로 구현체를 만들어서 spring bean으로 등록
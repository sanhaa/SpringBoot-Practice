
## Springboot 찍먹하기
- 강의: [김영한 스프링부트](https://www.youtube.com/playlist?list=PLumVmq_uRGHgBrimIp2-7MCnoPUskVMnd)
- 강의 실습코드 레포입니다.
- 키워드: `springboot`, `mvc`, `jdbc`, `jpa`, `spring data jpa`, `h2 DB`


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

### Spring Data JPA 제공 기능
- 인터페이스를 통한 기본적인 CRUD
- `findByName()`, `findByEmailAndName()` 처럼 이름만으로 조회기능 제공
- 페이징 기능 자동 제공
- 더 알아보기) `Querydsl` 라이브러리를 활용하면 복잡한 동적 쿼리 작성 가능

----

## AOP

> C의 pointer와 같은,, spring을 포기하고 싶게 만드는 개념일수도 있지만 사실은 필요목적을 알면 쉽다고,,,
 
### AOP가 필요한 상황
- 모든 메소드의 호출 시간을 측정하고 싶다면?
- 핵심 관심사항(`비즈니스 로직`) vs. 공통 관심사항
- 이 두 개가 섞여있다면 유지보수가 어렵다. (`상상만 해도 싫네.. 상상이 아니라 내 현실일지도 하하`)

### AOP의 동작원리?
- AOP가 적용되어야 하는 bean에 대하여
- proxy를 만들고 (? 가짜 객체)
- 해당 bean 의 모든 호출은 `proxy 객체` -> `joinPoint.proceed()` -> `실제 객체` 로 이루어짐

- DI 덕분에 프록시 동작, AOP가 가능한 것이당.
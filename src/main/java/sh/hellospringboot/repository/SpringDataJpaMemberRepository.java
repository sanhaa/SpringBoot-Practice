package sh.hellospringboot.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import sh.hellospringboot.domain.Member;

import java.util.Optional;

/**
 * SpringDataJpa 특징: JpaRepository를 상속받으면 자동으로,
 * - interface 구현체를 만들고 (다중으로 상속받은 MemberRepository)
 * - bean으로 등록
 * 우리는 그냥 만들어진 구현체를 가져다 쓰면 됩니다.
 */

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    @Override
    Optional<Member> findByName(String name);
    /*
        위의 함수는 아래의 JPQL과 같다.
        JPQL: select m from Member m where m.name = ?

        메서드 명명규칙에 따라 sql을 짜주는 셈
     */

}

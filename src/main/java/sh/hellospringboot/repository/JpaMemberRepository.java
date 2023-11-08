package sh.hellospringboot.repository;

import jakarta.persistence.EntityManager;
import sh.hellospringboot.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // spring boot 가 알아서 entityManger 만들어준다. db랑 통신? 담당

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // Entity (Member class) 에 맞춰서 알아서 insert 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // id가 pk이므로 바로 find 가능
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // sql과 비슷하지만 얘는 Member entity 자체를 selec
                .getResultList();
    }
}

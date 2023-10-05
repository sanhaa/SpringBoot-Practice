package sh.hellospringboot.repository;

import org.springframework.stereotype.Repository;
import sh.hellospringboot.domain.Member;

import java.util.*;

/*
이 class는 구현체!
 */
@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key 값 생성을 위한

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값 셋팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // get 해온 결과가 null일 수 있으므로
        // optional로 감싸서 처리
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // for test code
    public void clearStore(){
        store.clear();
    }
}

package sh.hellospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.hellospringboot.domain.Member;
import sh.hellospringboot.repository.MemberRepository;
import sh.hellospringboot.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){ // constructor (생성자) 개념
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원은 안된다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // optional로 감쌌기 때문에 ifPresent 메서드 사용하여 null 확인
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * ID로 회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

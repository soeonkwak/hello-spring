package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return member의 id값
     */
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증 메소드 호출
        memberRepository.save(member);
        return member.getId();
    }


    /**
     * 중복 회원 검증: 같은 이름을 가진 중복 회원 x
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //반환값이 Optional 형식이기 때문에 가능. 값이 존재하면 이 로직이 동작함. (이때 m은 그냥 주는 변수명같은거?)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전제 회원 조회
     */
    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}

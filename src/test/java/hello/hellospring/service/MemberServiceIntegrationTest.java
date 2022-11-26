package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행함. 진짜 스프링을 띄워서 테스트함.
@Transactional //테스트케이스에서 써주면 test 실행할때 트랜잭션이 실행, 테스트 검증하고, 테스트가 끝나면 롤백을 해줘서 db에 반영이 안돼서 계속 돌릴 수 있음.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
//    @Commit //DB에 반영되도록 하는 어노
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("Spring JPA");

        //when
        Long savedId = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void findOne() {
    }

    /**
     * 중복 이름을 가진 회원 가입시 예외가 발생하는지 테스트한다.
     */
    @Test
    void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1); //Spring이란 이름을 가진 회원1이 가입을 하고,
/*
        try{
            memberService.join(member2); //try-catch 문으로 감싸서 회원1과 동일한 이름을 가진 회원2가 가입을 시도했을때, 가입이 되면,
            fail(); //실패..............회원 서비스 잘못 만든거임.
        }catch (IllegalStateException e){ //예외발생해서 catch 문으로 들어오면 정상적으로 예외가 발생한거니 테스트 종료.
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //위에 try-catch 문으로 예외발생 검증하는걸 쉽게 하는 메소드가 존재.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//두번째 인자 로직을 태울때, 첫번째 인자에 해당하는 예외가 터져야함.

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findAllMembers() {
    }
}
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;


/**
 * 1. 테스트는 순서와 관계없이 서로 의존관계 없이 설계가 되어야 한다.
 * 2. 하나의 테스트가 끝날때 마다 저장소나 공용 데이터를 깔끔하게 지워줘야 한다. (afterEach 메소드 생성 이유)
 */
class MemoryMemberRepositoryTest { //다른데서 갖다 쓸게 아니니까 걍 public 으로 안해도됨.

     MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 테스트를 class 단위로 실행했을때 데이터로 인해서 실패가 뜰 수 있음. 그래서 매 테스트 메소드가 끝날 때마다 실행 시켜줄 애 생성.
     * 메소드 만들고 @AfterEach 어노테이션
     */
    @AfterEach
     public void afterEach(){
        repository.clearStore();
     }

     @Test //@Test 어노테이션을 붙여주면 이 함수를 단일로 실행시킬 수 있음.
     public void save() {

         //테스트 객체 생성
         Member member = new Member();
         member.setName("spring");

         //테스트 대상 실행
         repository.save(member);


         Member result = repository.findById(member.getId()).get(); //반환 타입이 Optional일 때 get()으로 Optional에서 한번 까서 꺼낼 수 있음.

         //테스트 검증 - new Member()로 생성한 애랑 내가 DB 에서 꺼낸 애랑 같은지 (메모리에 저장하기 때문에?)
         System.out.println("result : " +  (result == member));
         //Assertions.assertEquals(result, member);
         Assertions.assertThat(result).isEqualTo(member);
     }

    @Test
    public void findByName()  {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
         Member member1 = new Member();
         member1.setName("spring1");
         repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}

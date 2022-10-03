package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import hello.scantest.ScanTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    /**
     * 다른 패키지 내의 클래스를 빈으로 올릴 수 있는지 테스트.
     * HelloSpringApplication 클래스의 @SpringBootApplication 옵션으로 scantest 패키지를 스캔 범위로 지정해 놓는다.
     */
    public ScanTest scanTest(){
        return new ScanTest();
    }
}

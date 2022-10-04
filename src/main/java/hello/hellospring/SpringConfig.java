package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import hello.scantest.ScanTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    /**
     * MemberService가 의존성 주입 받을 Repository를 여기 config 파일에서 단 한 줄로 갈아 끼울 수 있다.
     *
     * <포인트>
     * 1. 객체 지향 관점
     *   - Repository 인터페이스를 두고, 구현체를 생성하였다.
     *      MemberService 는 인터페이스를 의존하고 있다.
     *   - 인터페이스를 구현하고 있는 어떤 구현체로도 갈아끼울 수  있다 :)
     *
     * 2. Spring 관점
     *   - MemberService 소스 페이지가 아닌 Config 파일에서 MemberService 와 MemberRepository 의 빈을 올리고
     *          MemberService 의 의존 관계를 설정하고 있다.
     *   - 상황에 따라 소스 페이지의 변경 없이 설정 파일만 수정하면 된다.
     */
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
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

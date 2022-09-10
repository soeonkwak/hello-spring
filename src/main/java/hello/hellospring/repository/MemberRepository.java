package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional은 데이터를 가져왔는데 null값인 경우 자동으로 처리?
    Optional<Member> findByName(String name);
    List<Member> findAll();

}

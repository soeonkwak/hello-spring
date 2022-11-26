package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //JPA는 EntityManager를 통해서 모든 동작이 됨. //Gradle에서 JPA 라이브러리 받으면(의존성 추가해주면) 스프링 부트에서 EntityManager 객체를 알아서 추가해줌 => 현재 연결되어있는 DB랑 다 연결까지 해서! 그러면 가져다 쓰기 위해 주입만 시켜주면 됨.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //이렇게만 해주면 jpa가 insert 쿼리 만들어서 db에 데이터 넣어줌 ..
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
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
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}

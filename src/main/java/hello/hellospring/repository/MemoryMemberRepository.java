package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    /**
     * 멤버 정보를 임의로 저장하기 위한 저장소. Map 형태로 저장.
     */
    private static Map<Long, Member> store = new HashMap<>();

    /**
     * 회원(Member 도메인)의 id 값을 생성해주기 위한 변수
     * sequence : 0, 1, 2, ... key 값을 생성해주는 애
     * Member 도메인의 id 멤버변수는 long 형 이니까 0'L'이 붙은듯?
     */
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * 임의의 저장소 store 에서 id 값으로 회원을 찾기 위한 메서드
     * @param id
     * @return member/null
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name)  {
        return store.values().stream() //루프로 돌림
                .filter(member -> member.getName().equals(name)) //java 8의 람다식. filter 는 스트림에서 사용하는 메서드. member 의 name 을 다 끄집어 와서 파라미터로 받은 name 랑 일치하는 애를 filter 하겠다.
                .findAny(); //뭐라도 찾겠다. stream 돌려서 있으면 찾고 없으면 Optional 로 null 이라도 반환하겠다.
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}

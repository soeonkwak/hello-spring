package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate; //얘는 기본적으로 제공. 단 inject을 바로 할 수 없으니 생성자를 통해서 사용.

    @Autowired //이거 붙여주는 이유는 datasource를 injection 받기 위해?
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); //SimpleJdbcInsert는 jdbcTemplate를 넘겨서 만드는 애.
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); //이거 대로 테이블명이랑 테이블 키컬럼 알려주면 쿼리를 짤 필요가 음슴.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id); //JdbcTemplate에서 첫번째 인자의 쿼리를 날린 결과가 두번째 임자의 함수의 RowMapper를 통해 Mapping한 걸 list 형태로 반환함.
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper()); //두번째 인자의 함수를 호출하면서 콜백으로 조회된 데이터가 객체로 들어오게 됨.
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
        };
    }

}

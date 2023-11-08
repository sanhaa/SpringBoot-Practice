package sh.hellospringboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sh.hellospringboot.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

//    @Autowired // 생성자가 하나인 경우 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource datasource){
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public Member save(Member member) {
        /**
         * SimpleJdbcInsert 활용하면
         * insert 쿼리를 작성하지 않아도 된다.
         */
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> memberList = jdbcTemplate.query("select * FROM member where id = ?", memberRowMapper(), id);
        return memberList.stream().findAny(); // Optional (null 처리 가능) 형태로 반환하는 법도 알아야겠다
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> memberList = jdbcTemplate.query("select * FROM member where name = ?", memberRowMapper(), name);
        return memberList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * FROM member", memberRowMapper());
    }

    /**
     * 이게 뭔지 모르겠다 ㅋㅋㅋ
     *
     */
    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> { // 람다 표현식 사용
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }

    // 기본 표현식
//    private RowMapper<Member> memberRowMapper(){
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//
//                return member;
//            }
//        };
//    }
}

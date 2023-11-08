package sh.hellospringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sh.hellospringboot.repository.JdbcMemberRepository;
import sh.hellospringboot.repository.MemberRepository;
import sh.hellospringboot.repository.MemoryMemberRepository;
import sh.hellospringboot.service.MemberService;

import javax.sql.DataSource;

/**
 * 방법 1. java 코드로 직접 bean 등록해주기 (SpringConfig.java 파일로 등록 or xml로 설정할 수도 있음)
 * 방법 2. @Autowired 어노테이션을 사용하면 Spring이 컴포넌트 스캔해서 자동으로 bean 등록
 */
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }

}

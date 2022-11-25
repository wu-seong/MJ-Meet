package group4.MJMeet.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//스프링 환경설정 어노테이션
@EnableWebSecurity//모든 요청 URL이 시큐리티의 제어를 받도록 한다.

//시큐리티의 세부 설정 SecurityFilterChain 빈 생성하여 설정할 수 있다.
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //모든 인증되지 않은 요청을 처리 해준다.
        //로그인을 하지 않아도 URL 요청이 가능
        http.authorizeRequests().antMatchers("/**").permitAll();
        return http.build();
    }
}
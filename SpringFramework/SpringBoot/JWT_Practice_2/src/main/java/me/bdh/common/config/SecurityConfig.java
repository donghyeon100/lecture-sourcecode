package me.bdh.common.config; // 패키지 선언

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // 메서드 단위 보안 활성화
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Spring Security 활성화
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정을 활성화하는 애노테이션
@EnableMethodSecurity // 메서드 단위 보안 설정을 활성화하는 애노테이션
@Configuration // 이 클래스가 Spring 설정 클래스임을 나타내는 애노테이션
public class SecurityConfig {

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        // BCrypt 암호화 알고리즘을 사용한 암호 해시 함수를 빈으로 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 특정 경로(정적 리소스)에 대해 Spring Security 무시 설정을 적용하는 메서드
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        // PathRequest.toStaticResources().atCommonLocations()는 일반적인 정적 리소스 경로를 지정rlad
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HttpSecurity 설정을 구성하는 메서드
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/login", "/join").permitAll() // "/", "/login", "/join" 경로는 모두 접근 허용
                        .anyRequest().authenticated() // 다른 모든 요청은 인증이 필요함
                )
                .formLogin(form -> form
                        .loginPage("/") // 로그인 페이지 경로 설정
                        .defaultSuccessUrl("/main", true) // 로그인 성공 시 이동할 기본 경로 설정
                        .permitAll() // 누구나 로그인 페이지 접근 가능하도록 설정
                )
                .logout(logout -> logout
                        .permitAll()); // 누구나 로그아웃할 수 있도록 설정

        return http.build(); // SecurityFilterChain 객체를 빌드하여 반환
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password(bCryptPasswordEncoder().encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(bCryptPasswordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

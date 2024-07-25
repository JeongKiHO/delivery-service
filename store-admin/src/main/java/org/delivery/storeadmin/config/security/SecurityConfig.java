package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity // security 활성화
public class SecurityConfig {

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    // filterChain 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(it ->{
                    it
                            .requestMatchers(
                                    PathRequest.toStaticResources().atCommonLocations()
                            ).permitAll() // staticResource 에 대해서는 모든 요청 허가

                            // open-api / ** 하위 모든 주소는 인증 없이 통과
                            .mvcMatchers(
                                    "/open-api/**"
                            ).permitAll()

                            // swagger 주소 제외 (swagger 는 인증 없이 통과)
                            .mvcMatchers(
                                    SWAGGER.toArray(new String[0])
                            ).permitAll()

                            .anyRequest().authenticated(); // 그 외 모든 Request 는 인증을 받는다.
                })
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // Hash 방식으로 암호화
        return new BCryptPasswordEncoder();
    }
}

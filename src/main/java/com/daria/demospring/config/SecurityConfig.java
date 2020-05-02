package com.daria.demospring.config;

import com.daria.demospring.security.jwt.JwtConfigurer;
import com.daria.demospring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ACCOUNT_ENDPOINT = "/api/v1/account/**";
    private static final String SKILL_ENDPOINT = "/api/v1/skill/**";
    private static final String USER_ENDPOINT = "/api/v1/user/**";
    private static final String DEVELOPER_ENDPOINT = "/api/v1/developer/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()

                .antMatchers(HttpMethod.GET, ACCOUNT_ENDPOINT).hasAnyRole("ADMIN", "MODER", "USER")
                .antMatchers(HttpMethod.POST, ACCOUNT_ENDPOINT).hasAnyRole("ADMIN", "MODER")
                .antMatchers(HttpMethod.PUT, ACCOUNT_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, ACCOUNT_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, SKILL_ENDPOINT).hasAnyRole("ADMIN", "MODER", "USER")
                .antMatchers(HttpMethod.POST, SKILL_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, SKILL_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, SKILL_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, USER_ENDPOINT).hasAnyRole("ADMIN", "MODER")
                .antMatchers(HttpMethod.POST, USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, USER_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, DEVELOPER_ENDPOINT).hasAnyRole("ADMIN", "MODER", "USER")
                .antMatchers(HttpMethod.POST, DEVELOPER_ENDPOINT).hasAnyRole("ADMIN", "MODER")
                .antMatchers(HttpMethod.PUT, DEVELOPER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, DEVELOPER_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}

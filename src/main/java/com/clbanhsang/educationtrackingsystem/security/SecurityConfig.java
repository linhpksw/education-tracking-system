package com.clbanhsang.educationtrackingsystem.security;

import com.clbanhsang.educationtrackingsystem.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //  Vô hiệu hóa CSRF protection để cho phép đăng nhập qua form POST mà không cần token CSRF
                .authorizeHttpRequests((authorize) -> authorize // Cấu hình xác thự yêu cầu
                        .requestMatchers("/**","/home","register").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN").anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
                        .usernameParameter("email").passwordParameter("password").
                        defaultSuccessUrl("/?login?success", true)
                        .failureUrl("/login?success=fail"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

        return http.build();
    }
}


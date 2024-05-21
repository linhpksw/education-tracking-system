package com.clbanhsang.educationtrackingsystem.security;

import com.clbanhsang.educationtrackingsystem.service.CustomUserDetailService;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINT = {"/**", "/home", "/register", "/users", "auth/token", "auth/introspect"};
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
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
                .csrf(AbstractHttpConfigurer::disable)//  Vô hiệu hóa CSRF protection để cho phép đăng nhập qua form POST mà không cần token CSRF
                .authorizeHttpRequests((authorize) -> authorize // Cấu hình xác thự yêu cầu cho ENDPOINT
                        .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users").hasAuthority()
                        .anyRequest().authenticated());


//              .formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
//                        .usernameParameter("email").passwordParameter("password").
//                        defaultSuccessUrl("/?login?success", true)
//                        .failureUrl("/login?success=fail"))
//                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

//         Cấu hình jwt, ta cần jwtDecoder();
//         Ạuthentication provider sẽ thực hiện decode token xem có hợp lệ hay không.
        http
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");

        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}


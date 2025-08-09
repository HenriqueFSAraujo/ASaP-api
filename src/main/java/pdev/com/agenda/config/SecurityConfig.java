package pdev.com.agenda.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthorizationFilterConfig securityFilter;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    private static final String[] SWAGGER_WHITELIST = {

            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/configuration/ui",
            "/configuration/security",
            "/v2/api-docs"
    };

    private static final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/login",
            "/api/users" // deixe aqui só se cadastro for público
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationConfiguration authConfig) throws Exception {

        AuthenticationManager am = authConfig.getAuthenticationManager();

        CustomAuthenticationFilterConfig authFilter =
                new CustomAuthenticationFilterConfig(am, tokenService);
        authFilter.setRequiresAuthenticationRequestMatcher(
                new org.springframework.security.web.util.matcher.AntPathRequestMatcher("/api/auth/login", "POST")
        );

        http
                .cors().and()
                .csrf().disable()
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers(SWAGGER_WHITELIST).permitAll()
                        .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(authFilter)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


package com.tekup.staffmanagementapi.config;

import com.tekup.staffmanagementapi.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(
                            "/v2/api-docs",
                            "v3/api-docs",
                            "v3/api-docs/**",
                            "/swagger-resources",
                            "/swagger-resources/**",
                            "/configuration/ui",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/webjars/**",
                            "/v3/api-docs/**").permitAll();
                    request.requestMatchers("/api/auth/**").permitAll();

                    request.requestMatchers("/api/users/**").hasRole("HR");

                    request.requestMatchers("/api/leave-requests").hasAnyRole("HR","EMPLOYEE");
                    request.requestMatchers("/api/leave-requests/employee/**").hasRole("EMPLOYEE");
                    request.requestMatchers("/api/leave-requests/**/status").hasRole("HR");
                    request.requestMatchers("/api/leave-requests/pending").hasRole("HR");

                    request.requestMatchers("/api/evaluations").hasRole("HR");
                    request.requestMatchers("/api/evaluations/employee/**").hasRole("HR");
                    request.requestMatchers("/api/evaluations/evaluator/**").hasRole("HR");

                    request.anyRequest().authenticated();
                })
                .sessionManagement( session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

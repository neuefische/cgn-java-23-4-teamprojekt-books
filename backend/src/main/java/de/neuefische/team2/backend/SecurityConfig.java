package de.neuefische.team2.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.environment}")
    private String environment;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println(environment);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a
                        .anyRequest().permitAll()
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .logout(logout -> {
                    if (environment.equals("prod")) {
                        logout.logoutSuccessUrl("/").permitAll();
                    } else {
                        logout.logoutSuccessUrl("http://localhost:5173").permitAll();
                    }
                })
                .oauth2Login(c -> {
                    try {
                        c.init(http);
                        if (environment.equals("prod")) {
                            c.defaultSuccessUrl("/", true);
                        } else {
                            c.defaultSuccessUrl("http://localhost:5173", true);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return http.build();
    }

}
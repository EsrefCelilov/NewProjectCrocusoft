package az.ltclab.newprojectcrocusoft.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {




        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                                .anyRequest().permitAll());
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/swagger-ui/swagger-ui-bundle.js",
                                "/swagger-ui/swagger-ui-standalone-preset.js",
                                "/swagger-ui/swagger-initializer.js",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/h2-console/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users/update/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/delete/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/read").permitAll()
                                .requestMatchers(HttpMethod.GET, "/users/getId/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        // H2 console üçün
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }





}

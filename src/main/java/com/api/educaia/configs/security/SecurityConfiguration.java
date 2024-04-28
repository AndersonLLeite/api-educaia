package com.api.educaia.configs.security;
import com.api.educaia.configs.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*"); // Permitir todas as origens
        corsConfiguration.addAllowedMethod("*"); // Permitir todos os métodos (GET, POST, PUT, DELETE, etc.)
        corsConfiguration.addAllowedHeader("*"); // Permitir todos os cabeçalhos
        corsConfiguration.setAllowCredentials(true); // Permitir credenciais

        http
                .csrf()
                .disable()
                .cors(cors -> cors.configurationSource(request -> corsConfiguration))
                .authorizeHttpRequests()
                .antMatchers("/api/v1/auth/**")

                .permitAll()
                .antMatchers("/api/school/create-school")

                .permitAll()


                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**")
                .permitAll()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
}



//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
//                .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
//                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs").permitAll()
////                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
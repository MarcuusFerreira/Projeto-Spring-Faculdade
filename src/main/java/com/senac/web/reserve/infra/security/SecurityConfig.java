package com.senac.web.reserve.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/condominium/login").permitAll()
//                        .requestMatchers("/condominium/register").permitAll()
//                        .requestMatchers("/condominium/register").permitAll()
//                        .requestMatchers( "/condominium/listall").hasRole("ADMIN")
//                        .requestMatchers("/condominium/listall").hasRole("ADMIN")
//                        .requestMatchers("/condominium/update").hasRole("ADMIN")
//                        .requestMatchers("/condominium/update").hasRole("ADMIN")
//                        .requestMatchers("/commonarea/insert").permitAll() //hasRole("ADMIN")
//                        .requestMatchers("/commonarea/update").permitAll() //hasRole("ADMIN")
//                        .requestMatchers("/commonarea/update").permitAll() //hasRole("ADMIN")
//                        .requestMatchers("/commonarea/listall").authenticated()
//                        .requestMatchers("/commonarea/listall").authenticated()
//                        .requestMatchers("/assets/login.css").permitAll()
//                        .requestMatchers("/js/login.js").permitAll()
                        .anyRequest().permitAll())
                        .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                        .formLogin(formLogin -> formLogin.loginPage("/login.html").permitAll())
                        .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("HEAD");
        configuration.addAllowedMethod("TRACE");
        configuration.addAllowedMethod("CONNECT");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
//            }
//        };
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

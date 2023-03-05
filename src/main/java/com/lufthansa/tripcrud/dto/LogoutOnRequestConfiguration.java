//package com.lufthansa.tripcrud.dto;
//
//
//import jakarta.servlet.ServletException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class LogoutOnRequestConfiguration {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().requestMatchers("/api/auth/**")
//                .permitAll();
//                http.logout(logout -> logout.logoutUrl("/request/logout")
//                        .addLogoutHandler((request, response, auth) -> {
//                            try {
//                                request.logout();
//                            }
//                            catch (ServletException e) {
////                                logger.error(e.getMessage());
//                            }
//                        }));
//        return http.build();
//    }
//}
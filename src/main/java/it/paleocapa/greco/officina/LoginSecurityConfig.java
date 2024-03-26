package it.paleocapa.greco.officina;

import java.util.HashSet;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.paleocapa.greco.officina.service.AdminDetailsService;
import it.paleocapa.greco.officina.service.DipendenteDetailsService;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig {

    private static GrantedAuthoritiesMapper customAuthorityMapper(String role) {
        return (authorities) -> {
            HashSet<GrantedAuthority> mappedAuthorities = new HashSet<>();
            mappedAuthorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role;
                }
            });

            return mappedAuthorities;
        };
    }

    private static SecurityFilterChain customHttpSecurity(HttpSecurity http, String filter_prefix, DaoAuthenticationProvider authenticationProvider) throws Exception {
        http.authenticationProvider(authenticationProvider);
        http.securityMatcher("/"+filter_prefix+"/*")
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .requestMatchers("/"+filter_prefix+"/*").hasAuthority(filter_prefix.toUpperCase()))
            // log in
            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                    .loginPage("/"+filter_prefix+"_login")                   
                        .usernameParameter("email")
                    .loginProcessingUrl("/"+filter_prefix+"/login_processing")
                    .failureUrl("/"+filter_prefix+"_login?error=loginError")
                    .defaultSuccessUrl("/"+filter_prefix+"/home"))
            // logout
            .logout(httpSecurityLogoutConfigurer ->
                    httpSecurityLogoutConfigurer.logoutUrl("/"+filter_prefix+"/logout")
                            .logoutSuccessUrl("/")
                            .deleteCookies("JSESSIONID"))
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                    httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    private static DaoAuthenticationProvider customAuthenticationProvider(UserDetailsService userDetailsService, String role) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authProvider.setAuthoritiesMapper(customAuthorityMapper(role.toUpperCase()));

        return authProvider;
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter {

        @Bean
        public UserDetailsService adminUserDetailsService() {
            return new AdminDetailsService();
        }

        @Bean
        public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
            DaoAuthenticationProvider clienteAuthenticationProvider = customAuthenticationProvider(adminUserDetailsService(), "ADMIN");
            return customHttpSecurity(http, "admin", clienteAuthenticationProvider);
        }

        /* @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                    )
                    .headers(headers -> headers.frameOptions().disable())
                    .csrf(csrf -> csrf
                            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
            return http.build();
        } */

    }

    @Configuration
    @Order(2)
    public static class DipendenteConfigurationAdapter {

        @Bean
        public UserDetailsService dipendenteUserDetailsService() {
            return new DipendenteDetailsService();
        }

        @Bean
        public SecurityFilterChain dipendenteFilterChain(HttpSecurity http) throws Exception {
            DaoAuthenticationProvider dipendenteAuthenticationProvider = customAuthenticationProvider(dipendenteUserDetailsService(), "DIPENDENTE");
            return customHttpSecurity(http, "dipendente", dipendenteAuthenticationProvider);
        }
    }

}
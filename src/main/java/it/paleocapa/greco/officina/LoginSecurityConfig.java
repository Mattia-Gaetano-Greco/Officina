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

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter {

        @Bean
        public UserDetailsService userDetailsService() {
            return new AdminDetailsService();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider1() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(bCryptPasswordEncoder());
            authProvider.setAuthoritiesMapper(customAuthorityMapper("ADMIN"));

            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChainApp1(HttpSecurity http) throws Exception {
            http.authenticationProvider(authenticationProvider1());
            http.securityMatcher("/admin/*")
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.
                    requestMatchers("/admin/*").hasAuthority("ADMIN"))
                // log in
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/admin_login")
                            .usernameParameter("email")
                        .loginProcessingUrl("/admin/login_processing")
                        .failureUrl("/admin_login?error=loginError")
                        .defaultSuccessUrl("/admin/home"))
                // logout
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutUrl("/admin/logout")
                                .logoutSuccessUrl("/")
                                .deleteCookies("JSESSIONID"))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
                .csrf(AbstractHttpConfigurer::disable);

            return http.build();
        }

        /* 
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                    )
                    .headers(headers -> headers.frameOptions().disable())
                    .csrf(csrf -> csrf
                            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
            return http.build();
        }
        */

    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter {

        @Bean
        public UserDetailsService userDetailsService2() {
            return new DipendenteDetailsService();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider2() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService2());
            authProvider.setPasswordEncoder(bCryptPasswordEncoder());
            authProvider.setAuthoritiesMapper(customAuthorityMapper("DIPENDENTE"));
    
            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChainApp2(HttpSecurity http) throws Exception {
            http.authenticationProvider(authenticationProvider2());
            http.securityMatcher("/dipendente/*")
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/dipendente/*").hasAuthority("DIPENDENTE"))
                // log in
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/dipendente_login")                   
                            .usernameParameter("email")
                        .loginProcessingUrl("/dipendente/login_processing")
                        .failureUrl("/dipendente_login?error=loginError")
                        .defaultSuccessUrl("/dipendente/home"))
                // logout
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutUrl("/dipendente/logout")
                                .logoutSuccessUrl("/")
                                .deleteCookies("JSESSIONID"))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
                .csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    }

}
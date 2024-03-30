package it.paleocapa.greco.officina;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.paleocapa.greco.officina.service.AdminDetailsService;
import it.paleocapa.greco.officina.service.ClienteDetailsService;
import it.paleocapa.greco.officina.service.DipendenteDetailsService;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig {
    
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter {


        @Bean
        public UserDetailsService userDetailsService() {
            return new AdminDetailsService();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider1() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(bCryptPasswordEncoder());
    
            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChainApp1(HttpSecurity http) throws Exception {
            http.authenticationProvider(authenticationProvider1());
            http.securityMatcher("/admin/*")
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.
                    requestMatchers("/admin/*").authenticated())
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
    public static class DipendenteConfigurationAdapter {

        @Bean
        public UserDetailsService userDetailsService2() {
            return new DipendenteDetailsService();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider2() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService2());
            authProvider.setPasswordEncoder(bCryptPasswordEncoder());
    
            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChainApp2(HttpSecurity http) throws Exception {
            http.authenticationProvider(authenticationProvider2());
            http.securityMatcher("/dipendente/*")
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/dipendente/*").authenticated())
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

    @Configuration
    @Order(2)
    public static class ClienteConfigurationAdapter {

        @Bean
        public UserDetailsService userDetailsService3() {
            return new ClienteDetailsService();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider3() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService3());
            authProvider.setPasswordEncoder(bCryptPasswordEncoder());
    
            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChainApp3(HttpSecurity http) throws Exception {
            http.authenticationProvider(authenticationProvider3());
            http.securityMatcher("/cliente/*")
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/cliente/*").authenticated())
                // log in
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/cliente_login")                   
                            .usernameParameter("email")
                        .loginProcessingUrl("/cliente/login_processing")
                        .failureUrl("/cliente_login?error=loginError")
                        .defaultSuccessUrl("/cliente/home"))
                // logout
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutUrl("/cliente/logout")
                                .logoutSuccessUrl("/")
                                .deleteCookies("JSESSIONID"))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
                .csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    }

}
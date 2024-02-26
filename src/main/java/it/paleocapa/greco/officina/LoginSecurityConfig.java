package it.paleocapa.greco.officina;
 
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.paleocapa.greco.officina.service.AdminDetailsService;
import it.paleocapa.greco.officina.service.DipendenteDetailsService;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig {
     
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }

    /* @Configuration
    @Order(1)
    public static class A_DipendenteSecurityConfig {
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
        * /
    
        @Bean
        public SecurityFilterChain securityFilterChainDipendente(HttpSecurity http) throws Exception {
            DaoAuthenticationProvider authenticationProvider = authenticationProvider2();
            http.authenticationProvider(authenticationProvider);
    
            http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/admin/*").permitAll()
                    .requestMatchers("/dipendente/*").authenticated()
                )
                .formLogin((form) -> form
                    .loginPage("/dipendente/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/dipendente/login")
                        .defaultSuccessUrl("/dipendente/home")
                    .permitAll()
                )
                .logout((logout) -> logout
                    .logoutUrl("/dipendente/logout")
                    .logoutSuccessUrl("/")
                );
    
            return http.build();
        }
    }*/

    @Configuration
    @Order(1)
    public static class B_AdminSecurityConfig {
     
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
        public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
            DaoAuthenticationProvider authenticationProvider = authenticationProvider1();
            http.authenticationProvider(authenticationProvider);
    
            http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/admin/*").authenticated()
                    .requestMatchers("/admin/officina/*").authenticated()
                )
                .formLogin((form) ->
                form
                    .loginPage("/admin/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/home")
                    .permitAll()
                )
                .logout((logout) -> logout
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/")
                );
    
            return http.build();
        }
    }

}

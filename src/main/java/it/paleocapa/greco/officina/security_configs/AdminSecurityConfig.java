package it.paleocapa.greco.officina.security_configs;
 
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.paleocapa.greco.officina.service.AdminDetailsService;
 
@Configuration
@Order(1)
public class AdminSecurityConfig {
     
    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminDetailsService();
    }
     
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
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
    */

    @Bean
	public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authenticationProvider = authenticationProvider1();
        http.authenticationProvider(authenticationProvider);

		http
			.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/dipendente/**").permitAll()
				.requestMatchers("/admin/**").authenticated()
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

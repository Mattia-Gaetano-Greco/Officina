package it.paleocapa.greco.officina.security_configs;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.paleocapa.greco.officina.service.DipendenteDetailsService;

@Configuration
@Order(2)
public class DipendenteSecurityConfig {
     
    @Bean
    public UserDetailsService userDetailsService2() {
        return new DipendenteDetailsService();
    }
     
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder2() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService2());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder2());
 
        return authProvider;
    }
 
    @Bean
	public SecurityFilterChain securityFilterChainDipendente(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authenticationProvider = authenticationProvider2();
        http.authenticationProvider(authenticationProvider);

		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/dipendente/**").authenticated()
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

}
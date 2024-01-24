package it.paleocapa.greco.officina;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.paleocapa.greco.officina.service.DipendenteUtenteDetailsService;
 
@Configuration
@Order(2)
public class DipendenteSecurityConfig {
     
    @Bean
    public UserDetailsService userDetailsService2() {
        return new DipendenteUtenteDetailsService();
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

		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/dipendente/**").hasRole("DIPENDENTE")
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
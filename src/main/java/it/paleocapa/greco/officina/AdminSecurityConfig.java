package it.paleocapa.greco.officina;
 
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
	public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authenticationProvider = authenticationProvider1();
        System.out.println(authenticationProvider);
        http.authenticationProvider(authenticationProvider);
        
        //http.authorizeHttpRequests((requests) -> requests.requestMatchers("/").permitAll());

		http
			.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/").permitAll()
				.requestMatchers("/admin/**").authenticated()
			)
			.formLogin((form) -> form
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

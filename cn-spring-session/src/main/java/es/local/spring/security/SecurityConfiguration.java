package es.local.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import es.local.spring.service.UserService;

@Configuration
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/login","/", "/register", "/sendregister","/sesionManager", "/css/**", "/js/**", "/images/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login")	
				.permitAll().failureUrl("/login").defaultSuccessUrl("/sesionManager")
				.permitAll().and().rememberMe()
				.key("AbcdEfghIjklmNopQrsTuvXyz_0123456789").and().logout().permitAll();

		http.headers().frameOptions().sameOrigin();
		http.authenticationProvider(authenticationProvider());
		http.rememberMe().userDetailsService(userDetailsService());
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	     
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	 
	    return authProvider;
	}


}

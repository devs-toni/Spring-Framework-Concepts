package com.localhost.spring_security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SeguridadAppConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder usuarios = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(usuarios.username("Toni").password("1234").roles("usuario","administrador"));
		auth.inMemoryAuthentication().withUser(usuarios.username("Ana").password("4321").roles("usuario"));
		auth.inMemoryAuthentication().withUser(usuarios.username("Ying").password("5678").roles("usuario","ayudante"));
		auth.inMemoryAuthentication().withUser(usuarios.username("Laura").password("8765").roles("usuario","administrador"));

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasRole("usuario")
			.antMatchers("/admin/**").hasRole("administrador")
			.and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/success_login")
			.permitAll().and().logout().permitAll().and()
			.exceptionHandling().accessDeniedPage("/error");
	}
}

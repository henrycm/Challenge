package com.jhcm.appdirect.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userDetailsService;

	@Resource
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.openidLogin().loginPage("/auth/login.jsp").defaultSuccessUrl("/").and().authorizeRequests()
				.antMatchers("/auth/*").permitAll().anyRequest().authenticated();
		http.csrf().disable();

	}
}

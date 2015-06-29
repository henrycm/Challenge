package com.jhcm.appdirect.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private AuthenticationUserDetailsService<OpenIDAuthenticationToken> openIdUserDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.openidLogin().loginPage("/auth/login")
				.authenticationUserDetailsService(openIdUserDetailsService)
				.defaultSuccessUrl("/").and().authorizeRequests()
				.antMatchers("/auth/*")
				.permitAll().anyRequest().authenticated();
		http.csrf().disable();

	}
}

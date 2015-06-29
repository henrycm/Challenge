package com.jhcm.appdirect.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.openid4java.consumer.ConsumerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenID4JavaConsumer;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.google.inject.internal.Lists;

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
		http.openidLogin().loginPage("/auth/login").consumer(openIdConsumer())
				.authenticationUserDetailsService(openIdUserDetailsService)
				.defaultSuccessUrl("/").and().authorizeRequests()
				.antMatchers("/auth/*").permitAll().anyRequest()
				.authenticated();
		http.csrf().disable();

	}

	@Bean
	public OpenID4JavaConsumer openIdConsumer() throws ConsumerException {
		AxFetchListFactory f = new AxFetchListFactory() {

			@Override
			public List<OpenIDAttribute> createAttributeList(String identifier) {
				ArrayList<OpenIDAttribute> l = Lists.newArrayList(
						new OpenIDAttribute("userUuid",
								"https://www.appdirect.com/schema/user/uuid"),
						new OpenIDAttribute("email",
								"http://axschema.org/contact/email"),
						new OpenIDAttribute("firstName",
								"http://axschema.org/namePerson/first"),
						new OpenIDAttribute("lastName",
								"http://axschema.org/namePerson/last"));
				return l;
			}
		};

		return new OpenID4JavaConsumer(f);
	}
}

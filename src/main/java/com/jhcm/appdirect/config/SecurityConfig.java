package com.jhcm.appdirect.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.openid4java.consumer.ConsumerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenID4JavaConsumer;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.google.inject.internal.Lists;
import com.jhcm.appdirect.security.RestSecureddResourceProcessingFilter;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private Environment env;

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
				.antMatchers("/auth/*").permitAll()
				.antMatchers("/rest/event/**").permitAll().anyRequest()
				.authenticated();
		http.csrf().disable();
		http.addFilterAfter(oAuthProviderProcessingFilter(),
				OpenIDAuthenticationFilter.class);
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

	@Bean
	OAuthProviderProcessingFilter oAuthProviderProcessingFilter() {
		List<RequestMatcher> requestMatchers = new ArrayList<>();
		requestMatchers.add(new AntPathRequestMatcher("/rest/event/**"));
		ProtectedResourceProcessingFilter filter = new RestSecureddResourceProcessingFilter(
				requestMatchers);
		filter.setConsumerDetailsService(consumerDetailsService());
		filter.setTokenServices(providerTokenServices());
		filter.setIgnoreMissingCredentials(false);
		return filter;
	}

	@Bean
	public ConsumerDetailsService consumerDetailsService() {
		InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();
		BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
		consumerDetails
				.setConsumerKey(env.getProperty("appdirect.consumerkey"));
		consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(env
				.getProperty("appdirect.consumersecret")));
		consumerDetails.setRequiredToObtainAuthenticatedToken(false);
		Map<String, BaseConsumerDetails> consumerDetailsStore = new HashMap<>();
		consumerDetailsStore.put(env.getProperty("appdirect.consumerkey"),
				consumerDetails);
		consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);
		return consumerDetailsService;
	}

	@Bean
	public OAuthProviderTokenServices providerTokenServices() {
		return new InMemoryProviderTokenServices();
	}
}

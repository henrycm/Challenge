package com.jhcm.appdirect.config;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = { "com.jhcm.appdirect" })
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

	@Autowired
	private Environment env;

	private final String consumerKey = "integration-challenge-full-29915";
	private final String consumerSecret = "zD1aJQ2zGriMZCtL";

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public OAuthConsumer getOAuthConsumer() {
		OAuthConsumer o = new DefaultOAuthConsumer(consumerKey, consumerSecret);
		o.setSigningStrategy(new QueryStringSigningStrategy());
		return o;
	}
}

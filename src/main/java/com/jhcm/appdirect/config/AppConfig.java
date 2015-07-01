package com.jhcm.appdirect.config;

import javax.annotation.Resource;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Resource
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public OAuthConsumer getOAuthConsumer() {
		log.debug("Consumerkey:" + env.getProperty("appdirect.consumerkey"));
		OAuthConsumer o = new DefaultOAuthConsumer(
				env.getProperty("appdirect.consumerkey"),
				env.getProperty("appdirect.consumersecret"));
		o.setSigningStrategy(new QueryStringSigningStrategy());
		return o;
	}
}

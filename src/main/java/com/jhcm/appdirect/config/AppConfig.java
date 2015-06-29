package com.jhcm.appdirect.config;

import javax.annotation.Resource;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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

	private Logger log = Logger.getLogger(AppConfig.class);

	@Resource
	private Environment env;

	@Value("${appdirect.consumerkey}")
	private String consumerKey;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public OAuthConsumer getOAuthConsumer() {
		log.debug("consumerKey:" + consumerKey);
		OAuthConsumer o = new DefaultOAuthConsumer(
				env.getProperty("appdirect.consumerkey"),
				env.getProperty("appdirect.consumersecret"));
		o.setSigningStrategy(new QueryStringSigningStrategy());
		return o;
	}
}

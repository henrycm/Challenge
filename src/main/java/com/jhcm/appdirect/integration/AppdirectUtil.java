package com.jhcm.appdirect.integration;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jhcm.appdirect.view.AppDirectEventController;

@Component
public class AppdirectUtil {
	private Logger log = Logger.getLogger(AppDirectEventController.class);

	private final OAuthConsumer consumer;

	@Value("${appdirect.consumerKey}")
	private String consumerKey;
	@Value("${appdirect.consumerSecret}")
	private String consumerSecret;

	public AppdirectUtil() {
		this.consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
		this.consumer.setSigningStrategy(new QueryStringSigningStrategy());
	}

	public String sign(String urlString) {
		log.debug("Signing URL: " + urlString);
		try {
			String signedUrl = consumer.sign(urlString);
			log.debug("Signed URL: " + signedUrl);
			return signedUrl;
		} catch (OAuthException e) {
			log.error("Error when signing URL", e);
			return urlString;
		}
	}

	public String readEventData(String url) {
		RestTemplate restTemplate = new RestTemplate();
		String signed_url = sign(url);
		ResponseEntity<String> response = restTemplate.getForEntity(signed_url, String.class);
		log.debug("XML =>" + response.getBody());
		return response.toString();
	}
}

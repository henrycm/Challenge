package com.jhcm.appdirect.integration;

import javax.annotation.Resource;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jhcm.appdirect.view.AppDirectEventController;

@Component
public class SignService {
	private Logger log = Logger.getLogger(AppDirectEventController.class);

	@Resource
	private OAuthConsumer consumer;

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

}

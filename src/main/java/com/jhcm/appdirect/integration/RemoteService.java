package com.jhcm.appdirect.integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import oauth.signpost.OAuthConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * RemoteService handles data request with appDirect servers. Uses OAuthConsumer
 * to sign URL as needed from appDirect API.
 */
@Component
public class RemoteService {

	private static final Logger log = LoggerFactory.getLogger(RemoteService.class);

	@Resource
	private OAuthConsumer consumer;

	public String getXml(String url) throws Exception {
		String signed_url = consumer.sign(url);
		log.debug("SIGNED_URL:" + signed_url);
		URL u = new URL(signed_url);
		HttpURLConnection request = (HttpURLConnection) u.openConnection();
		request.setRequestMethod("GET");
		request.setRequestProperty("Accept", "application/xml");
		request.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		request.disconnect();
		return sb.toString();
	}
}

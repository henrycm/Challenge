package com.jhcm.appdirect.integration;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import oauth.signpost.OAuthConsumer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jhcm.appdirect.integration.xml.Event;

@Component
public class RemoteService {

	private Logger log = Logger.getLogger(RemoteService.class);

	@Resource
	private OAuthConsumer consumer;

	public Event getFromXml(String xml) throws JAXBException {
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		Event ev = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ev = (Event) jaxbUnmarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return ev;
	}

	public String getXml(String url) throws Exception {
		String signed_url = consumer.sign(url);
		log.debug("SIGNED_URL:" + signed_url);
		URL u = new URL(signed_url);
		HttpURLConnection request = (HttpURLConnection) u.openConnection();
		request.setRequestMethod("GET");
		request.setRequestProperty("Accept", "application/xml");
		consumer.sign(request);
		request.connect();
		debugHeaders(request);
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		request.disconnect();
		return sb.toString();
	}

	public void debugHeaders(HttpURLConnection request) {
		Map<String, List<String>> hdrs = request.getHeaderFields();
		Set<String> hdrKeys = hdrs.keySet();
		StringBuilder sb = new StringBuilder();
		for (String k : hdrKeys)
			sb.append("Key: " + k + "  Value: " + hdrs.get(k));

		log.debug("Connection headers:" + sb.toString());
	}

	public void debugHeaders(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		StringBuilder sb = new StringBuilder();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			sb.append("Header Name:" + headerName);
			String headerValue = request.getHeader(headerName);
			sb.append("Header Value:" + headerValue + "\n");
		}
		log.debug("Request headers:" + sb.toString());
	}
}

package com.jhcm.appdirect.integration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jhcm.appdirect.integration.xml.Event;

@Component
public class RemoteService {

	private Logger log = Logger.getLogger(RemoteService.class);

	@Resource
	private SignService signService;

	public Event getFromXml(String xml) {
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		Event ev = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ev = (Event) jaxbUnmarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			log.error(e);
		}
		return ev;
	}

	public String getXml(String url) {
		String signed_url = signService.sign(url);
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> response = rest.exchange(signed_url, HttpMethod.GET, entity, String.class);
		String xml = response.getBody();
		return xml;
	}

}

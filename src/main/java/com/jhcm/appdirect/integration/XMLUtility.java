package com.jhcm.appdirect.integration;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jhcm.appdirect.integration.xml.Event;

@Component
public class XMLUtility {

	private Logger log = Logger.getLogger(XMLUtility.class);

	@Resource
	private SignService signService;

	public Event getFromXml(String st_url) {
		String signed_url = signService.sign(st_url);
		URL url;
		Event ev = null;
		try {
			url = new URL(signed_url);
			JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ev = (Event) jaxbUnmarshaller.unmarshal(url);
		} catch (MalformedURLException | JAXBException e) {
			log.error(e);
		}
		return ev;
	}

}

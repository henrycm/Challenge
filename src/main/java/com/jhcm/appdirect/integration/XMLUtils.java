package com.jhcm.appdirect.integration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.jhcm.appdirect.integration.xml.Event;

public class XMLUtils {

	public static Event getFromXml(String xml) throws JAXBException {
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		Event ev = null;

		JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ev = (Event) jaxbUnmarshaller.unmarshal(stream);

		return ev;
	}
}

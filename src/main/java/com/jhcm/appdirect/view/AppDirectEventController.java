package com.jhcm.appdirect.view;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhcm.appdirect.integration.XMLUtility;
import com.jhcm.appdirect.integration.xml.Event;
import com.jhcm.appdirect.integration.xml.Result;

@RestController
@RequestMapping("/rest/event")
public class AppDirectEventController {

	private Logger log = Logger.getLogger(AppDirectEventController.class);

	@Resource
	private XMLUtility xmlUtility;
	
	@RequestMapping(method = RequestMethod.GET)
	public Result event(@RequestParam(value = "eventUrl", required = false) String eventUrl,
			@RequestParam(value = "token", required = false) String token) {
		log.debug("Event!");
		log.debug("Url:" + eventUrl);
		log.debug("token:" + token);
		if(eventUrl != null)
		{
			Event ev = xmlUtility.getFromXml(eventUrl);
			log.debug(ev);
		}
			
		return new Result(true, "Test message");
	}
}

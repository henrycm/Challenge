package com.jhcm.appdirect.view;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhcm.appdirect.integration.xml.types.Result;

@RestController
@RequestMapping("/rest/event")
public class AppDirectEventController {

	private Logger log = Logger.getLogger(AppDirectEventController.class);

	@RequestMapping(method = RequestMethod.GET)
	public Result event(@RequestParam(value = "eventUrl", required = false) String eventUrl,
			@RequestParam(value = "token", required = false) String token) {
		log.debug("Event!");
		log.debug("Url:" + eventUrl);
		log.debug("token:" + token);
		
		return new Result(true, "Test message");
	}
}

package com.jhcm.appdirect.view;

import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/event")
public class AppDirectEventController {

	private Logger log = Logger.getLogger(AppDirectEventController.class);

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String event(@RequestParam(value = "eventUrl", required = false) String eventUrl,
			@RequestParam(value = "token", required = false) String tokenfinal, @Context SecurityContext securityContext) {
		log.debug("Event!");
		log.debug("Url:" + eventUrl);
		log.debug("token:" + eventUrl);
		return "OK";
	}
}

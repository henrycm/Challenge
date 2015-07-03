package com.jhcm.appdirect.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhcm.appdirect.backend.service.AccountService;
import com.jhcm.appdirect.integration.RemoteService;
import com.jhcm.appdirect.integration.XMLUtils;
import com.jhcm.appdirect.integration.xml.Event;
import com.jhcm.appdirect.integration.xml.Result;
import com.jhcm.appdirect.integration.xml.types.ErrorCode;

@RestController
@RequestMapping("/rest/event")
public class AppDirectEventController {

	private Logger log = LoggerFactory.getLogger(AppDirectEventController.class);

	@Resource
	private AccountService accountService;
	@Resource
	private RemoteService remoteService;

	@RequestMapping(method = RequestMethod.GET)
	public Result event(HttpServletRequest request, @RequestParam(value = "eventUrl", required = false) String eventUrl) {
		log.debug("Event arrived!");
		log.debug("Url:" + eventUrl);
		if (eventUrl == null) {
			return new Result(false, ErrorCode.CONFIGURATION_ERROR, "No URL received");
		}
		try {
			String xml = remoteService.getXml(eventUrl);
			accountService.logEvent(xml, eventUrl);
			Event ev = XMLUtils.getFromXml(xml);
			return accountService.handleEvent(ev);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false, ErrorCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
}

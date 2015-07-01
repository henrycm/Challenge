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
import com.jhcm.appdirect.integration.xml.Result;

@RestController
@RequestMapping("/rest/event")
public class AppDirectEventController {

	private Logger log = LoggerFactory
			.getLogger(AppDirectEventController.class);

	@Resource
	private RemoteService remoteService;

	@Resource
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public Result event(HttpServletRequest request,
			@RequestParam(value = "eventUrl", required = false) String eventUrl) {
		log.debug("Event arrived!");
		log.debug("Url:" + eventUrl);
		try {
			return accountService.handleEvent(eventUrl);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false, e.getMessage());
		}
	}
}

package com.jhcm.appdirect.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory
			.getLogger(AppDirectEventController.class);

	@RequestMapping(value = "/auth/login")
	public String init(
			@RequestParam(value = "openid", required = false) String openid,
			@RequestParam(value = "accountId", required = false) String accountId) {

		log.debug("openid:" + openid);
		log.debug("accountId:" + accountId);
		return "auth/login";
	}
}

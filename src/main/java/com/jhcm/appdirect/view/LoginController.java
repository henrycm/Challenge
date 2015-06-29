package com.jhcm.appdirect.view;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private Logger log = Logger.getLogger(AppDirectEventController.class);

	@RequestMapping(value = "/auth/login")
	public String init(
			@RequestParam(value = "openid", required = false) String openid,
			@RequestParam(value = "accountId", required = false) String accountId) {

		log.debug("openid:" + openid);
		log.debug("accountId:" + accountId);
		return "auth/login";
	}
}

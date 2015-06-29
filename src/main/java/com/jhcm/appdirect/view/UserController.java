package com.jhcm.appdirect.view;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhcm.appdirect.backend.service.AccountService;

@Controller
public class UserController {

	@Resource
	private AccountService accountService;

	@RequestMapping(value = "/")
	public String init() {
		return "redirect:list";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model) {
		model.addAttribute("users", accountService.listUsers());
		return "user";
	}

	@RequestMapping(value = "/logs")
	public String logs(ModelMap model) {
		model.addAttribute("logs", accountService.listEventLogs());
		return "eventLog";
	}

	@RequestMapping(value = "/logs/{id}")
	@ResponseBody()
	public String logs(@PathVariable Long id) {
		return accountService.getEventLog(id).getXml();
	}
}

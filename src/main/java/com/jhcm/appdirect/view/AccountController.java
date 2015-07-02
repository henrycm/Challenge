package com.jhcm.appdirect.view;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhcm.appdirect.backend.service.AccountService;

@Controller
public class AccountController {

	@Resource
	private AccountService accountService;

	@RequestMapping(value = "/")
	public String init() {
		return "redirect:list";
	}

	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false, defaultValue = "0") Integer pageNumber, ModelMap model) {
		model.addAttribute("page", accountService.listUsers(pageNumber));
		return "user";
	}

	@RequestMapping(value = "/logs/list")
	public String logs(@RequestParam(required = false, defaultValue = "0") Integer pageNumber, ModelMap model) {
		model.addAttribute("page", accountService.listEventLogs(pageNumber));
		return "eventLog";
	}

	@RequestMapping(value = "/logs/{id}")
	@ResponseBody()
	public String logs(@PathVariable Long id) {
		return accountService.getEventLog(id).getXml();
	}
}

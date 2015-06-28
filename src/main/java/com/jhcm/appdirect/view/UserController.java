package com.jhcm.appdirect.view;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhcm.appdirect.backend.model.User;
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
		model.addAttribute("user", new User());
		return "user";
	}
}

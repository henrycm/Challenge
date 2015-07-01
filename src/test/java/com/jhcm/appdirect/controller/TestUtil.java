package com.jhcm.appdirect.controller;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;

public class TestUtil {
	@Resource
	protected ApplicationContext appContext;

	public String getXml(String filename) {
		return appContext.getResource("classpath:xml/" + filename).toString();
	}
}

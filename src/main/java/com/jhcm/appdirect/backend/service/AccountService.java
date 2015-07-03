package com.jhcm.appdirect.backend.service;

import org.springframework.data.domain.Page;

import com.jhcm.appdirect.backend.model.EventLog;
import com.jhcm.appdirect.backend.model.User;
import com.jhcm.appdirect.integration.xml.Event;
import com.jhcm.appdirect.integration.xml.Result;

public interface AccountService {

	public abstract Result handleEvent(Event ev) throws Exception;

	public abstract Page<User> listUsers(int pageNumber);

	public abstract User getUserByOpenId(String openId);

	public abstract Page<EventLog> listEventLogs(int pageNumber);

	public abstract EventLog getEventLog(Long id);

	public abstract void logEvent(String xml, String url);

}
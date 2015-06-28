package com.jhcm.appdirect.backend.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jhcm.appdirect.backend.model.Account;
import com.jhcm.appdirect.backend.model.EventLog;
import com.jhcm.appdirect.backend.model.User;
import com.jhcm.appdirect.backend.repositories.EventLogRepository;
import com.jhcm.appdirect.backend.repositories.UserRepository;
import com.jhcm.appdirect.integration.RemoteService;
import com.jhcm.appdirect.integration.xml.Event;
import com.jhcm.appdirect.integration.xml.types.EventType;

@Service
public class AccountService {
	private Logger log = Logger.getLogger(AccountService.class);

	@Resource
	private RemoteService remoteService;

	@Resource
	private UserRepository urepo;

	@Resource
	private EventLogRepository lrepo;

	public void handleEvent(String url) throws Exception {
		Event ev = null;
		if (url != null) {
			String xml = remoteService.getXml(url);
			logEvent(xml, url);
			ev = remoteService.getFromXml(xml);
		}

		log.debug("EventType:" + ev.getType());
		if (ev.getType().equals(EventType.USER_ASSIGNMENT.name())) {
			handleUserAssignment(ev);
		} else if (ev.getType().equals(EventType.USER_UNASSIGNMENT.name())) {
			handleUserUnassignment(ev);
		}
	}

	public List<User> listUsers() {
		return urepo.findAll();
	}

	private void handleUserAssignment(Event ev) {
		User u = urepo.findByEmail(ev.getPayload().getUser().getEmail());
		if (u == null) {
			u = new User();
			log.debug("Creating user:" + ev.getPayload().getUser().getEmail());
		}
		u.setEmail(ev.getPayload().getUser().getEmail());
		u.setFirstName(ev.getPayload().getUser().getFirstName());
		u.setLanguage(ev.getPayload().getUser().getLanguage());
		u.setOpenId(ev.getPayload().getUser().getOpenId());
		Account a = u.getAccount();
		if (a == null && ev.getPayload().getAccount().getAccountIdentifier() != null) {
			a = new Account();
			a.setAccountIdentifier(ev.getPayload().getAccount().getAccountIdentifier());
			a.setStatus(ev.getPayload().getAccount().getStatus());
			u.setAccount(a);
		}
		urepo.save(u);
	}

	private void handleUserUnassignment(Event ev) {
		User u = urepo.findByEmail(ev.getPayload().getUser().getEmail());
		if (u != null)
			urepo.delete(u);
	}

	private void logEvent(String xml, String url) {
		try {
			log.debug("XML:" + xml);
			EventLog el = new EventLog();
			el.setDate(new Date());
			el.setXml(xml);
			el.setUrl(url);
			lrepo.save(el);
		} catch (Exception e) {
			log.warn(e);
		}
	}
}

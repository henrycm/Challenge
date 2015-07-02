package com.jhcm.appdirect.backend.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jhcm.appdirect.backend.model.Account;
import com.jhcm.appdirect.backend.model.EventLog;
import com.jhcm.appdirect.backend.model.User;
import com.jhcm.appdirect.backend.repositories.AccountRepository;
import com.jhcm.appdirect.backend.repositories.EventLogRepository;
import com.jhcm.appdirect.backend.repositories.UserRepository;
import com.jhcm.appdirect.integration.xml.Event;
import com.jhcm.appdirect.integration.xml.Result;
import com.jhcm.appdirect.integration.xml.types.EventType;

@Service
public class AccountService {
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);

	@Value("${global.page_size}")
	private int PAGE_SIZE;

	@Resource
	private UserRepository urepo;
	@Resource
	private AccountRepository arepo;
	@Resource
	private EventLogRepository lrepo;

	public Result handleEvent(Event ev) throws Exception {
		log.debug("EventType:" + ev.getType());
		if (ev.getType().equals(EventType.USER_ASSIGNMENT.name())) {
			return handleUserAssignment(ev);
		} else if (ev.getType().equals(EventType.USER_UNASSIGNMENT.name())) {
			return handleUserUnassignment(ev);
		} else if (ev.getType().equals(EventType.SUBSCRIPTION_ORDER.name())) {
			return handleSubscriptionOrder(ev);
		} else if (ev.getType().equals(EventType.SUBSCRIPTION_CANCEL.name())) {
			return handleSubscriptionCancell(ev);
		}
		return new Result(true, "Succeed");
	}

	public Page<User> listUsers(int pageNumber) {
		PageRequest req = new PageRequest(pageNumber, PAGE_SIZE);
		return urepo.findAll(req);
	}

	public User getUserByOpenId(String openId) {
		return urepo.findByOpenId(openId);
	}

	public Page<EventLog> listEventLogs(int pageNumber) {
		PageRequest req = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "id");
		return lrepo.findAll(req);
	}

	public EventLog getEventLog(Long id) {
		return lrepo.findOne(id);
	}

	private Result handleUserAssignment(Event ev) {
		User u = urepo.findByOpenId(ev.getPayload().getUser().getOpenId());
		if (u == null) {
			u = new User();
			log.debug("Creating user:" + ev.getPayload().getUser().getEmail());
		}
		u.setEmail(ev.getPayload().getUser().getEmail());
		u.setFirstName(ev.getPayload().getUser().getFirstName());
		u.setLanguage(ev.getPayload().getUser().getLanguage());
		u.setOpenId(ev.getPayload().getUser().getOpenId());
		Account a = u.getAccount();
		if (a == null)
			a = new Account();
		a.setAccountIdentifier(ev.getPayload().getAccount().getAccountIdentifier());
		a.setStatus(ev.getPayload().getAccount().getStatus());
		u.setAccount(a);

		urepo.save(u);
		return new Result(true, "Succeed");
	}

	private Result handleUserUnassignment(Event ev) {
		User u = urepo.findByOpenId(ev.getPayload().getUser().getOpenId());
		if (u != null)
			urepo.delete(u);
		return new Result(true, "Succeed");
	}

	private Result handleSubscriptionOrder(Event ev) throws Exception {
		User u = urepo.findByOpenId(ev.getCreator().getOpenId());
		if (u != null) {
			u.getAccount().setEditionCode(ev.getPayload().getOrder().getEditionCode());
			u.getAccount().setPricingDuration(ev.getPayload().getOrder().getPricingDuration());
			return new Result(true, "Succeed");
		} else
			return new Result(false, "No user found for this creator: " + ev.getCreator().getEmail());
	}

	private Result handleSubscriptionCancell(Event ev) {
		User u = urepo.findByOpenId(ev.getCreator().getOpenId());
		if (u != null) {
			arepo.delete(u.getAccount());
			return new Result(true, "Succeed");
		}
		return new Result(false, "No account found.");
	}

	public void logEvent(String xml, String url) {
		try {
			log.debug("XML:" + xml);
			EventLog el = new EventLog();
			el.setDate(new Date());
			el.setXml(xml);
			el.setUrl(url);
			lrepo.save(el);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
	}
}

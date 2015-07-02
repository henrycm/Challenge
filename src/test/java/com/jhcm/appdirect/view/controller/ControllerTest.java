package com.jhcm.appdirect.view.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jhcm.appdirect.config.AppConfig;
import com.jhcm.appdirect.config.MVCConfig;
import com.jhcm.appdirect.config.PersistenceConfig;
import com.jhcm.appdirect.integration.RemoteService;
import com.jhcm.appdirect.view.AppDirectEventController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, PersistenceConfig.class, MVCConfig.class })
public class ControllerTest {

	private Logger log = LoggerFactory.getLogger(ControllerTest.class);

	@Resource
	protected ApplicationContext appContext;

	@Resource
	private WebApplicationContext wac;

	@Mock
	private RemoteService rs;

	@Resource
	private AppDirectEventController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		ReflectionTestUtils.setField(controller, "remoteService", rs);
	}

	@Test
	public void testUserAssignment() throws Exception {
		log.debug("Testing User assignment...");
		when(rs.getXml(any(String.class))).thenReturn(getXml("dummyAssign.xml"));
		final String testUrl = "dummyUrl";
		MvcResult result = this.mockMvc.perform(get("/rest/event?eventUrl=" + testUrl)).andExpect(status().isOk())
				.andReturn();
		assertThat("Result contains Succeed!", result.getResponse().getContentAsString(), containsString("Succeed"));
	}

	@Test
	public void testUserUnAssignment() throws Exception {
		log.debug("Testing User unAssignment...");
		when(rs.getXml(any(String.class))).thenReturn(getXml("dummyUnassign.xml"));
		final String testUrl = "dummyUrl";
		MvcResult result = this.mockMvc.perform(get("/rest/event?eventUrl=" + testUrl)).andExpect(status().isOk())
				.andReturn();
		assertThat("Result contains Succeed!", result.getResponse().getContentAsString(), containsString("Succeed"));
	}

	@Test
	public void testListUsers() throws Exception {
		mockMvc.perform(get("/list")).andExpect(status().isOk()).andExpect(view().name("user"))
				.andExpect(forwardedUrl("/WEB-INF/pages/user.jsp"))
				.andExpect(model().attribute("page", hasProperty("content", hasSize(1))));
	}

	public String getXml(String filename) throws FileNotFoundException, IOException {
		return new String(Files.readAllBytes(Paths.get(appContext.getResource("classpath:xml/" + filename).getFile()
				.getAbsolutePath())));
	}
}

package com.jhcm.appdirect.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		rootContext.register(MvcConfig.class);
		rootContext.register(PersistenceConfig.class);
		rootContext.register(SecurityConfig.class);

		servletContext.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(MvcConfig.class);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(
				rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain",
				DelegatingFilterProxy.class);
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
	}
}
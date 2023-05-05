package com.spring.security.backend.security.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		// Ensure that WebSecurityConfig was loaded in our existing ApplicationInitializer
		return new Class[] { WebSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		
		//Specify the servlet mapping(s) for the DispatcherServlet — for example "/", "/app", etc.
		return new String[] {"/private", "/public", "/"};
	}
}
package org.cc.security.security;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cc.security.constants.UriConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutHandler.class);

	private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

	@Autowired
	protected ServletContext context;

	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LOGGER.info("Forcing user to logout? {}", request.getAttribute("FORCE_LOGOUT"));


		if (request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION) != null) {
			request.getSession().removeAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		}

		setAlwaysUseDefaultTargetUrl(true);
		setDefaultTargetUrl(UriConstants.LOGIN);

		super.onLogoutSuccess(request, response, authentication);
	}
	

}

package fr.eni.projetEncheres.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/hellofakechemin" })
public class firstLineOfDefense implements Filter {

	public firstLineOfDefense() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session;
		session = ((HttpServletRequest) request).getSession();

//		if (((HttpServletRequest) request).getServletPath().startsWith("/Login")) {
//			chain.doFilter(request, response);
//		} else if (((HttpServletRequest) request).getServletPath().startsWith("/Register")) {
//			chain.doFilter(request, response);
//		} else if (session.getAttribute("userID") != null) {
//			chain.doFilter(request, response);
//		} else {
//			((HttpServletResponse) response).sendRedirect("Login");
//		}
		chain.doFilter(request, response);

		if (((HttpServletRequest) request).getServletPath().startsWith("/Login")) {
			chain.doFilter(request, response);
		} else if (((HttpServletRequest) request).getServletPath().startsWith("/Register")) {
			chain.doFilter(request, response);
		} else if (session.getAttribute("userID") != null) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("Login");
		}


	}
}

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

@WebFilter(urlPatterns = { "/helloceciestfakechemin" })
public class AdminFilter implements Filter {

	public AdminFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		
		if ((Boolean) session.getAttribute("amIAdmin") == true) {
			chain.doFilter(request, response);
		} 
		else {
			((HttpServletResponse) response).sendRedirect("Login");
		}
<<<<<<< HEAD
	
=======

>>>>>>> branch 'main' of https://github.com/Deadrix/ENCHERES-ENI.git
	}
}

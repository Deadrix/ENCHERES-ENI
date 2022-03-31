package fr.eni.projetEncheres.controller;

import fr.eni.projetEncheres.model.bo.User;
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

@WebFilter(urlPatterns = {"/hellofakechemin"})
public class AdminFilter implements Filter {
  public void init(FilterConfig filterConfig) throws ServletException {}
  
  public void destroy() {}
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest)request).getSession();
    User tempUser = (User)session.getAttribute("Thierry");
    if (tempUser.getAmIAdmin()) {
      chain.doFilter(request, response);
    } else {
      ((HttpServletResponse)response).sendRedirect(String.valueOf(request.getServletContext().getContextPath()) + "/Login");
    } 
  }
}

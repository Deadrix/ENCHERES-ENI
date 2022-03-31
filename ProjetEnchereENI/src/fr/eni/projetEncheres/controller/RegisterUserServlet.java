package fr.eni.projetEncheres.controller;

import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/Register"})
public class RegisterUserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String alias = request.getParameter("alias");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if (alias.length() > 3 || (email.length() > 5 && password.length() > 4)) {
      User tempUser = new User(alias, request.getParameter("lastName"), 
          request.getParameter("firstName"), email, request.getParameter("telephone"), 
          request.getParameter("street"), request.getParameter("zipCode"), request.getParameter("city"), 
          password);
      try {
        UserManager.getInstance().registerProcess(tempUser);
      } catch (BLLException e) {
        request.setAttribute("registerErrorMessage", "something was wrong with your form");
        request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
        e.printStackTrace();
        throw new ServletException("F uped servlet edition");
      } 
      if (tempUser.getUserId().intValue() == 0) {
        request.setAttribute("registerErrorMessage", "something was wrong with your form");
        request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
      } else {
        HttpSession session = request.getSession();
        session.setAttribute("Thierry", tempUser);
        Boolean userIsConnected = Boolean.valueOf(true);
        session.setAttribute("connected", userIsConnected);
        Cookie HHAconnection = new Cookie("HHAconnection", tempUser.getEmail());
        HHAconnection.setMaxAge(1440);
        response.addCookie(HHAconnection);
        request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward((ServletRequest)request, (ServletResponse)response);
      } 
    } else {
      request.setAttribute("registerErrorMessage", "something was wrong with your form");
      request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
}

package fr.eni.projetEncheres.controller;

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

@WebServlet({"/Login"})
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("connected") == null) {
      Cookie[] cookies = request.getCookies();
      byte b;
      int i;
      Cookie[] arrayOfCookie1;
      for (i = (arrayOfCookie1 = cookies).length, b = 0; b < i; ) {
        Cookie chocolateFudge = arrayOfCookie1[b];
        if (chocolateFudge.getName().equals("HHAconnection"))
          request.setAttribute("email", chocolateFudge.getValue()); 
        b++;
      } 
      getServletContext().getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } else if (((Boolean)session.getAttribute("connected")).booleanValue()) {
      request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User tempUser = new User();
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    tempUser = UserManager.getInstance().login(email, password);
    HttpSession session = request.getSession();
    request.getSession().getAttribute("userID");
    if (tempUser.getUserId() != null && session.getAttribute("userID") == null) {
      session.setAttribute("Thierry", tempUser);
      Boolean userIsConnected = Boolean.valueOf(true);
      session.setAttribute("connected", userIsConnected);
      Cookie HHAconnection = new Cookie("HHAconnection", tempUser.getEmail());
      if (request.getParameter("rememberMe") != null) {
        HHAconnection.setMaxAge(31536000);
      } else {
        HHAconnection.setMaxAge(3600);
      } 
      response.addCookie(HHAconnection);
      response.sendRedirect("Login");
    } else if (request.getSession().getAttribute("userID") != null) {
      request.setAttribute("loginErrorMessage", "you are already logged in");
      request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } else {
      request.setAttribute("loginErrorMessage", "something wrong with your credentials");
      request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
}

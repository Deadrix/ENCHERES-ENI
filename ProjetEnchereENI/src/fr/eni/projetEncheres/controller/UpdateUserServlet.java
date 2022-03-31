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

@WebServlet({"/UpdateUser"})
public class UpdateUserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("fucking hell");
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User tempUser = (User)session.getAttribute("Thierry");
    tempUser.setAlias(request.getParameter("alias"));
    tempUser.setEmail(request.getParameter("email"));
    tempUser.setFirstName(request.getParameter("firstName"));
    tempUser.setLastName(request.getParameter("lastName"));
    tempUser.setTelephone(request.getParameter("telephone"));
    tempUser.setPostalCode(request.getParameter("postalCode"));
    tempUser.setStreet(request.getParameter("street"));
    tempUser.setCity(request.getParameter("city"));
    tempUser.setPassword(request.getParameter("password"));
    if (tempUser.getAlias().length() > 3 && 
    		tempUser.getEmail().length() > 5 && tempUser.getPassword().length() > 4) {
      try {
        UserManager.getInstance().updateProcess(tempUser);
      } catch (BLLException e) {
        request.setAttribute("updateErrorMessage", "something was wrong with the info provided");
        request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward((ServletRequest)request, (ServletResponse)response);
        e.printStackTrace();
        throw new ServletException(" Fuped servlet edition");
      } 
      if (tempUser.getUserId() == null) {
        request.setAttribute("updateErrorMessage", "i don't think you are logged in");
        request.getRequestDispatcher("/WEB-INF/hp.jsp").forward((ServletRequest)request, (ServletResponse)response);
      } else {
        Boolean userIsConnected = Boolean.valueOf(true);
        session.setAttribute("Thierry", tempUser);
        session.setAttribute("connected", userIsConnected);
        Cookie HHAconnection = new Cookie("HHAconnection", tempUser.getEmail());
        HHAconnection.setMaxAge(3600);
        response.addCookie(HHAconnection);
        request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward((ServletRequest)request, (ServletResponse)response);
      } 
    } else {
      request.setAttribute("updateErrorMessage", "something was wrong with your form");
      request.getRequestDispatcher("/ConnectedHP.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
}

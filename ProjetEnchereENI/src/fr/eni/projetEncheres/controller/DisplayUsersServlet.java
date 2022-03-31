package fr.eni.projetEncheres.controller;

import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/DisplayUsers"})
public class DisplayUsersServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.getWriter().append("Served at: My Convinience").append(request.getContextPath());
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User tempUser = (User)session.getAttribute("Thierry");
    User toDisplay = new User();
    toDisplay.setAlias(request.getParameter("alias"));
    toDisplay.setEmail(request.getParameter("email"));
    if (tempUser.getAmIAdmin()) {
      try {
        if (toDisplay.getAlias().length() >= 3) {
          UserManager.getInstance().selectByAlias(toDisplay);
        } else if (toDisplay.getEmail().length() >= 5) {
          UserManager.getInstance().selectByMail(toDisplay);
        } else if (!request.getParameter("userID").isEmpty()) {
          Integer userID = Integer.valueOf(request.getParameter("userID"));
          toDisplay = UserManager.getInstance().selectById(userID);
        } 
      } catch (BLLException e) {
        e.printStackTrace();
        throw new ServletException(" you fucked up boy servlet edition");
      } 
      request.setAttribute("userID", toDisplay.getUserId());
      request.setAttribute("alias", toDisplay.getAlias());
      request.setAttribute("lastName", toDisplay.getLastName());
      request.setAttribute("firstName", toDisplay.getFirstName());
      request.setAttribute("email", toDisplay.getEmail());
      request.setAttribute("telephone", toDisplay.getTelephone());
      request.setAttribute("street", toDisplay.getStreet());
      request.setAttribute("postalCode", toDisplay.getPostalCode());
      request.setAttribute("city", toDisplay.getCity());
      request.getRequestDispatcher("Admin.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } else {
      if (toDisplay.getAlias().length() >= 3) {
        try {
          UserManager.getInstance().selectByAlias(tempUser);
        } catch (BLLException e) {
          e.printStackTrace();
          throw new ServletException(" you fucked up boy servlet edition");
        } 
      } else {
        try {
          UserManager.getInstance().selectByMail(tempUser);
        } catch (BLLException e) {
          e.printStackTrace();
          throw new ServletException(" you fucked up boy servlet edition");
        } 
      } 
      request.setAttribute("alias", tempUser.getAlias());
      request.setAttribute("lastName", tempUser.getLastName());
      request.setAttribute("firstName", tempUser.getFirstName());
      request.setAttribute("email", tempUser.getEmail());
      request.setAttribute("telephone", tempUser.getTelephone());
      request.setAttribute("street", tempUser.getStreet());
      request.setAttribute("postalCode", tempUser.getPostalCode());
      request.setAttribute("city", tempUser.getCity());
      response.sendRedirect("/home.jsp");
    } 
  }
}

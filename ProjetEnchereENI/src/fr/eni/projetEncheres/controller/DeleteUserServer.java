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

@WebServlet({"/DeleteUser"})
public class DeleteUserServer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/WEB-INF/TestServletAndFunction.jsp").forward((ServletRequest)request, (ServletResponse)response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User tempUser = (User)session.getAttribute("Thierry");
    if (tempUser.getAmIAdmin()) {
      Integer userIdToDelete = Integer.valueOf(request.getParameter("userIDToDelete"));
      try {
        UserManager.getInstance().delete(userIdToDelete);
      } catch (BLLException e) {
        e.printStackTrace();
        throw new ServletException(" you fucked up boy servlet edition");
      } 
      request.getRequestDispatcher("Admin.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } else {
      String password = request.getParameter("password");
      if (password.length() > 4) {
        try {
          UserManager.getInstance().delete(tempUser.getUserId());
        } catch (BLLException e) {
          e.printStackTrace();
          throw new ServletException(" you fucked up boy servlet edition");
        } 
        session.removeAttribute("Thierry");
        session.removeAttribute("connected");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        session.invalidate();
        response.sendRedirect("Login");
      } 
    } 
  }
}

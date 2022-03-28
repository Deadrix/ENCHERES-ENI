package fr.eni.projetEncheres.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;


@WebServlet("/DeleteUser")
public class DeleteUserServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/TestServletAndFunction.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		int userId;
//		userId = Integer.parseInt(request.getParameter("userId"));
//		try {
//			UserManager.getInstance().delete(userId);
//		} catch (DALException e) {
//			e.printStackTrace();
//		};

		HttpSession session = request.getSession();
		String alias, email, password;
		alias = (String) session.getAttribute("alias");
		email = (String) session.getAttribute("email");
		password = request.getParameter("password");
		
		
		
		
		

		if (alias.length() > 3 || email.length() > 5 && password.length() > 4) {

			User tempUser = new User((Integer)session.getAttribute("userID"),alias, request.getParameter("lastName"),
					request.getParameter("firstName"), email, request.getParameter("telephone"),
					request.getParameter("street"), request.getParameter("zipCode"), request.getParameter("city"),
					password, (Integer)session.getAttribute("credit"), (Boolean) session.getAttribute("amIAdmin"));
			try {
				UserManager.getInstance().delete(tempUser.getUserId());
				session.removeAttribute("userID");
				session.removeAttribute("alias");
				session.removeAttribute("lastName");
				session.removeAttribute("firstName");
				session.removeAttribute("email");
				session.removeAttribute("telephone");
				session.removeAttribute("street");
				session.removeAttribute("postalCode");
				session.removeAttribute("city");
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "0");
				session.invalidate();
				response.sendRedirect("Login");				
			} catch (DALException e) {
				request.setAttribute("updateErrorMessage", "something was wrong with the info provided");
				request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward(request, response);
				e.printStackTrace();
			}
			
		}
		

	}

}

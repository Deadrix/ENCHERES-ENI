package fr.eni.projetEncheres.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.UserManager;


@WebServlet("/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("fucking hell");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String alias, email, password;
		alias = request.getParameter("alias");
		email = request.getParameter("email");
		password = request.getParameter("password");

		if (alias.length() > 3 || email.length() > 5 && password.length() > 4) {

			User tempUser = new User(alias, request.getParameter("lastName"),
					request.getParameter("firstName"), email, request.getParameter("telephone"),
					request.getParameter("street"), request.getParameter("zipCode"), request.getParameter("city"),
					password, (Integer)session.getAttribute("credit"));
			tempUser.setUserId((Integer)session.getAttribute("userID"));
			try {
				UserManager.getInstance().updateProcess(tempUser);
			} catch (BLLException | DALException e) {
				request.setAttribute("updateErrorMessage", "something was wrong with the info provided");
				request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward(request, response);
				e.printStackTrace();
			}

			if (tempUser.getUserId() != session.getAttribute("userID")) {
				request.setAttribute("updateErrorMessage", "i don't think you are logged in");
				request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
			} else {

				
				session.setAttribute("userID", tempUser.getUserId());
				session.setAttribute("alias", tempUser.getAlias());
				session.setAttribute("lastName", tempUser.getLastName());
				session.setAttribute("firstName", tempUser.getFirstName());
				session.setAttribute("email", tempUser.getEmail());
				session.setAttribute("telephone", tempUser.getTelephone());
				session.setAttribute("street", tempUser.getStreet());
				session.setAttribute("postalCode", tempUser.getPostalCode());
				session.setAttribute("city" ,tempUser.getCity());
				session.setAttribute("credit" ,tempUser.getCredit());
				Cookie HHAconnection = new Cookie("HHAconnection", tempUser.getEmail());
				HHAconnection.setMaxAge(60*5);
				response.addCookie(HHAconnection);
				request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward(request, response);
				
			}
		} else {
			request.setAttribute("updateErrorMessage", "something was wrong with your form");
			request.getRequestDispatcher("/ConnectedHP.jsp").forward(request, response);
		}

	}

}

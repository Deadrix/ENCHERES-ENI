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

import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String alias, email, password;
		alias = request.getParameter("alias");
		email = request.getParameter("email");
		password = request.getParameter("password");

		if (alias.length() > 3 || email.length() > 5 && password.length() > 4) {

			User tempUser = new User(alias, request.getParameter("lastName"),
					request.getParameter("firstName"), email, request.getParameter("telephone"),
					request.getParameter("street"), request.getParameter("zipCode"), request.getParameter("city"),
					password);

			try {
				UserManager.getInstance().registerProcess(tempUser);
			} catch (BLLException e) {
				request.setAttribute("registerErrorMessage", "something was wrong with your form");
				request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
				e.printStackTrace();
			}

			if (tempUser.getUserId() == 0) {
				request.setAttribute("registerErrorMessage", "something was wrong with your form");
				request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();

				
				session.setAttribute("userID", tempUser.getUserId());
				session.setAttribute("alias", tempUser.getAlias());
				session.setAttribute("lastName", tempUser.getLastName());
				session.setAttribute("firstName", tempUser.getFirstName());
				session.setAttribute("email", tempUser.getEmail());
				session.setAttribute("telephone", tempUser.getTelephone());
				session.setAttribute("street", tempUser.getStreet());
				session.setAttribute("postalCode", tempUser.getPostalCode());
				session.setAttribute("city" ,tempUser.getCity());
				session.setAttribute("credit", tempUser.getCredit());
				session.setAttribute("amIAdmin", tempUser.getAmIAdmin());
				Cookie HHAconnection = new Cookie("HHAconnection", tempUser.getEmail());
				HHAconnection.setMaxAge(60*24);
				response.addCookie(HHAconnection);
				request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward(request, response);
				
			}
		} else {
			request.setAttribute("registerErrorMessage", "something was wrong with your form");
			request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
		}

	}
}

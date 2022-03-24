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

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User tempUser;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		tempUser = UserManager.getInstance().login(email, password);

		if (tempUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", tempUser);
			Cookie loggedIn = new Cookie("login", tempUser.getEmail());
			loggedIn.setMaxAge(-1);
			response.addCookie(loggedIn);
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/TestSuccess.jsp");
			dispatch.forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/view/TestServletAndFunction.jsp").forward(request,
					response);
		}

	}

}

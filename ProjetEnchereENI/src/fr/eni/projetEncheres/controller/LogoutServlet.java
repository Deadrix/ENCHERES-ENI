package fr.eni.projetEncheres.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	if (request.getSession().getAttribute("userID") == null){
		response.sendRedirect("Login");
	}
//		RequestDispatcher view = request.getRequestDispatcher("/Login");
//		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
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
	}

}

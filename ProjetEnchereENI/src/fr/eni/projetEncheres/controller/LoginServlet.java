package fr.eni.projetEncheres.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import javafx.print.Printer;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie cookies[] = request.getCookies();
		for(Cookie chocolateFudge : cookies) {
			if (chocolateFudge.getName().equals("HHAconnection")){
				request.setAttribute("email", chocolateFudge.getValue());
			}
		}
		getServletContext().getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User tempUser = new User();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		tempUser = UserManager.getInstance().login(email, password);

		HttpSession session = request.getSession();
		request.getSession().getAttribute("userID");
		if (tempUser.getUserId() != null && request.getSession().getAttribute("userID") == null) {
			
		
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
			session.setAttribute("amIAdmin", tempUser.getAmIAdmin());
			Cookie HHAconnection = new Cookie("HHAconnection", tempUser.getEmail());
			
			if (request.getParameter("rememberMe") != null){
				HHAconnection.setMaxAge(3600*24*365);								
			} else {
				HHAconnection.setMaxAge(3600);								
			}
			response.addCookie(HHAconnection);
			request.getRequestDispatcher("/WEB-INF/ConnectedHP.jsp").forward(request, response);
			
		} else if (request.getSession().getAttribute("userID") != null){
		request.setAttribute("loginErrorMessage", "you are already logged in");
		request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
		}else {
			request.setAttribute("loginErrorMessage", "something wrong with your credentials");
			request.getRequestDispatcher("/WEB-INF/hp.jsp").forward(request, response);
		}

	}

}

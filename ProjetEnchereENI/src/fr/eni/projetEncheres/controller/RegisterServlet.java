package fr.eni.projetEncheres.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/TestServletAndFunction.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User(request.getParameter("alias"), request.getParameter("lastName"),
				request.getParameter("firstName"), request.getParameter("email"), request.getParameter("telephone"),
				request.getParameter("street"), request.getParameter("postalCode"), request.getParameter("city"),
				request.getParameter("password"));

//	try {
//		UserManager.getInstance().insert(user);
//	} catch (DALException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/TestSuccess.jsp");
//	dispatch.forward(request, response);



		User tempUser = new User();
		try {
			tempUser = UserManager.getInstance().selectByMail(user);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (user.getEmail().equals(tempUser.getEmail()) || user.getPassword() != null) {
			PrintWriter out = response.getWriter();
			out.print("Dude, What are you doing?");
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/TestServletAndFunction.jsp");
			dispatch.forward(request, response);
		} else {
			try {
				UserManager.getInstance().insert(user);
				
			} catch (DALException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/TestSuccess.jsp");
			dispatch.forward(request, response);
		}

	}
}

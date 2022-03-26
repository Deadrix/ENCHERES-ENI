package fr.eni.projetEncheres.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.dal.DALException;


@WebServlet("/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/TestServletAndFunction.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId;
		userId = Integer.parseInt(request.getParameter("userId"));
		try {
			UserManager.getInstance().delete(userId);
		} catch (DALException e) {
			e.printStackTrace();
		};
//		request.getRequestDispatcher("/WEB-INF/TestSuccess.jsp").forward(request, response);
		response.sendRedirect("/WEB-INF/TestSuccess.jsp");
		

	}

}

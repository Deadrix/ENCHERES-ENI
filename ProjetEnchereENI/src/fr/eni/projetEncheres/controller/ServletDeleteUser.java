package fr.eni.projetEncheres.controller;

import java.io.IOException;

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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id;
		id = Integer.parseInt(request.getParameter("id"));
		try {
			UserManager.getInstance().delete(id);
		} catch (DALException e) {
			e.printStackTrace();
		};
		response.sendRedirect("home.html");

	}

}

package fr.eni.projetEncheres.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.model.bo.User;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		getServletContext().getRequestDispatcher("/WEB-INF/JSP/HTML/Login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User u;
		u = new User(request.getParameter(txtName), 
				request.getParameter(txtFirstName),
				request.getParameter(txtEmail),
				request.getParameter(txtPwd);
				
		System.out.println("dans le DoPost");
	}

}

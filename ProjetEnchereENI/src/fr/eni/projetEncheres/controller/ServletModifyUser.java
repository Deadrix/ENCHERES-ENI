package fr.eni.projetEncheres.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.bll.UserManager;

/**
 * Servlet implementation class ServletModifyUser
 */
@WebServlet("/ServletModifyUser")
public class ServletModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId;
		userId = Integer.parseInt(request.getParameter("id"));
		User u = null;
		u = UserManager.getInstance().findById(userId);
		request.setAttribute("user", u);
		getServletContext().getRequestDispatcher("/WEB-INF/modifyUser.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId;
		String alias, lastName, firstName, email, telephone, street, postalCode, city, password;
		User u;

		userId = Integer.parseInt(request.getParameter("userId"));
		alias = request.getParameter("txtAlias");
		lastName = request.getParameter("txtLastName");
		firstName = request.getParameter("txtFirstName");
		email = request.getParameter("txtEmail");
		telephone = request.getParameter("txtTelephone");
		street = request.getParameter("txtStreet");
		postalCode = request.getParameter("txtPostalCode");
		city = request.getParameter("txtCity");
		password = request.getParameter("txtPassword");

		u = UserManager.getInstance().findById(userId);

		u.setLastName(lastName);
		u.setFirstName(firstName);
		u.setEmail(email);
		u.setTelephone(telephone);
		u.setStreet(street);
		u.setPostalCode(postalCode);
		u.setCity(city);
		u.setPassword(password);

		UserManager.getInstance().modifier(u);
		response.sendRedirect("home.html");
	}

}

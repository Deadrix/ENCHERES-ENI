package fr.eni.projetEncheres.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;

@WebServlet("/DisplayUsers")
public class DisplayUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: My Convinience").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("userID") != null) {
			User tempUser = new User();
			String alias = request.getParameter("alias");
			String email = request.getParameter("email");
			tempUser.setAlias(alias);
			tempUser.setEmail(email);

			if ((Boolean) session.getAttribute("amIAdmin") == true) {
				try {

					if (alias.length()>= 3) {
						UserManager.getInstance().selectByAlias(tempUser);
					} else if (email.length() >= 5) {
						UserManager.getInstance().selectByMail(tempUser);
					}
					else if (!request.getParameter("userID").isEmpty()){
						Integer userID = Integer.valueOf(request.getParameter("userID"));
						UserManager.getInstance().selectById(userID);
					}
				} catch (DALException e) {
					request.setAttribute("updateErrorMessage", "can find the infos");
					request.getRequestDispatcher("Admin.jsp").forward(request, response);
					e.printStackTrace();
				}

				request.setAttribute("userID", tempUser.getUserId());
				request.setAttribute("alias", tempUser.getAlias());
				request.setAttribute("lastName", tempUser.getLastName());
				request.setAttribute("firstName", tempUser.getFirstName());
				request.setAttribute("email", tempUser.getEmail());
				request.setAttribute("telephone", tempUser.getTelephone());
				request.setAttribute("street", tempUser.getStreet());
				request.setAttribute("postalCode", tempUser.getPostalCode());
				request.setAttribute("city", tempUser.getCity());
				request.getRequestDispatcher("Admin.jsp").forward(request, response);

			} else {

				try {
					if (!alias.isEmpty()) {
						UserManager.getInstance().selectByAlias(tempUser);
					} else {
						UserManager.getInstance().selectByMail(tempUser);
					}
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("alias", tempUser.getAlias());
				request.setAttribute("lastName", tempUser.getLastName());
				request.setAttribute("firstName", tempUser.getFirstName());
				request.setAttribute("email", tempUser.getEmail());
				request.setAttribute("telephone", tempUser.getTelephone());
				request.setAttribute("street", tempUser.getStreet());
				request.setAttribute("postalCode", tempUser.getPostalCode());
				request.setAttribute("city", tempUser.getCity());
				response.sendRedirect("/home.jsp");
			}
		}
	}
}
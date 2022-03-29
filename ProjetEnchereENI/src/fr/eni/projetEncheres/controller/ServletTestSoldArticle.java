package fr.eni.projetEncheres.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.model.bll.CategoryManager;
import fr.eni.projetEncheres.model.bll.PickUpManager;
import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.bo.PickUp;
import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;

/**
 * Servlet implementation class ServletTestSoldArticle
 */
@WebServlet("/ServletTestSoldArticle")
public class ServletTestSoldArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  PickUpManager pm = new PickUpManager();
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/TestSoldArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SoldArticle article = new SoldArticle();
		
		
			
			String article_name = request.getParameter("articleName");
			String description = request.getParameter("description");
			//Catégorie à récup' ??
			Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
			
			LocalDate auction_start_date = LocalDate.parse((request.getParameter("auctionStart")));
			LocalDate auction_end_date = LocalDate.parse((request.getParameter("auctionEnd")));
			Integer initial_price = Integer.parseInt(request.getParameter("initialPrice"));
			Integer final_price = Integer.parseInt(request.getParameter("soldPrice"));
			
			String street = request.getParameter("street");
			String postalCode = request.getParameter("postalCode");
			String city = request.getParameter("city");
		
		Category cat = CategoryManager.getInstance().selectById(categoryId);
		User user = (User) session.getAttribute("userID");
			
			PickUp pickup = new PickUp();
				pickup.setRueRetrait(street);
				pickup.setCodePostalRetrait(postalCode);
				pickup.setVilleRetrait(city);
				
			pm.addPickUp(pickup);
				article.setArticleName(article_name);
				article.setDescription(description);
				article.setAuctionStart(auction_start_date);
				article.setAuctionEnd(auction_end_date);
				article.setInitialPrice(initial_price);
				article.setSoldPrice(final_price);
				
			
	}

}

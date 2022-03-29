package fr.eni.projetEncheres.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.model.bll.ArticleManager;
import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.CategoryManager;
import fr.eni.projetEncheres.model.bll.PickUpManager;
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

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/TestSoldArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		SoldArticle article = new SoldArticle();
		CategoryManager catMng = new CategoryManager();
		ArticleManager artMng = new ArticleManager();
		PickUpManager pickUpMng = new PickUpManager();
		User user = new User();

		// ARTICLE

		article.setArticleName(request.getParameter("articleName"));
		article.setDescription(request.getParameter("description"));
		if (request.getParameter("RadioButtonDate").equals("Immediat")) {
			article.setAuctionStart(LocalDate.now());
			article.setState(1);

		} else {
			article.setAuctionStart(LocalDate.parse((request.getParameter("auctionStart"))));

			article.setAuctionEnd(LocalDate.parse((request.getParameter("auctionEnd"))));
			article.setInitialPrice(Integer.parseInt(request.getParameter("initialPrice")));
			article.setSoldPrice(Integer.parseInt(request.getParameter("soldPrice")));
			//Comment récup' l'objet user connecté
			article.setSeller((User)request.getAttribute("userID"));

			article.setState(0);
		}
		article.setAuctionEnd(LocalDate.parse((request.getParameter("auctionEnd"))));
		article.setInitialPrice(Integer.parseInt(request.getParameter("initialPrice")));
		article.setSoldPrice(Integer.parseInt(request.getParameter("soldPrice")));
		// Comment récup' l'objet user connecté
		article.setSeller((User) request.getAttribute("userID"));
		try {

			article.setCategory(catMng.selectById(Integer.parseInt(request.getParameter("categoryId"))));

			if(LocalDate.parse(request.getParameter("auctionStart")).isAfter(LocalDate.now())) {
				article.setState(0);
			}
			
							
			
			
			
			
		
			//String article_name = request.getParameter("articleName");
			//String description = request.getParameter("description");
			//Catégorie à récup' ??
			//Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
			
//			LocalDate auction_start_date = LocalDate.parse((request.getParameter("auctionStart")));
//			LocalDate auction_end_date = LocalDate.parse((request.getParameter("auctionEnd")));
//			Integer initial_price = Integer.parseInt(request.getParameter("initialPrice"));
//			Integer final_price = Integer.parseInt(request.getParameter("soldPrice"));
			
//			String street = request.getParameter("street");
//			String postalCode = request.getParameter("postalCode");
//			String city = request.getParameter("city");
//		
//	
//			Category cat = CategoryManager.getInstance().selectById(categoryId);
//			User user = (User) session.getAttribute("userID");
			
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
				
				
				
				
				
				
		} catch (DALException | BLLException e) {
			// TODO Auto-generated catch block

		} catch (NumberFormatException | BLLException e) {

			e.printStackTrace();
		}

		try {
			artMng.insert(article);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// PICK-UP

		PickUp pickup = new PickUp();
		pickup.setArticleToPickUp(article.getArticleId());
		pickup.setRueRetrait(request.getParameter("street"));
		pickup.setCodePostalRetrait(request.getParameter("postalCode"));
		pickup.setVilleRetrait(request.getParameter("city"));

		try {
			pickUpMng.addPickUp(pickup);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

}

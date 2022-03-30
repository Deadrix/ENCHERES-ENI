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

		User loggedUser = (User) request.getSession().getAttribute("Thierry");
		SoldArticle article = new SoldArticle();
		CategoryManager catMng = new CategoryManager();
		ArticleManager artMng = new ArticleManager();
		PickUpManager pickUpMng = new PickUpManager();


		// ARTICLE

		article.setArticleName(request.getParameter("articleName"));
		article.setDescription(request.getParameter("description"));
		if (request.getParameter("RadioButtonDate").equals("Immediat")) {
			article.setAuctionStart(LocalDate.now());
			article.setState(1);

		} else {
			article.setAuctionStart(LocalDate.parse((request.getParameter("auctionStart"))));
			article.setState(0);
		}
		article.setAuctionEnd(LocalDate.parse((request.getParameter("auctionEnd"))));
		article.setInitialPrice(Integer.parseInt(request.getParameter("initialPrice")));
		article.setSoldPrice(Integer.parseInt(request.getParameter("soldPrice")));
		article.setSeller(loggedUser);
		try {
			article.setCategory(catMng.selectById(Integer.parseInt(request.getParameter("categoryId"))));
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

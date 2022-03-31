package fr.eni.projetEncheres.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.model.bll.ArticleManager;
import fr.eni.projetEncheres.model.bll.AuctionManager;
import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.PickUpManager;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.Auction;
import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.bo.PickUp;
import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;

/**
 * Servlet implementation class AuctionDetails
 */
@WebServlet("/AuctionDetailServlet")
public class AuctionDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArticleManager articleManager = new ArticleManager();
	AuctionManager auctionManager = new AuctionManager();
	UserManager userManager = new UserManager();
	PickUpManager pickUpManager = new PickUpManager();
	//User seller = new User();
	private static int no_article;
	private static int noUser;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupère le numéro de l'utilisateur courant
//		User no_user = (User) request.getSession().getAttribute("Thierry");
		
//		noUser = no_user.getUserId();
		noUser = 3;
		request.setAttribute("noUser", noUser);
		System.out.println("userId " + noUser);

		// On récupère le numéro de l'article
//		if (request.getParameter("articleId") != null) {
//			no_article = Integer.parseInt(request.getParameter("articleId"));
//			System.out.println("numero article :" + no_article);
//		}
		
		no_article = 3;
		request.setAttribute("no_article", noUser);
		System.out.println("no_article " + noUser);

		// On récupère la date du jour
		LocalDate today = LocalDate.now();
		request.setAttribute("today", today);
		
		
		// On récupère la date de fin d'enchere
		try {
			
			SoldArticle art = articleManager.selectById(no_article);
			request.setAttribute("articleSold", art);

			LocalDate auctionEnds = art.getAuctionEnd();

			boolean isBefore = auctionEnds.isBefore(today);

			if (isBefore == true) {
				request.setAttribute("isBefore", 1);
			} else {
				request.setAttribute("isBefore", 0);
			}

			//int bestAuction = auctionManager.selectBestAuctionFromArticle(no_article).getItemPrice();
			
			// User ID of buyer
			int auctionWinnerId = art.getBuyer().getUserId();
			request.setAttribute("auctionWinner", auctionWinnerId);

			// Name of buyer
			String auctionWinnerName = userManager.selectById(auctionWinnerId).getAlias();
			request.setAttribute("auctionWinnerName", auctionWinnerName);


			

			// category
			Category category = art.getCategory();
			request.setAttribute("category", category);

			// best bid
			Auction highestAuction = auctionManager.selectBestAuctionFromArticle(no_article);
			request.setAttribute("highestAuction", highestAuction);

			// pickup si défini
			PickUp pickUpLocation = pickUpManager.getPickUp(no_article);
			request.setAttribute("pickUpLocation", pickUpLocation);

			// display seller 
			User seller = art.getSeller();
			request.setAttribute("seller", seller);
		} catch (DALException | BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getServletContext().getRequestDispatcher("/WEB-INF/auctionDetail.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pick Up And Credit Management
		String thisPickUp = request.getParameter("pickUp");
		int finalPrice;
		try {
			finalPrice = articleManager.selectById(no_article).getSoldPrice();
		if (thisPickUp != null) {
			System.out.println("Sold at = " + finalPrice);
			if (finalPrice == 0) {
				int bestOffer = auctionManager.selectBestAuctionFromArticle(no_article).getItemPrice();
				// Saving new price
				articleManager.updateSoldPrice(articleManager.selectById(no_article));
				// Créditer le vendeur du montant de l'enchère
				int buyerCredit = auctionManager.selectBestAuctionFromArticle(no_article).getBuyer().getCredit();
				articleManager.selectById(no_article).getSeller()
						.setCredit(articleManager.selectById(no_article).getSeller().getCredit() + buyerCredit);
			} else {
				PrintWriter out = response.getWriter();
				out.println("Le retrait a déjà été effectué et votre solde crédité du montant de l'enchère");
				out.close();
			}
		}
		} catch (BLLException e) {
			e.printStackTrace();
		}
//		request.getServletContext().getRequestDispatcher("/AuctionList").forward(request, response);
	}

}
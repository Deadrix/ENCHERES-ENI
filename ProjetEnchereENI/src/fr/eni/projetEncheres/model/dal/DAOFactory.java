package fr.eni.projetEncheres.model.dal;

import fr.eni.projetEncheres.model.bo.Auction;
import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.bo.PickUp;
import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.bo.User;

public class DAOFactory {

	public static DAO<SoldArticle> getArticleDAO() {
		return new SoldArticleDAOImpl();
	}

	public static DAO<Category> getCategoryDAO() {
		return new CategoryDAOImpl();
	}

	public static DAO<PickUp> getPickUpDAO() {
		return new PickUpDAOImpl();
	}

	public static DAO<User> getUserDAO() {
		return new UserDAOImpl();
	}

	public static DAO<Auction> getAuctionDAO() {
		return new AuctionDAOImpl();
	}

}

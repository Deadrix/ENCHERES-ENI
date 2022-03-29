package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Auction;
import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.dal.AuctionDAO;
import fr.eni.projetEncheres.model.dal.AuctionDAOImpl;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class AuctionManager {

	private static AuctionManager instanceOfAuctionManager = null;
	private AuctionDAOImpl aAuctionDAOImpl = (AuctionDAOImpl) DAOFactory.getAuctionDAO();
	
	public AuctionManager(AuctionDAOImpl aAuctionDAOImpl) {
		this.aAuctionDAOImpl = aAuctionDAOImpl;
		}
	
	public static AuctionManager getInstance() {
		if (instanceOfAuctionManager ==null) {
			instanceOfAuctionManager = new AuctionManager((AuctionDAOImpl) DAOFactory.getAuctionDAO());
		}
		return instanceOfAuctionManager;
	}
	
	public void insert(Auction auction) throws DALException {
		aAuctionDAOImpl.insert(auction);
	}
	
	public void update(Auction auction) throws DALException {
		aAuctionDAOImpl.update(auction);
	}
	
	public List<Auction> selectAll() throws DALException {
		return aAuctionDAOImpl.selectAll();
	}
	
	public Auction selectBestAuctionFromArticle(SoldArticle article) throws DALException {
		return aAuctionDAOImpl.selectBestAuctionFromArticle(article);
	}

	public Auction selectBestAuctionFromArticle(int articleId) throws DALException {
		return aAuctionDAOImpl.selectBestAuctionFromArticle(articleId);
	}

	public Auction selectById(int idObject) throws DALException {
		return null;
	}

	public void delete(int idObject) throws DALException {
		
	}
	
}

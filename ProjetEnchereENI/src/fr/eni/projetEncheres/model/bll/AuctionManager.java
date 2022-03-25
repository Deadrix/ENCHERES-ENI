package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Auction;
import fr.eni.projetEncheres.model.dal.AuctionDAOImpl;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class AuctionManager {

	private static AuctionManager instanceofUserManager = null;
	private AuctionDAOImpl aAuctionDAOImpl = (AuctionDAOImpl) DAOFactory.getAuctionDAO();
	
	public AuctionManager(AuctionDAOImpl aAuctionDAOImpl) {
		this.aAuctionDAOImpl = aAuctionDAOImpl;
		}
	
	public static AuctionManager getInstance() {
		if (instanceofUserManager ==null) {
			instanceofUserManager = new AuctionManager((AuctionDAOImpl) DAOFactory.getAuctionDAO());
		}
		return instanceofUserManager;
	}
	
	public void insert(Auction auction) throws DALException {
		aAuctionDAOImpl.insert(auction);
	}
	
	public void update(Auction auction) throws DALException {
		aAuctionDAOImpl.update(auction);
	}
	
	public void delete(Integer idObject) throws DALException {
		aAuctionDAOImpl.delete(idObject);
	}
	
	public List<Auction> selectAll() throws DALException {
		return aAuctionDAOImpl.selectAll();
		}

/*
 *  Je ne vois pas comment réup' la valeurcorrespondant au prix de l'enchère en cours
 * 
	public Auction selectLastAuctionFromArticle (Integer lastAuctionFromArticle) throws DALException {
		return aAuctionDAOImpl.selectLastAuctionFromArticle(lastAuctionFromArticle);
	}
	*/
	
	
	
	
	
	
}

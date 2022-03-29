package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Auction;
import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.dal.AuctionDAO;
import fr.eni.projetEncheres.model.dal.AuctionDAOImpl;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAO;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class AuctionManager {

//	private static AuctionManager instanceOfAuctionManager = null;
//private AuctionDAOImpl aAuctionDAOImpl = (AuctionDAOImpl) DAOFactory.getAuctionDAO();
	
//	public AuctionManager(AuctionDAOImpl aAuctionDAOImpl) {
//		this.aAuctionDAOImpl = aAuctionDAOImpl;
//		}
	
	private DAO<Auction> auctionDAO;
	
	public AuctionManager() {
		this.auctionDAO = DAOFactory.getAuctionDAO();
	}

//	public static AuctionManager getInstance() throws BLLException {
//		if (instanceOfAuctionManager ==null) {
//			instanceOfAuctionManager = new AuctionManager((AuctionDAOImpl) DAOFactory.getAuctionDAO());
//		}
//		return instanceOfAuctionManager;
//	}
	
	public void insert(Auction auction) throws BLLException {
		try {
			auctionDAO.insert(auction);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
		}
	}
	
	public void update(Auction auction) throws BLLException {
		try {
			auctionDAO.update(auction);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Update failed - ", e);
		}
	}
	
	public List<Auction> selectAll() throws BLLException {
		try {
			return auctionDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select All failed - ", e);
		}
	}
	
	public Auction selectBestAuctionFromArticle(SoldArticle article) throws BLLException {
		try {
			return ((AuctionDAO) auctionDAO).selectBestAuctionFromArticle(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select Best Auction From Article failed - ", e);
		}
	}

	public Auction selectBestAuctionFromArticle(int articleId) throws BLLException {
		try {
			return ((AuctionDAO) auctionDAO).selectBestAuctionFromArticle(articleId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select Best Auction Frome Article failed - ", e);
		}
	}

	public Auction selectById(int idObject) throws BLLException {
		return null;
	}

	public void delete(int idObject) throws BLLException {
		
	}
	
}

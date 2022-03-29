package fr.eni.projetEncheres.model.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAO;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.SoldArticleDAO;
import fr.eni.projetEncheres.model.dal.SoldArticleDAOImpl;

public class ArticleManager {

//	private static ArticleManager instanceOfArticleManager = null;
	private DAO<SoldArticle> soldArticleDAO;
	
	//private SoldArticleDAOImpl soldArticleDAOImpl = (SoldArticleDAOImpl) DAOFactory.getArticleDAO();

	//public ArticleManager(SoldArticleDAOImpl aSoldArticleDAOImpl) {
//		this.soldArticleDAOImpl = aSoldArticleDAOImpl;
//	}

	public ArticleManager() {
		this.soldArticleDAO = DAOFactory.getArticleDAO();
	}

//	public static ArticleManager getInstance() {
//		if (instanceOfArticleManager == null) {
//			instanceOfArticleManager = new ArticleManager((SoldArticleDAOImpl) DAOFactory.getArticleDAO());
//		}
//		return instanceOfArticleManager;
//	}

	public void insert(SoldArticle article) throws BLLException {
		try {
			soldArticleDAO.insert(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Insert failed - ", e);
		}
	}

	public void update(SoldArticle article) throws BLLException {
		try {
			soldArticleDAO.update(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Update failed - ", e);
		}
	}

	public SoldArticle selectById(int articleId) throws BLLException {
		try {
			return soldArticleDAO.selectById(articleId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select by Id failed - ", e);
		}
	}

	public List<SoldArticle> selectAll() throws BLLException {
		try {
			return soldArticleDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select All failed - ", e);
		}
	}

	public void delete(int articleId) throws BLLException {
		try {
			soldArticleDAO.delete(articleId);
		} catch (DALException e) {
			throw new BLLException("Delete failed - ", e);
		}
	}

	public List<SoldArticle> selectByDescription(String motCle) throws BLLException {
		try {
			return ((SoldArticleDAO) soldArticleDAO).selectByDescription(motCle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select by Description failed - ", e);
		}
	}
	
	public List<SoldArticle> selectByCategoryByState(int category, int state) throws BLLException {
		try {
			return ((SoldArticleDAO) soldArticleDAO).selectByCategoryByState(category, state);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select by Category by State failed - ", e);
		}
	}

	public void updateAuction(SoldArticle article) throws BLLException {
		try {
			((SoldArticleDAO) soldArticleDAO).updateAuction(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Update Auction failed - ", e);
		}		
	}

	public void updateBuyer(SoldArticle article) throws BLLException {
		try {
			((SoldArticleDAO) soldArticleDAO).updateBuyer(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Update Buyer failed - ", e);
		}
	}

	public void updateSoldPrice(SoldArticle article) throws BLLException {
		try {
			((SoldArticleDAO) soldArticleDAO).updateSoldPrice(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Update Sold Price failed - ", e);
		}
	}
	
	public SoldArticle newSell(SoldArticle article) throws BLLException{
		return article;
	}
	
	public static void validatedDate(SoldArticle article) throws BLLException{
		if (article.getAuctionStart() == null || article.getAuctionEnd() == null || article.getAuctionStart().isBefore(LocalDate.now()) ||
				article.getAuctionEnd().isBefore(article.getAuctionStart())) {
			
		}
	
	}
	
}

package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.SoldArticleDAO;
import fr.eni.projetEncheres.model.dal.SoldArticleDAOImpl;

public class ArticleManager implements SoldArticleDAO {

	private static ArticleManager instanceOfArticleManager = null;
	private SoldArticleDAOImpl soldArticleDAOImpl = (SoldArticleDAOImpl) DAOFactory.getArticleDAO();

	public ArticleManager(SoldArticleDAOImpl aSoldArticleDAOImpl) {
		this.soldArticleDAOImpl = aSoldArticleDAOImpl;
	}

	public static ArticleManager getInstance() {
		if (instanceOfArticleManager == null) {
			instanceOfArticleManager = new ArticleManager((SoldArticleDAOImpl) DAOFactory.getArticleDAO());
		}
		return instanceOfArticleManager;
	}

	public void insert(SoldArticle article) throws DALException {
		soldArticleDAOImpl.insert(article);
	}

	public void update(SoldArticle article) throws DALException {
		soldArticleDAOImpl.update(article);
	}

	public SoldArticle selectById(int articleId) throws DALException {
		return soldArticleDAOImpl.selectById(articleId);
	}

	public List<SoldArticle> selectAll() throws DALException {
		return soldArticleDAOImpl.selectAll();
	}

	public void delete(int articleId) throws DALException {
		soldArticleDAOImpl.delete(articleId);
	}

	public List<SoldArticle> selectByDescription(String motCle) throws DALException {
		return soldArticleDAOImpl.selectByDescription(motCle);
	}
	
	public List<SoldArticle> selectByCategoryByState(int category, int state) throws DALException {
		return soldArticleDAOImpl.selectByCategoryByState(category, state);
	}

	public void updateAuction(SoldArticle article) throws DALException {
		soldArticleDAOImpl.updateAuction(article);		
	}

	public void updateBuyer(SoldArticle article) throws DALException {
		soldArticleDAOImpl.updateBuyer(article);
	}

	public void updateSoldPrice(SoldArticle article) throws DALException {
		soldArticleDAOImpl.updateSoldPrice(article);
	}
	
}

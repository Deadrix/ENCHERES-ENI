package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.SoldArticleDAO;
import fr.eni.projetEncheres.model.dal.SoldArticleDAOImpl;

public class ArticleManager implements SoldArticleDAO {

	private static ArticleManager instanceArticleManager = null;
	private SoldArticleDAOImpl soldArticleDAOImpl = (SoldArticleDAOImpl) DAOFactory.getArticleDAO();

	public ArticleManager(SoldArticleDAOImpl aSoldArticleDAOImpl) {
		this.soldArticleDAOImpl = aSoldArticleDAOImpl;
	}

	public static ArticleManager getInstance() {
		if (instanceArticleManager == null) {
			instanceArticleManager = new ArticleManager((SoldArticleDAOImpl) DAOFactory.getArticleDAO());
		}
		return instanceArticleManager;
	}

	public void insert(SoldArticle article) throws DALException {
		soldArticleDAOImpl.insert(article);
	}

	public void update(SoldArticle article) throws DALException {
		soldArticleDAOImpl.update(article);
	}

	public SoldArticle selectById(int idArticle) throws DALException {
		return soldArticleDAOImpl.selectById(idArticle);
	}

	public List<SoldArticle> selectAll() throws DALException {
		return soldArticleDAOImpl.selectAll();
	}

	public List<SoldArticle> selectByMotCle(String motCle) throws DALException {
		return soldArticleDAOImpl.selectByMotCle(motCle);
	}

	public void delete(int idArticle) throws DALException {
		soldArticleDAOImpl.delete(idArticle);
	}

	public List<SoldArticle> selectByCategoryByState(String motCle, int categorie) throws DALException {
		return soldArticleDAOImpl.selectByCategoryByState(motCle, categorie);
	}

	@Override
	public List<SoldArticle> SelectByCategoryByState(String motCle, int categorie) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAuction(int articleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBuyer(int articleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSoldPrice(int articleId) {
		// TODO Auto-generated method stub
		
	}
}

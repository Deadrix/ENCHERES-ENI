package fr.eni.projetEncheres.model.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.SoldArticleDAO;
import fr.eni.projetEncheres.model.dal.SoldArticleDAOImpl;

public class ArticleManager {

		private SoldArticleDAOImpl soldArticleDAO;
		
		public ArticleManager() {
			this.soldArticleDAO = (SoldArticleDAOImpl) DAOFactory.getArticleDAO();
		}
	
		
		public void insert(SoldArticle object) throws BLLException {
			try {
				this.soldArticleDAO.insert(object);
			}catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Sold Article adding failed - ", e);
			}
		}
		
		public void update(SoldArticle object) {
			SoldArticleDAO.update(object);
		}
		
		public SoldArticle selectById(int idObject) throws DALException {
			return soldArticleDAO.selectById(idObject);
		}
		
		public List<SoldArticle> selectAll() throws DALException{
			return (ArrayList<SoldArticle>)soldArticleDAO.selectAll();
		}
		
		public void delete(int idObject) {
			SoldArticleDAO.delete(idObject);
		}
}	
				
		
		
	



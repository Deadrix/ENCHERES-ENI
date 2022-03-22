package fr.eni.projetEncheres.model.dal;

import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.bo.Category;




public class DAOFactory {
	
	public static DAO<SoldArticle> getArticleDAO() {
		return new SoldArticleDAOImpl();
	}
	
	public static DAO<User> getUserDAO() {
		return new UserDAOImpl();
	}

	public static DAO<Category> getCategoryDAO() {
		return new CategoryDAOImpl();
	}



	
	 /*
	  * public static ArticleDAO getArticleDAO()  {
		ArticleDAO articleDAO=null;
		try {
			articleDAO=(ArticleDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articleDAO; 
	}
	  */
}

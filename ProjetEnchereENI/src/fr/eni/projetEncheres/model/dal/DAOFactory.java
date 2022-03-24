package fr.eni.projetEncheres.model.dal;

import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.bo.PickUp;
import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.bo.User;




public class DAOFactory {
	
	private static DAOFactory  instanceOfDaoFactory;
	
	public static DAO<SoldArticle> getArticleDAO() {
		return new SoldArticleDAOImpl();
	}
	
//	public static DAO<User> getUserDAO() {
//		return new UserDAOImpl();
//	}

	public static DAO<Category> getCategoryDAO() {
		return new CategoryDAOImpl();
	}
	
	public static DAO<PickUp> getPickUpDAO() {
		return new PickUpDAOImpl();
	}

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
//	public static DAOFactory getInstanceOfDaoFactory() {
//		if (instanceOfDaoFactory == null) {
//			instanceOfDaoFactory = new DAOFactory();
//		}
//		return instanceOfDaoFactory;
//	}

	
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

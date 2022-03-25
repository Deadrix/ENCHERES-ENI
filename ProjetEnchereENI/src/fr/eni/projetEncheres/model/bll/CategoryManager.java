package fr.eni.projetEncheres.model.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.CategoryDAOImpl;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAO;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class CategoryManager {

	
	private DAO<Category> categoryDAO;
	private static CategoryManager instance;
	private CategoryDAOImpl categoryDaAOImpl = (CategoryDAOImpl) DAOFactory.getCategoryDAO();
	
	
	public CategoryManager() {
		this.categoryDAO = DAOFactory.getCategoryDAO();
	}

	public List<Category> selectCategory(){
		List<Category> lstCategory = new ArrayList<Category>();

		return lstCategory;
		
	}

/*	public static CategoryManager getInstance() {
		if(instance ==null) {
			instance = new CategoryManager();
		}
		return instance;
	}

	public Category selectById(int no_categorie) throws DALException {
		return categoryDaAOImpl.selectById(no_categorie);
	}
*/
	

}

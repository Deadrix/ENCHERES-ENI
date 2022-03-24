package fr.eni.projetEncheres.model.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.dal.DAO;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class CategoryManager {

	
	private DAO<Category> categoryDAO;
	
	public CategoryManager() {
		this.categoryDAO = DAOFactory.getCategoryDAO();
	}

	public List<Category> selectCategory(){
		List<Category> lstCategory = new ArrayList<Category>();

		return lstCategory;
		
	}
	
	

}

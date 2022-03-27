package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.dal.CategoryDAO;
import fr.eni.projetEncheres.model.dal.CategoryDAOImpl;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class CategoryManager implements CategoryDAO {

	private static CategoryManager instanceOfCategoryManager = null;
	private CategoryDAOImpl categoryDAOImpl = (CategoryDAOImpl) DAOFactory.getCategoryDAO();

	public CategoryManager(CategoryDAOImpl aCategoryDAOImpl) {
		this.categoryDAOImpl = aCategoryDAOImpl;
	}

	public static CategoryManager getInstance() {
		if (instanceOfCategoryManager == null) {
			instanceOfCategoryManager = new CategoryManager((CategoryDAOImpl) DAOFactory.getCategoryDAO());
		}
		return instanceOfCategoryManager;
	}
	
	public void insert(Category category) throws DALException {
		categoryDAOImpl.insert(category);
	}
	
	public void update(Category category) throws DALException {
		categoryDAOImpl.update(category);
	}
	
	public Category selectById(int categoryId) throws DALException {
		return categoryDAOImpl.selectById(categoryId);
	}
	
	public List<Category> selectAll() throws DALException {
		return categoryDAOImpl.selectAll();
	}

	public void delete(int categoryId) throws DALException {
		categoryDAOImpl.delete(categoryId);
	}

}

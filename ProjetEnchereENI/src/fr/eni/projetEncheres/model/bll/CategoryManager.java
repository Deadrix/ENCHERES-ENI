package fr.eni.projetEncheres.model.bll;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;
import fr.eni.projetEncheres.model.dal.CategoryDAO;
import fr.eni.projetEncheres.model.dal.CategoryDAOImpl;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAO;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class CategoryManager  {

	private DAO<Category> categoryDAO;
	
	private static CategoryManager instanceOfCategoryManager = null;
	private CategoryDAOImpl categoryDAOImpl = (CategoryDAOImpl) DAOFactory.getCategoryDAO();

	public CategoryManager() {
		this.categoryDAO = DAOFactory.getCategoryDAO();
	}
	
	public CategoryManager(CategoryDAOImpl aCategoryDAOImpl) {
		this.categoryDAOImpl = aCategoryDAOImpl;
	}

//	public static CategoryManager getInstance() {
//		if (instanceOfCategoryManager == null) {
//			instanceOfCategoryManager = new CategoryManager((CategoryDAOImpl) DAOFactory.getCategoryDAO());
//		}
//		return instanceOfCategoryManager;
//	}
	
	public void insert(Category category) throws BLLException {
		try {
			categoryDAOImpl.insert(category);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Category category) throws BLLException {
		try {
			categoryDAOImpl.update(category);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Update failed - ", e);
		}
	}
	
	public Category selectById(int categoryId) throws BLLException {
		try {
			return categoryDAOImpl.selectById(categoryId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select by Id failed - ", e);
		}
	}
	
	public List<Category> selectAll() throws BLLException {
		try {
			return categoryDAOImpl.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Select All failed - ", e);
		}
	}

	public void delete(int categoryId) throws BLLException {
		try {
			categoryDAOImpl.delete(categoryId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Delete failed - ", e);
		}
	}

}

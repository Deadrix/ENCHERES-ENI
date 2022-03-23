package fr.eni.projetEncheres.model.dal;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;

public class CategoryDAOImpl implements DAO<Category> {
	
	private final String SQLINSERT="INSERT INTO Categories (libelle) VALUES (?)";
	private final String SQLUPDATE="UPDATE Categories set libelle=? where id=?";
	private final String SQLDELETEBYID="DELETE FROM Categories WHERE id=? ";
	private final String SQLSELECTALL="SELECT libelle FROM Categories";
	private final String SQLSELECTBYID="SELECT libelle FROM Categories where id=?";
	private static CategoryDAOImpl instance = null;

	@Override
	public void insert(Category object) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category object) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category selectById(int idObject) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> selectByMotCle(String motCle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idObject) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
}

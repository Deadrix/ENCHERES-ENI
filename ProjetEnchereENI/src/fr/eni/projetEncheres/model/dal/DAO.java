package fr.eni.projetEncheres.model.dal;

import java.util.List;

public interface DAO<T> {
	
	public void insert(T object) throws DALException;
	
	public void update(T object) throws DALException;
	
	public T selectById(int idObject) throws DALException;
	
	public List<T> selectAll() throws DALException;
	
	public List<T> selectByMotCle(String motCle) throws DALException;
	
	public void delete(int idObject) throws DALException;
	

}

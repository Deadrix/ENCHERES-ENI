package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;

public class CategoryDAOImpl implements DAO<Category> {
	
	private final String SQLINSERT="INSERT INTO Categories (libelle) VALUES (?)";
	private final String SQLUPDATE="UPDATE Categories set libelle=? where id=?";
	private final String SQLDELETEBYID="DELETE FROM Categories WHERE id=? ";
	private final String SQLSELECTALL="SELECT SELECT * FROM CATEGORIES";
	private final String SQLSELECTBYID="SELECT libelle FROM Categories where id=?";
	private static CategoryDAOImpl instance = null;

	

	@Override
	public List<Category> selectAll() throws DALException {
		Connection con = null;
		java.sql.Statement stmt = null;
		List<Category> lstCategory = new ArrayList<Category>();
		ResultSet rs;
		Category category;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQLSELECTALL);
			while(rs.next()) 
			{
				category = new Category();
				category.setLibelle(rs.getString("libelle"));
				category.setCategoryId(rs.getInt("no-categories"));
				lstCategory.add(category);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : All Category selection from database failed - ", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return lstCategory;
			
}



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
	public List<Category> selectByMotCle(String motCle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(int idObject) throws DALException {
		// TODO Auto-generated method stub
		
	}
		

	
}

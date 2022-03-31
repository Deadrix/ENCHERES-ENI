package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;

public class CategoryDAOImpl implements CategoryDAO {

	private final String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
	private final String UPDATE = "UPDATE Categories set libelle=? where no_categorie=?";
	private final String SELECTBYID = "SELECT no_categorie, libelle FROM Categories where no_categorie=?";
	private final String SELECTALL = "SELECT no_categorie, libelle FROM CATEGORIES";
	private final String DELETEBYID = "DELETE FROM Categories WHERE no_categorie=? ";

	public void insert(Category category) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, category.getLibelle());

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				category.setCategoryId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Insert Category into database failed - ", e);
		}

	}

	public void update(Category category) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(UPDATE)) {
			ps.setString(1, category.getLibelle());
			ps.setInt(2, category.getCategoryId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Update Category into database failed - ", e);
		}
	}

	public Category selectById(int categoryId) throws DALException {

		Category cat = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYID)) {

			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				cat = new Category();
				cat.setCategoryId(rs.getInt("no_categorie"));
				cat.setLibelle(rs.getString("libelle"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Select Category by Id from database failed - ", e);
		}

		return cat;

	}

	public List<Category> selectAll() throws DALException {

		List<Category> lst;
		lst = new ArrayList<>();
		Category cat = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTALL)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cat = new Category();
				cat.setCategoryId(rs.getInt("no_categorie"));
				cat.setLibelle(rs.getString("libelle"));
				lst.add(cat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Select All Categorys from database failed - ", e);
		}
		return lst;

	}

	public void delete(int categoryId) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(DELETEBYID)) {

			ps.setInt(1, categoryId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Delete Category from database failed - ", e);
		}
	}

}

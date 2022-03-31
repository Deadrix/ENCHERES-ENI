package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.PickUp;
import fr.eni.projetEncheres.model.bo.SoldArticle;

public class PickUpDAOImpl implements DAO<PickUp> {

//	no_article         INTEGER NOT NULL,
//    rue              VARCHAR(30) NOT NULL,
//    code_postal      VARCHAR(15) NOT NULL,
//    ville            VARCHAR(30) NOT NULL

	private static final String INSERT = "INSERT INTO RETRAITS(rue, code_postal, ville) VALUES (?, ?, ?);";
	private static final String UPDATE = "UPDATE RETRAITS SET " + "rue = ?, " + "code_postal = ?, " + "ville = ? "
			+ "WHERE no_article = ?;";
	private static final String DELETE = "DELETE FROM RETRAITS WHERE no_article = ?;";
	private static final String SELECTBYID = "SELECT * FROM RETRAITS WHERE no_article = ?;";
	private static final String SELECTALL = "SELECT * FROM RETRAIT";
	private static final String SELECTBYKEYWORD = "SELECT rue, code_postal, ville"
			+ " FROM RETRAITS WHERE rue like ? or ville like ? or code_postal like ?";

	public void insert(PickUp object) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(INSERT)) {
			ps.setString(1, object.getRueRetrait());
			ps.setString(2, object.getCodePostalRetrait());
			ps.setString(3, object.getVilleRetrait());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Insert Pick-Up into database failed - ", e);
		}
	}

	public void update(PickUp object) throws DALException {
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(UPDATE)) {

			ps.setString(1, object.getRueRetrait());
			ps.setString(2, object.getCodePostalRetrait());
			ps.setString(3, object.getVilleRetrait());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Update Pick-Up into database failed", e);
		}
	}

	public PickUp selectById(int idSoldArticle) throws DALException {

		PickUp pu = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYID)) {
			ps.setInt(1, idSoldArticle);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pu = new PickUp(rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Select Pick-Up by Id from database failed - ", e);
		}
		return pu;
	}

	public List<PickUp> selectAll() throws DALException {

		List<PickUp> lst = new ArrayList<>();
		lst = new ArrayList<>();
		PickUp pu = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTALL)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pu = new PickUp(rs.getString(2), rs.getString(3), rs.getString(4));
				lst.add(pu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(
					"DATA ACCESS LAYER EXCEPTION : Select All Pick-Ups selection from database failed - ", e);
		}
		return lst;
	}

	public List<PickUp> selectByMotCle(String motCle) throws DALException {
		
		List<PickUp> lst = new ArrayList<>();
		lst = new ArrayList<>();
		PickUp pu = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYKEYWORD)) {
			
			ps.setString(1, motCle);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pu = new PickUp(rs.getString(2), rs.getString(3), rs.getString(4));

				lst.add(pu);
			}
			
		} catch (SQLException e) {
			throw new DALException("selectByMotCle failed - ", e);
		}
		return lst;
	}

	public void delete(int idArticleVendu) throws DALException {
		Connection connect = null;
		PreparedStatement ps = null;
		try {
			connect = ConnectionProvider.getConnection();
			ps = connect.prepareStatement(DELETE);
			ps.setInt(1, idArticleVendu);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Delete Pick-up from database failed - ", e);
		}
	}

}

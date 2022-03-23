package fr.eni.projetEncheres.model.dal;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bo.PickUp;

public class PickUpDAOImpl implements DAO<PickUp> {
	
//	no_article         INTEGER NOT NULL,
//    rue              VARCHAR(30) NOT NULL,
//    code_postal      VARCHAR(15) NOT NULL,
//    ville            VARCHAR(30) NOT NULL
	

	private static final String INSERT ="INSERT INTO RETRAITS(rue, code_postal, ville) VALUES (?, ?, ?);";
	private static final String UPDATE ="UPDATE RETRAITS SET "
			+ "rue = ?, "
			+ "code_postal = ?, "
			+ "ville = ? "
			+ "WHERE no_article = ?;";
	private static final String DELETE ="DELETE FROM PICKUPS WHERE no_article = ?;";
	private static final String SELECT_ALL ="SELECT * FROM PICKUPS";
	private static final String SELECT_BY_ID ="SELECT * FROM PICKUPS WHERE no_article = ?;";
	

	@Override
	public void insert(PickUp object) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, object.getRueRetrait());
			pstmt.setString(2, object.getCodePostalRetrait());
			pstmt.setString(3, object.getVilleRetrait());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up insertion into database failed - ", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
	}

	@Override
	public void update(PickUp object) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, object.getRueRetrait());
			pstmt.setString(2, object.getCodePostalRetrait());
			pstmt.setString(3, object.getVilleRetrait());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up update in database failed - ", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}		
	}

	@Override
	public PickUp selectById(int idSoldArticle) throws DALException {
		PickUp pu = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, idSoldArticle);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pu = new PickUp(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up selection by id from database failed - ", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return pu;
	}

	@Override
	public List<PickUp> selectAll() throws DALException {
		List<PickUp> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				PickUp pu = new PickUp(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
				list.add(pu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : All pick-ups selection from database failed - ", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public List<PickUp> selectByMotCle(String motCle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idArticleVendu) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, idArticleVendu);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up deletion from database failed - ", e);
		}
		
	}
	
	

}

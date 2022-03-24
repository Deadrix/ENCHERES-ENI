package fr.eni.projetEncheres.model.dal;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.SoldArticle;

public class SoldArticleDAOImpl implements DAO<SoldArticle> {
	
	private static SoldArticleDAOImpl instance = null;

	private final String SQLINSERT="INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_acheteur,no_categories) VALUES(?,?,?,?,?,?,?,?,?)";
	private final String SQLUPDATE="UPDATE ARTICLES_VENDUS set nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_vendeur=?, no_acheteur=?, no_categorie=? WHERE id=?";
	private final String SQLDELETEBYID="DELETE FROM ARTICLES_VENDUS WHERE id=?";
	private final String SQLSELECTBYID="SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS where id=?";
	private final String SQLSELECTALL="SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS";
	
	
	@Override
	public void insert(SoldArticle object) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=ConnectionProvider.getConnection();
			pstmt=con.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, object.getArticleName());
			pstmt.setString(2, object.getDescription());
			pstmt.setDate(3, (Date) object.getAuctionStart());
			pstmt.setDate(4, (Date) object.getAuctionEnd());	
			if(object.getAuctionStart()==null) {
				pstmt.setNull(5, Type.INT);
			}
			else {
				pstmt.setInt(5, object.getInitialPrice());
			}
			if(object.getAuctionStart()==null) {
				pstmt.setNull(6, Type.INT);
			}
			else {
				pstmt.setInt(6, object.getSoldPrice());
			}
			
			pstmt.setInt(7, object.getSeller().getUserId());
			pstmt.setInt(8, object.getBuyer().getUserId());
			pstmt.setInt(9, object.getCategory().getCategoryId());
			
			pstmt.executeUpdate();
			con.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : SoldArticle insertion into database failed - ", e);
		}
		
		finally {
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
	public void update(SoldArticle object) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=ConnectionProvider.getConnection();
			pstmt=con.prepareStatement(SQLUPDATE);
			pstmt.setString(1, object.getArticleName());
			pstmt.setString(2, object.getDescription());
			pstmt.setDate(3, (Date) object.getAuctionStart());
			pstmt.setDate(4, (Date) object.getAuctionEnd());	
			if(object.getAuctionStart()==null) {
				pstmt.setNull(5, Type.INT);
			}
			else {
				pstmt.setInt(5, object.getInitialPrice());
			}
			if(object.getAuctionStart()==null) {
				pstmt.setNull(6, Type.INT);
			}
			else {
				pstmt.setInt(6, object.getSoldPrice());
			}
			
			pstmt.setObject(7, object.getBuyer());
			pstmt.setObject(8, object.getCategory());
			
			pstmt.executeUpdate();
			con.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : SoldArticle insertion into database failed - ", e);
		}
		
		finally {
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
	public SoldArticle selectById(int idObject) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		SoldArticle art = null;
		ResultSet rs;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SQLDELETEBYID);
			pstmt.setInt(1, idObject);
			rs = pstmt.executeQuery(SQLSELECTBYID);
			if(rs.next()) 
			{
				art = new SoldArticle(rs.getInt("no_article"),
									  rs.getString("nom_article"),
									  rs.getString("description"),
									  rs.getDate("date_debut_encheres"),
									  rs.getDate("date_fin_encheres"),
									  rs.getInt("prix_initial"),
									  rs.getInt("prix_vente"),									  
									  UserManager.getInstance().selectById(rs.getInt("no_vendeur")),
									  UserManager.getInstance().selectById(rs.getInt("no_acheteur")),
									  CategoryManager.getInstance().selectById(rs.getInt("no_categorie"));
				return art;  		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : SoldArticle selectionById from database failed - ", e);
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
		return art;
		}
		
	

	@Override
	public List<SoldArticle> selectAll() throws DALException {
		Connection con = null;
		java.sql.Statement stmt = null;
		List<SoldArticle> lst;
		lst = new ArrayList<>();
		ResultSet rs;
		SoldArticle soldArticle;
				
			try {
				con = ConnectionProvider.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(SQLSELECTALL);
				while(rs.next()) 
				{
					soldArticle = new SoldArticle(rs.getInt("no_article"),
												  rs.getString("nom_article"),
												  rs.getString("description"),
												  rs.getDate("date_debut_encheres"),
												  rs.getDate("date_fin_encheres"),
												  rs.getInt("prix_initial"),
												  rs.getInt("prix_vente"),									  
												  UserManager.getInstance().selectById(rs.getInt("no_vendeur")),
												  UserManager.getInstance().selectById(rs.getInt("no_acheteur")),
												  CategoryManager.getInstance().selectById(rs.getInt("no_categorie"));
					lst.add(soldArticle);
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("DATA ACCESS LAYER EXCEPTION : All SoldArticle selection from database failed - ", e);
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
			return lst;
			}
		
	

	@Override
	public List<SoldArticle> selectByMotCle(String motCle) throws DALException {
		return null;
	}

	@Override
	public void delete(int idObject) throws DALException {
		Connection con;
		PreparedStatement pstmt;
		try {
			con=ConnectionProvider.getConnection();
			pstmt=con.prepareStatement(SQLDELETEBYID);
			pstmt.setInt(1, idObject);
						
			pstmt.executeUpdate();
			con.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : SoldArticle deletion into database failed - ", e);
		}
		
	
		
	}

}

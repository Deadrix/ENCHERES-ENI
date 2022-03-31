package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bll.AuctionManager;
import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.CategoryManager;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.SoldArticle;

public class SoldArticleDAOImpl implements SoldArticleDAO {

	private final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_vendeur,no_categorie, etat_vente) VALUES(?,?,?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_categorie=? WHERE no_article=?";
	
	private final String SELECTBYID = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_acheteur,no_categorie, etat_vente FROM ARTICLES_VENDUS where no_article=?";
	
	private final String SELECTALL = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_acheteur,no_categorie, etat_vente FROM ARTICLES_VENDUS";
	private final String DELETEBYID = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String SELECTBYDESCRIPTION = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_acheteur,no_categorie, etat_vente FROM ARTICLES_VENDUS where description like=%?%";
	private final String SELECTBYCATEGORYBYSTATE = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_acheteur,no_categorie, etat_vente FROM ARTICLES_VENDUS where no_categorie=? AND state=?";
	private final String AUCTIONUPDATE = "UPDATE ARTICLES_VENDUS SET enchere_courante=? WHERE no_article=?";
	private final String BUYERNUPDATE = "UPDATE ARTICLES_VENDUS SET no_acheteur=? WHERE no_article=?";
	private final String SOLDPRICEUPDATE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";

	private static AuctionManager auctionMng = new AuctionManager();
	private static CategoryManager categoryMng = new CategoryManager();
		
	public void insert(SoldArticle article) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, article.getArticleName());
			ps.setString(2, article.getDescription());
			ps.setDate(3, Date.valueOf(article.getAuctionStart()));
			ps.setDate(4, Date.valueOf(article.getAuctionEnd()));
			ps.setInt(5, article.getInitialPrice());
			ps.setInt(6, article.getSeller().getUserId());
			ps.setInt(7, article.getCategory().getCategoryId());
			ps.setInt(8, article.getState());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				article.setArticleId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Insert Article into database failed - ", e);
		}

	}

	public void update(SoldArticle article) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(UPDATE)) {
			ps.setString(1, article.getArticleName());
			ps.setString(2, article.getDescription());
			ps.setDate(3, Date.valueOf(article.getAuctionStart()));
			ps.setDate(4, Date.valueOf(article.getAuctionEnd()));
			ps.setInt(5, article.getInitialPrice());
			ps.setInt(6, article.getSoldPrice());
			ps.setInt(7, article.getSeller().getUserId());
			ps.setInt(8, article.getCategory().getCategoryId());
			ps.setInt(9, article.getArticleId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Global Article update into database failed", e);
		}
	}

	public SoldArticle selectById(int articleId) throws DALException {

		SoldArticle art = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYID)) {
			ps.setInt(1, articleId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				art = new SoldArticle();
				art.setArticleId(rs.getInt("no_article"));
				art.setArticleName(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setAuctionStart(rs.getDate("date_debut_encheres").toLocalDate());
				art.setAuctionEnd(rs.getDate("date_fin_encheres").toLocalDate());
				art.setInitialPrice(rs.getInt("prix_initial"));
				art.setSoldPrice(rs.getInt("prix_vente"));
				art.setSeller(UserManager.getInstance().selectById(rs.getInt("no_vendeur")));
				art.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_acheteur")));
				art.setCategory(categoryMng.selectById(rs.getInt("no_categorie")));
				art.setState(rs.getInt("etat_vente"));
				art.setAuction(auctionMng.selectBestAuctionFromArticle(rs.getInt("no_article")));
			}
			
		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Select Article by Id from database failed - ", e);
		}
		return art;
	}
	

	
	public List<SoldArticle> selectAll() throws DALException {

		List<SoldArticle> lst;
		lst = new ArrayList<>();
		SoldArticle art = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTALL)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				art = new SoldArticle();
				art.setArticleId(rs.getInt("no_article"));
				art.setArticleName(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setAuctionStart(rs.getDate("date_debut_encheres").toLocalDate());
				art.setAuctionEnd(rs.getDate("date_fin_encheres").toLocalDate());
				art.setInitialPrice(rs.getInt("prix_initial"));
				art.setSoldPrice(rs.getInt("prix_vente"));
				art.setSeller(UserManager.getInstance().selectById(rs.getInt("no_vendeur")));
				art.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_acheteur")));
				art.setCategory(categoryMng.selectById(rs.getInt("no_categorie")));
				art.setState(rs.getInt("etat_vente"));
				art.setAuction(auctionMng.selectBestAuctionFromArticle(rs.getInt("no_article")));
				lst.add(art);
			}

		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Select All Articles from database failed - ", e);
		}
		return lst;
	}

	public void delete(int articleId) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(DELETEBYID)) {
			ps.setInt(1, articleId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Delete Article from database failed - ", e);
		}
	}

	public List<SoldArticle> selectByDescription(String motCle) throws DALException {

		List<SoldArticle> lst;
		lst = new ArrayList<>();
		SoldArticle art = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYDESCRIPTION)) {
			ps.setString(1, motCle);
			ResultSet rs = ps.executeQuery(SELECTBYDESCRIPTION);
			while (rs.next()) {
				art = new SoldArticle();
				art.setArticleId(rs.getInt("no_article"));
				art.setArticleName(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setAuctionStart(rs.getDate("date_debut_encheres").toLocalDate());
				art.setAuctionEnd(rs.getDate("date_fin_encheres").toLocalDate());
				art.setInitialPrice(rs.getInt("prix_initial"));
				art.setSoldPrice(rs.getInt("prix_vente"));
				art.setSeller(UserManager.getInstance().selectById(rs.getInt("no_vendeur")));
				art.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_acheteur")));
				art.setCategory(categoryMng.selectById(rs.getInt("no_categorie")));
				art.setState(rs.getInt("etat_vente"));
				art.setAuction(auctionMng.selectBestAuctionFromArticle(rs.getInt("no_article")));
				lst.add(art);
			}

		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException(
					"DATA ACCESS LAYER EXCEPTION : Select Articles by Description from database failed - ", e);
		}
		return lst;
	}

	public List<SoldArticle> selectByCategoryByState(int categorie, int state) throws DALException {

		List<SoldArticle> lst;
		lst = new ArrayList<>();
		SoldArticle art = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYCATEGORYBYSTATE)) {
			ps.setInt(1, categorie);
			ps.setInt(2, state);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				art = new SoldArticle();
				art.setArticleId(rs.getInt("no_article"));
				art.setArticleName(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setAuctionStart(rs.getDate("date_debut_encheres").toLocalDate());
				art.setAuctionEnd(rs.getDate("date_fin_encheres").toLocalDate());
				art.setInitialPrice(rs.getInt("prix_initial"));
				art.setSoldPrice(rs.getInt("prix_vente"));
				art.setSeller(UserManager.getInstance().selectById(rs.getInt("no_vendeur")));
				art.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_acheteur")));
				art.setCategory(categoryMng.selectById(rs.getInt("no_categorie")));
				art.setState(rs.getInt("etat_vente"));
				art.setAuction(auctionMng.selectBestAuctionFromArticle(rs.getInt("no_article")));
				lst.add(art);
			}

		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException(
					"DATA ACCESS LAYER EXCEPTION : Select Articles by Category and State from database failed - ", e);
		}
		return lst;
	}

	public void updateAuction(SoldArticle article) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(AUCTIONUPDATE)) {
			ps.setInt(1, auctionMng.selectBestAuctionFromArticle(article).getItemPrice());
			ps.setInt(2, article.getArticleId());

			ps.executeUpdate();

		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Update Article Auction from database failed - ", e);
		}

	}

	public void updateBuyer(SoldArticle article) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(BUYERNUPDATE)) {
			ps.setInt(1, auctionMng.selectBestAuctionFromArticle(article).getBuyer().getUserId());
			ps.setInt(2, article.getArticleId());

			ps.executeUpdate();

		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Update Article Buyer from database failed - ", e);
		}

	}

	public void updateSoldPrice(SoldArticle article) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SOLDPRICEUPDATE)) {
			ps.setInt(1, auctionMng.selectBestAuctionFromArticle(article).getItemPrice());
			ps.setInt(2, article.getArticleId());

			ps.executeUpdate();

		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException(
					"DATA ACCESS LAYER EXCEPTION : Update Article SoldPrice update from database failed - ", e);
		}
	}

}

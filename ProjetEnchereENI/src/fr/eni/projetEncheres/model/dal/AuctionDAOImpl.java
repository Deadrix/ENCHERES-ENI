package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bll.ArticleManager;
import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.Auction;
import fr.eni.projetEncheres.model.bo.SoldArticle;

public class AuctionDAOImpl implements AuctionDAO {

	private final String INSERT = "INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere) VALUES(?,?,?,?)";
	private final String UPDATE = "UPDATE ENCHERES set no_utilisateur=?,no_article=?, date_enchere=?, montant_enchere=? WHERE id=?";
	private final String DELETE = "DELETE FROM ENCHERES WHERE id=?";
	private final String SELECTALL = "SELECT no_utilisateur,no_article,date_enchere,montant_enchere FROM ENCHERES";
	private final String SELECTBESTAUCTIONFROMARTICLE = "SELECT no_utilisateur,no_article,date_enchere,MAX(montant_enchere) WHERE no_article=?";

	private static ArticleManager articlMng = new ArticleManager();
	
	public void insert(Auction auction) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(INSERT)) {
			ps.setInt(1, auction.getBuyer().getUserId());
			ps.setInt(2, auction.getAuctionedArticle().getArticleId());
			ps.setDate(3, (Date) auction.getAuctionDate());
			ps.setInt(4, auction.getItemPrice());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Insert Auction into database failed - ", e);
		}
	}

	public void update(Auction auction) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(UPDATE)) {
			ps.setInt(1, auction.getBuyer().getUserId());
			ps.setInt(2, auction.getAuctionedArticle().getArticleId());
			ps.setDate(3, (Date) auction.getAuctionDate());
			ps.setInt(4, auction.getItemPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Update Auction into database failed - ", e);
		}
	}

	public Auction selectById(int idAuction) throws DALException {
		return null;
	}

	public List<Auction> selectAll() throws DALException {

		List<Auction> lst;
		lst = new ArrayList<>();
		Auction auction = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTALL)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				auction = new Auction();
				auction.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_utilisateur")));
				auction.setAuctionedArticle(articlMng.selectById(rs.getInt("no_article")));
				auction.setAuctionDate(rs.getDate("date_enchere"));
				auction.setItemPrice(rs.getInt("montant_enchere"));
				lst.add(auction);
			}
		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Select All Auction from database failed - ", e);
		}
		return lst;
	}

	public Auction selectBestAuctionFromArticle(SoldArticle article) throws DALException {

		Auction auction = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBESTAUCTIONFROMARTICLE)) {
			ps.setInt(1, article.getArticleId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				auction = new Auction();
				auction.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_utilisateur")));
				auction.setAuctionedArticle(articlMng.selectById(rs.getInt("no_article")));
				auction.setAuctionDate(rs.getDate("date_enchere"));
				auction.setItemPrice(rs.getInt("montant_enchere"));
			}
		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException(
					"DATA ACCESS LAYER EXCEPTION : Select the Best Auction of an Article from database failed - ", e);
		}

		return auction;
	}
	
	public Auction selectBestAuctionFromArticle(int articleId) throws DALException {

		Auction auction = null;

		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBESTAUCTIONFROMARTICLE)) {
			ps.setInt(1, articleId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				auction = new Auction();
				auction.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_utilisateur")));
				auction.setAuctionedArticle(articlMng.selectById(rs.getInt("no_article")));
				auction.setAuctionDate(rs.getDate("date_enchere"));
				auction.setItemPrice(rs.getInt("montant_enchere"));
			}
		} catch (SQLException | BLLException e) {
			e.printStackTrace();
			throw new DALException(
					"DATA ACCESS LAYER EXCEPTION : Select the Best Auction of an Article from database failed - ", e);
		}

		return auction;
	}


	public void delete(int idObject) throws DALException {
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(DELETE)) {
			ps.setInt(1, idObject);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Delete Auction into database failed - ", e);
		}
	}
}

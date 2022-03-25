package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.model.bll.ArticleManager;
import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.Auction;

public class AuctionDAOImpl implements AuctionDAO {

	private final String INSERT = "INSERT INTO ENCHERES (no_acheteur,no_article,date_debut_encheres,montant_enchere,state) VALUES(?,?,?,?,?)";
	private final String UPDATE = "UPDATE ENCHERES set no_acheteur=?, no_article=?, date_debut_encheres=?, montant_enchere=?, state=? WHERE id=?";
	private final String DELETE = "DELETE FROM ENCHERES WHERE id=?";
	private final String SELECTALL = "SELECT no_acheteur,no_article,date_debut_encheres,montant_enchere,state FROM ENCHERES";
	
	//Alors celle là c'est du freestyle! Mais en fait je vois pas comment récupérer le dernier prix (en cours) de l'article
	private final String SELECTLASTITEMPRICE = "SELECT montant_enchere, FROM ENCHERES";

	public void insert(Auction auction) throws DALException {
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, auction.getBuyer().getUserId());
			ps.setInt(2, auction.getAuctionedArticle().getArticleId());
			ps.setDate(3, (Date) auction.getAuctionDate());
			ps.setInt(4, auction.getItemPrice());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				auction.setAuctionedArticle(ArticleManager.getInstance().selectById(rs.getInt(1)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Auction insertion into database failed - ", e);
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
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Auction update into database failed - ", e);
	}
	}

	public void delete(int idObject) throws DALException {
		try (Connection connect = ConnectionProvider.getConnection();
		PreparedStatement ps = connect.prepareStatement(DELETE)) {
		ps.setInt(1, idObject);
		ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Auction deletion into database failed - ", e);
		}

	}

	public List<Auction> selectAll() throws DALException {
		List<Auction> lst;
		lst = new ArrayList<>();
		Auction auction = null;
		
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTALL)) {
			ResultSet rs = ps.executeQuery(SELECTALL);
			while (rs.next()) {
				auction = new Auction();
				auction.setBuyer(UserManager.getInstance().selectById(rs.getInt("no_acheteur")));
				auction.setAuctionedArticle(ArticleManager.getInstance().selectById(rs.getInt("no_article")));
				auction.setAuctionDate(rs.getDate("date_debut_encheres"));
				auction.setItemPrice(rs.getInt("montant_enchere"));
				lst.add(auction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : All SoldArticle selection from database failed - ",e);
		}
		return lst;
		}

	
	//METODE A INSERER POUR RECUPERER LE PRIX DE L'ENCHERE EN COURS
	// -->
	
	}









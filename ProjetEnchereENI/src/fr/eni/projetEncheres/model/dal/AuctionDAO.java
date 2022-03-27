package fr.eni.projetEncheres.model.dal;

import fr.eni.projetEncheres.model.bo.SoldArticle;
import fr.eni.projetEncheres.model.bo.Auction;

public interface AuctionDAO extends DAO<Auction> {

	public Auction selectBestAuctionFromArticle(SoldArticle article) throws DALException ;
	
	public Auction selectBestAuctionFromArticle(int articleId) throws DALException ;

}

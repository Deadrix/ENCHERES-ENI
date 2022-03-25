package fr.eni.projetEncheres.model.dal;

import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;

public interface SoldArticleDAO extends DAO<SoldArticle>{
	
	public List<SoldArticle> SelectByCategoryByState(String motCle, int categorie) throws DALException;
	
	public void updateAuction(int articleId);
	
	public void updateBuyer(int articleId);
	
	public void updateSoldPrice(int articleId);
	
}

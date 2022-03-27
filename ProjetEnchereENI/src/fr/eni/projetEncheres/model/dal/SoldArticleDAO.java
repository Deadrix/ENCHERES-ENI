package fr.eni.projetEncheres.model.dal;

import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;

public interface SoldArticleDAO extends DAO<SoldArticle> {

	public List<SoldArticle> selectByDescription(String motCle) throws DALException;

	public List<SoldArticle> selectByCategoryByState(int category, int state) throws DALException;

	public void updateAuction(SoldArticle article) throws DALException;

	public void updateBuyer(SoldArticle article) throws DALException;

	public void updateSoldPrice(SoldArticle article) throws DALException;
	
}

package fr.eni.projetEncheres.model.dal;

import java.util.List;

import fr.eni.projetEncheres.model.bo.SoldArticle;

public class SoldArticleDAOImpl implements DAO<SoldArticle> {
	
	private final String SQLINSERT="INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	private final String SQLUPDATE="UPDATE ARTICLES_VENDUS set nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial,prix_vente=?,no_utilisateur=?,no_categorie=? where id=?";
	private final String SQLDELETEBYID="DELETE FROM ARTICLES_VENDUS WHERE id=?";
	private final String SQLSELECTALL="SELECT nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS";
	private final String SQLSELECTBYID="SELECT nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS where id=?";
	private static SoldArticleDAOImpl instance = null;

	@Override
	public void insert(SoldArticle object) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SoldArticle object) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SoldArticle selectById(int idObject) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SoldArticle> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SoldArticle> selectByMotCle(String motCle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

}

package fr.eni.projetEncheres.model.bo;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer categoryId;
	private String libelle;
	private List<SoldArticle> articles;

	public Category() {
		super();
	}

	public Category(String libelle) {
		super();
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<SoldArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<SoldArticle> articles) {
		this.articles = articles;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}

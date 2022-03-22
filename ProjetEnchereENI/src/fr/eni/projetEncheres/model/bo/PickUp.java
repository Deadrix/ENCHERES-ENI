package fr.eni.projetEncheres.model.bo;

import java.io.Serializable;

public class PickUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rueRetrait;
	private String codePostalRetrait;
	private String villeRetrait;
	private SoldArticle articleToPickUp;
	
	public PickUp() {
		super();
	}

	public PickUp(String rueRetrait, String codePostalRetrait, String villeRetrait) {
		super();
		this.rueRetrait = rueRetrait;
		this.codePostalRetrait = codePostalRetrait;
		this.villeRetrait = villeRetrait;
	}

	public String getRueRetrait() {
		return rueRetrait;
	}

	public void setRueRetrait(String rueRetrait) {
		this.rueRetrait = rueRetrait;
	}

	public String getCodePostalRetrait() {
		return codePostalRetrait;
	}

	public void setCodePostalRetrait(String codePostalRetrait) {
		this.codePostalRetrait = codePostalRetrait;
	}

	public String getVilleRetrait() {
		return villeRetrait;
	}

	public void setVilleRetrait(String villeRetrait) {
		this.villeRetrait = villeRetrait;
	}

	public SoldArticle getArticleToPickUp() {
		return articleToPickUp;
	}

	public void setArticleToPickUp(SoldArticle articleToPickUp) {
		this.articleToPickUp = articleToPickUp;
	}

}

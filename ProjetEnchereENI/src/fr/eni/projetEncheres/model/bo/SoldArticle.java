package fr.eni.projetEncheres.model.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class SoldArticle implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idArticle;
	private String articleName;
	private String description;
	private Date auctionStart;
	private Date auctionEnd;
	private int initialPrice;
	private int soldPrice;
	private Category category;
	private User seller;
	private User buyer;
	
	
	public SoldArticle() {
		super();
	}


	public SoldArticle(int idArticle, String articleName, String description, Date auctionStart, Date auctionEnd,
			int initialPrice, int soldPrice, Category category, User seller, User buyer) {
		super();
		this.idArticle = idArticle;
		this.articleName = articleName;
		this.description = description;
		this.auctionStart = auctionStart;
		this.auctionEnd = auctionEnd;
		this.initialPrice = initialPrice;
		this.soldPrice = soldPrice;
		this.category = category;
		this.seller = seller;
		this.buyer = buyer;
	}

// Constructeur sans buyer pour l'initialiser plus tard (à l'achat de l'article)
	public SoldArticle(int idArticle, String articleName, String description, Date auctionStart, Date auctionEnd,
			int initialPrice, int soldPrice, Category category, User seller) {
		super();
		this.idArticle = idArticle;
		this.articleName = articleName;
		this.description = description;
		this.auctionStart = auctionStart;
		this.auctionEnd = auctionEnd;
		this.initialPrice = initialPrice;
		this.soldPrice = soldPrice;
		this.category = category;
		this.seller = seller;
	}


	public int getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}


	public String getArticleName() {
		return articleName;
	}


	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getAuctionStart() {
		return auctionStart;
	}


	public void setAuctionStart(Date auctionStart) {
		this.auctionStart = auctionStart;
	}


	public Date getAuctionEnd() {
		return auctionEnd;
	}


	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}


	public int getInitialPrice() {
		return initialPrice;
	}


	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}


	public int getSoldPrice() {
		return soldPrice;
	}


	public void setSoldPrice(int soldPrice) {
		this.soldPrice = soldPrice;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public User getSeller() {
		return seller;
	}


	public void setSeller(User seller) {
		this.seller = seller;
	}


	public User getBuyer() {
		return buyer;
	}


	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	
	
	
	
}

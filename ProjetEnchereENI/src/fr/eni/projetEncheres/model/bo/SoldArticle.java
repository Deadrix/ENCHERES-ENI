package fr.eni.projetEncheres.model.bo;

import java.io.Serializable;
import java.util.Date;

public class SoldArticle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer articleId;
	private String articleName;
	private String description;
	private Date auctionStart;
	private Date auctionEnd;
	private Integer initialPrice;
	private Integer soldPrice;
	private User buyer;
	private User seller;

	private Category category;

	public SoldArticle() {
		super();
	}

	public SoldArticle(String articleName, String description, Date auctionStart, Date auctionEnd, Integer initialPrice,
			Category category) {
		super();
		this.articleName = articleName;
		this.description = description;
		this.auctionStart = auctionStart;
		this.auctionEnd = auctionEnd;
		this.initialPrice = initialPrice;
		this.category = category;
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

	public Integer getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Integer initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(Integer soldPrice) {
		this.soldPrice = soldPrice;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
}

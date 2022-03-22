package fr.eni.projetEncheres.model.bo;

import java.io.Serializable;
import java.util.Date;

public class Auction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date auctionDate;
	private Integer itemPrice;
	private User user;
	private SoldArticle auctionedArticle;

	public Auction() {
		super();
	}

	public Auction(Date auctionDate, Integer itemPrice, User user, SoldArticle auctionedArticle) {
		super();
		this.auctionDate = auctionDate;
		this.itemPrice = itemPrice;
		this.user = user;
		this.auctionedArticle = auctionedArticle;

	}

	public Date getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SoldArticle getAuctionedArticle() {
		return auctionedArticle;
	}

	public void setAuctionedArticle(SoldArticle auctionedArticle) {
		this.auctionedArticle = auctionedArticle;
	}

}
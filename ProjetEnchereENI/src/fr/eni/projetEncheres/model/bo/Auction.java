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
	private User buyer;
	private SoldArticle auctionedArticle;

	public Auction() {
		super();
	}

	public Auction(Date auctionDate, Integer itemPrice, User buyer, SoldArticle auctionedArticle) {
		super();
		this.auctionDate = auctionDate;
		this.itemPrice = itemPrice;
		this.buyer = buyer;
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

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public SoldArticle getAuctionedArticle() {
		return auctionedArticle;
	}

	public void setAuctionedArticle(SoldArticle auctionedArticle) {
		this.auctionedArticle = auctionedArticle;
	}

}
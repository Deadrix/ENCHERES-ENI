package fr.eni.projetEncheres.model.bo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String alias;
	private String lastName;
	private String firstName;
	private String email;
	private String telephone;
	private String street;
	private String postalCode;
	private String city;
	private String password;
	private List<SoldArticle> soldArticle;
	private List<SoldArticle> boughtArticle;
	private List<Auction> auctions;
	private Integer credit;

	

	public User() {
		super();
	}
	public User(String alias, String email) {
		this.alias = alias;
		this.email=email;
		
	}
	public User(String alias, String lastName, String firstName, String email, String telephone,
			String street, String postalCode, String city, String password, Integer credit) {
		super();
		this.userId = userId;
		this.alias = alias;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.telephone = telephone;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credit = credit;
	}

	public User(String alias, String lastName, String firstName, String email, String telephone, String street,
			String postalCode, String city, String password) {
		super();
		this.alias = alias;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.telephone = telephone;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
	}

	public User(String alias, String email, String password) {
		super();
		this.alias = alias;
		this.email = email;
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public List<SoldArticle> getSoldArticle() {
		return soldArticle;
	}

	public void setSoldArticle(List<SoldArticle> soldArticle) {
		this.soldArticle = soldArticle;
	}

	public List<SoldArticle> getBoughtArticle() {
		return boughtArticle;
	}

	public void setBoughtArticle(List<SoldArticle> boughtArticle) {
		this.boughtArticle = boughtArticle;
	}
	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
}

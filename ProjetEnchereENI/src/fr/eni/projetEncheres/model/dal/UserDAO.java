package fr.eni.projetEncheres.model.dal;

import fr.eni.projetEncheres.model.bo.User;

public interface UserDAO extends DAO<User>{

	public User Login(String email, String pwd);
	

}

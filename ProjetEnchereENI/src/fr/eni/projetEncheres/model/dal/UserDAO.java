package fr.eni.projetEncheres.model.dal;

import fr.eni.projetEncheres.model.bo.User;

public interface UserDAO extends DAO<User>{

	public User Login(String email, String pwd);

	void updateAdmin(User user) throws DALException;

	void updateCredit(User user) throws DALException;

	void updatePassword(User user) throws DALException;
	

}

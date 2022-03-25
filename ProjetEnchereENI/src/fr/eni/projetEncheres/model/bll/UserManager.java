package fr.eni.projetEncheres.model.bll;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.UserDAOImpl;

public class UserManager {

	private static UserManager InstanceofUserManager = null;
	private UserDAOImpl aUserDAOImpl = (UserDAOImpl) DAOFactory.getUserDAO();
	
	public UserManager(UserDAOImpl aUserDAOImpl) {
		this.aUserDAOImpl = aUserDAOImpl;
	}
	
	public static UserManager getInstance() {
		if (InstanceofUserManager == null) {
			InstanceofUserManager = new UserManager((UserDAOImpl) DAOFactory.getUserDAO());
		}
		return InstanceofUserManager;	
	}

	public User login(String email, String pwd)
	{
		return aUserDAOImpl.Login(email, pwd);		
	}
	
	public void insert(User user) throws DALException {
		aUserDAOImpl.insert(user);
	}
	public User selectByMail(User user) throws DALException {
		return aUserDAOImpl.selectByMail(user);
	}
	public User selectByAlias(User user) throws DALException {
		return aUserDAOImpl.selectByAlias(user);
	}
	
	public void delete(Integer userId) throws DALException {
		aUserDAOImpl.delete(userId);
	}
	public User selectById(Integer userId) throws DALException {
		return aUserDAOImpl.selectById(userId);
	}
	public void update(User user) throws DALException {
		aUserDAOImpl.update(user);
	}


	
	
}

package fr.eni.projetEncheres.model.bll;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;

public class UserManager {

	private DAOFactory instanceOfDaoFactory = new DAOFactory();
	private static UserManager InstanceofUserManager = null ;
	
	public UserManager(DAOFactory instanceOfDaoFactory) {
		this.instanceOfDaoFactory = instanceOfDaoFactory;
	}
	
	public static UserManager getInstance() {
		if (InstanceofUserManager == null) {
			InstanceofUserManager = new UserManager(DAOFactory.getInstanceOfDaoFactory());
		}
		return InstanceofUserManager;	
	}

	public void insert(User user) throws DALException {
		instanceOfDaoFactory.getUserDAO().insert(user);
	}
	
	public void delete(Integer userId) throws DALException {
		instanceOfDaoFactory.getUserDAO().delete(userId);
	}
	public User selectById(Integer userId) throws DALException {
		return instanceOfDaoFactory.getUserDAO().selectById(userId);
	}
	public void update(User user) throws DALException {
		instanceOfDaoFactory.getUserDAO().update(user);
	}
	
	public User login(String email, String pwd)
	{
		return instanceOfDaoFactory.getUserDAO().Login(email, pwd);
		
	}
	
}

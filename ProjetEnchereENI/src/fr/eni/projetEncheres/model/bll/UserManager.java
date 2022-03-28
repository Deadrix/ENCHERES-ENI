package fr.eni.projetEncheres.model.bll;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.UserDAOImpl;

public class UserManager {

	private static UserManager instanceOfUserManager = null;
	private UserDAOImpl aUserDAOImpl = (UserDAOImpl) DAOFactory.getUserDAO();
	private String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	private String passwordRegex = "(?i)^[A-Za-zÀ-ÖØ-öø-ÿ0-9 @!&-]+$";

	public UserManager(UserDAOImpl aUserDAOImpl) {
		this.aUserDAOImpl = aUserDAOImpl;
	}

	public UserManager() {
	}

	public static UserManager getInstance() {
		if (instanceOfUserManager == null) {
			instanceOfUserManager = new UserManager((UserDAOImpl) DAOFactory.getUserDAO());
		}
		return instanceOfUserManager;
	}

	public User registerProcess(User user) throws BLLException {
		User tempUser = new User();
		boolean registered = false;
		registered = validatePassword(user.getPassword());
		if (registered == true) {
			registered = validateEmail(user.getEmail());
			if (registered == true) {
				registered = existingEmail(user.getEmail());
				if (registered == false) {
					try {
						tempUser.setCredit(0);
						tempUser = register(user);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new BLLException("Are you Drunk, you are already in");
				}
			}
		} else {
			throw new BLLException("Are you Drunk, that is not your email");

		}
		return tempUser;

	}

	public User updateProcess(User user) throws DALException, BLLException {
		boolean youAreAllowed = false;
		youAreAllowed = validatePassword(user.getPassword());
		if (youAreAllowed == true) {
			youAreAllowed = validateEmail(user.getEmail());
			if (youAreAllowed == true) {
				if (login(user.getEmail(), user.getPassword()) != null) {
					updateUser(user);
				}
				;
				aUserDAOImpl.updateUser(user);
			} else {
				throw new BLLException("password not valid for an update");
			}
		} else {
			throw new BLLException("email not valid for an update");
		}return user;
	}

	private void updateUser(User user) throws DALException {
		 aUserDAOImpl.updateUser(user);
		
	}

	public boolean validatePassword(String password) throws BLLException {
		boolean legit = true;

		if (!(password.length() >= 4)) {
			legit = false;
			throw new BLLException("Password must be at least 8 characters");
		}

		if (!password.matches(passwordRegex)) {
			legit = false;
			throw new BLLException(
					"password must contain only letters, numbers, white spaces and some special characters (!@&-)");
		}
		return legit;
	}

	private boolean existingEmail(String email) throws BLLException {
		return aUserDAOImpl.existingEmail(email);
	}

	public boolean validateEmail(String email) throws BLLException {
		boolean legit = true;

		if (!email.matches(emailRegex)) {
			legit = false;
			throw new BLLException("email invalid, come on you can do better");
		}
		return legit;
	}

	public User login(String email, String pwd) {
		return aUserDAOImpl.Login(email, pwd);
	}

	public User register(User user) throws DALException {
		return aUserDAOImpl.register(user);
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

//	public void update(User user) throws DALException {
//		aUserDAOImpl.update(user);
//	}
}

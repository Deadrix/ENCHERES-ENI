package fr.eni.projetEncheres.model.bll;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.DALException;
import fr.eni.projetEncheres.model.dal.DAOFactory;
import fr.eni.projetEncheres.model.dal.UserDAOImpl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserManager {
	private static UserManager instanceOfUserManager = null;

	private UserDAOImpl aUserDAOImpl = (UserDAOImpl) DAOFactory.getUserDAO();

	private String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

	private String passwordRegex = "^[A-Za-z@!&-]+$";

	public UserManager(UserDAOImpl aUserDAOImpl) {
		this.aUserDAOImpl = aUserDAOImpl;
	}

	public UserManager() {
	}

	public static UserManager getInstance() {
		if (instanceOfUserManager == null)
			instanceOfUserManager = new UserManager((UserDAOImpl) DAOFactory.getUserDAO());
		return instanceOfUserManager;
	}

	public User registerProcess(User user) throws BLLException {
		boolean registered = false;
		registered = validatePassword(user.getPassword());
		if (registered) {
			registered = validateEmail(user.getEmail());
			if (registered) {
				registered = existingEmail(user.getEmail());
				if (!registered) {
					user.setPassword(anneHashaway(user.getPassword()));
					user.setCredit(Integer.valueOf(0));
					register(user);
				} else {
					throw new BLLException("Are you Drunk, you are already in");
				}
			}
		} else {
			throw new BLLException("Are you Drunk, that is not your email");
		}
		return user;
	}

	public String anneHashaway(String input) throws BLLException {
		StringBuilder bs = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digested = md.digest(input.getBytes());
			byte b;
			int i;
			byte[] arrayOfByte1;
			for (i = (arrayOfByte1 = digested).length, b = 0; b < i;) {
				byte b1 = arrayOfByte1[b];
				bs.append(String.format("%02x", new Object[] { Byte.valueOf(b1) }));
				b++;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		} finally {
		}
		return bs.toString();
	}

	public User updateProcess(User user) throws BLLException {
		boolean youAreAllowed = false;
		youAreAllowed = validateEmail(user.getEmail());
		if (youAreAllowed) {
			if (login(user.getEmail(), user.getPassword()).getUserId() == user.getUserId())
				updateUser(user);
		} else {
			throw new BLLException("email not valid for an update");
		}
		return user;
	}

	
	public void updateAdmin(User user) throws BLLException {
		try {
			this.aUserDAOImpl.updateAdmin(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}
	public void updateCredit (User user) throws BLLException {
		try {
			this.aUserDAOImpl.updateCredit(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}
	private void updateUser(User user) throws BLLException {
		try {
			this.aUserDAOImpl.updateUser(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}

	public boolean validatePassword(String password) {
		boolean legit = true;
		if (password.length() < 4)
			legit = false;
		if (!password.matches(this.passwordRegex))
			legit = false;
		return legit;
	}

	private boolean existingEmail(String email) throws BLLException {
		return this.aUserDAOImpl.existingEmail(email);
	}

	public boolean validateEmail(String email) throws BLLException {
		boolean legit = true;
		if (!email.matches(this.emailRegex)) {
			legit = false;
			throw new BLLException("email invalid, come on you can do better");
		}
		return legit;
	}

	public User login(String email, String pwd) {
		try {
			pwd = anneHashaway(pwd);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return this.aUserDAOImpl.Login(email, pwd);
	}

	public User register(User user) throws BLLException {
		try {
			return this.aUserDAOImpl.register(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}

	public void insert(User user) throws BLLException {
		try {
			this.aUserDAOImpl.insert(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}

	public User selectByMail(User user) throws BLLException {
		try {
			return this.aUserDAOImpl.selectByMail(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}

	public User selectByAlias(User user) throws BLLException {
		try {
			return this.aUserDAOImpl.selectByAlias(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}

	public void delete(Integer userId) throws BLLException {
		try {
			this.aUserDAOImpl.delete(userId.intValue());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}

	public User selectById(Integer userId) throws BLLException {
		try {
			return this.aUserDAOImpl.selectById(userId.intValue());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(" you fucked up boy bll edition");
		}
	}
}

package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bo.User;

public class UserDAOImpl implements UserDAO {

	User tempUserDAO;

//MSSQL
	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,rue,code_postal,"
			+ "ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,1)";
	private static final String UPDATE = "UPDATE UTILISATEURS SET (pseudo,nom,prenom,email,rue,code_postal,"
			+ "ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,1) WHERE  no_utilisateur = ?";
	private static final String UPDATEPASSWORD = "UPDATE UTILISATEURS SET mot_de_passe=? where email=?";
	private static final String UPDATECREDITBYID = "UPDATE UTILISATEURS SET credit=? where no_utilisateur=?";
	
	private static final String DELETEBYID = "DELETE FROM UTILISATEURS where no_utilisateur=?";
	private static final String SELECTBYID = "SELECT no_utilisateur, pseudo, nom, prenom, email, rue, code_postal,"
			+ "ville,mot_de_passe,credit,administrateur from UTILISATEURS WHERE  no_utilisateur = ?";
	private static final String SELECTALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, rue, code_postal,"
			+ "ville,mot_de_passe,credit,administrateur from UTILISATEURS";
	private static final String SELECTBYMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where email=?";
	private static final String SELECTBYALIAS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where alias=?";
	private final String LOGIN="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where email=? and mot_de_passe=?";

//MYSQL
//	private static final String INSERT = "INSERT INTO USERS (userAlias,userLastName,userFirstName,userEmail,userStreet,userZipCode,"
//			+ "userCity,userPassword,userCredit,userAdmin) VALUES(?,?,?,?,?,?,?,?,?,1)";
//	private static final String UPDATE = "UPDATE USERS SET userAlias=? ,userLastName=? ,userFirstName=? ,userEmail=?,userStreet=?,userZipCode=?,userCity=?,userPassword=?,userCredit=?,userAdmin=1 WHERE userID = ?";
//	private static final String UPDATEPASSWORD = "UPDATE USERS SET userPassword=? where userEmail=?";
//	private static final String UPDATEuserCreditBYID = "UPDATE USERS SET userCredit=? where userID=?";
//
//	private static final String DELETEBYID = "DELETE FROM USERS where userID=?";
//	private static final String SELECTBYID = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userStreet, userZipCode,"
//			+ "userCity,userPassword,userCredit,userAdmin from USERS WHERE  userID = ?";
//	private static final String SELECTALL = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userStreet, userZipCode,"
//			+ "userCity,userPassword,userCredit,userAdmin from USERS";
//	private static final String SELECTBYMAIL = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userTelephone, userStreet, userZipCode, userCity, userPassword, userCredit, userAdmin FROM USERS where userEmail=?";
//	private static final String SELECTBYALIAS = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userTelephone, userStreet, userZipCode, userCity, userPassword, userCredit, userAdmin FROM USERS where userAlias=?";
//	private final String LOGIN = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userTelephone, userStreet, userZipCode, userCity, userPassword, userCredit, userAdmin FROM USERS where userEmail=? and userPassword=?";

	private void setFields(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getAlias());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getFirstName());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getStreet());
		ps.setString(6, user.getPostalCode());
		ps.setString(7, user.getCity());
		ps.setString(8, user.getPassword());
		ps.setInt(9, user.getCredit());
	}

	private User getFields(ResultSet rs, User user) throws SQLException {
		user.setUserId(rs.getInt("userID"));
		user.setAlias(rs.getString("userAlias"));
		user.setLastName(rs.getString("userLastName"));
		user.setFirstName(rs.getString("userFirstName"));
		user.setEmail(rs.getString("userEmail"));
		user.setStreet(rs.getString("userStreet"));
		user.setPostalCode(rs.getString("userZipCode"));
		user.setCity(rs.getString("userCity"));
		user.setPassword(rs.getString("userPassword"));
		user.setTelephone(rs.getString("userTelephone"));
		user.setCredit(rs.getInt("userCredit"));
		return user;
	}

	public boolean existingEmail(String email) throws BLLException {
		boolean exist = false;
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYMAIL)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				exist = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}

	public User selectByMail(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(SELECTBYMAIL)) {
				tempUserDAO = new User();
				ps.setString(1, user.getEmail());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tempUserDAO;
	}

	public User selectByAlias(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(SELECTBYALIAS)) {
				tempUserDAO = new User();
				ps.setString(1, user.getAlias());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tempUserDAO;
	}

	public User register(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
				setFields(ps, user);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					user.setUserId(rs.getInt(1));
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void updateUser(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(UPDATE)) {
				setFields(ps, user);
				ps.setInt(10, user.getUserId());
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void insert(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
				setFields(ps, user);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					user.setUserId(rs.getInt(1));
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(UPDATE)) {
				setFields(ps, user);
				ps.setInt(1, user.getUserId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public User Login(String userEmail, String pwd) {
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(LOGIN)) {
			tempUserDAO = new User();
			ps.setString(1, userEmail);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				getFields(rs, tempUserDAO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempUserDAO;
	}

	@Override
	public User selectById(int userid) throws DALException {
		User tempUserDAO = new User();
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYID)) {
			ps.setInt(1, userid);
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				getFields(rs, tempUserDAO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempUserDAO;
	}

	@Override
	public List<User> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<User> selectByMotCle(String motCle) throws DALException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(int userId) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(DELETEBYID);
			ps.setInt(1, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

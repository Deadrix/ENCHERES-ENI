package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import fr.eni.projetEncheres.model.bll.BLLException;
import fr.eni.projetEncheres.model.bo.User;

public class UserDAOImpl implements UserDAO {

	

//MSSQL
//	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,rue,code_postal,"
//			+ "ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,1)";
//	private static final String UPDATE = "UPDATE UTILISATEURS SET (pseudo,nom,prenom,email,rue,code_postal,"
//			+ "ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,1) WHERE  no_utilisateur = ?";
//	private static final String UPDATEPASSWORD = "UPDATE UTILISATEURS SET mot_de_passe=? where email=?";
//	private static final String UPDATECREDITBYID = "UPDATE UTILISATEURS SET credit=? where no_utilisateur=?";
//	private static final String DELETEBYID = "DELETE FROM UTILISATEURS where no_utilisateur=?";
//	private static final String SELECTBYID = "SELECT no_utilisateur, pseudo, nom, prenom, email, rue, code_postal,"
//			+ "ville,mot_de_passe,credit,administrateur from UTILISATEURS WHERE  no_utilisateur = ?";
//	private static final String SELECTALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, rue, code_postal,"
//			+ "ville,mot_de_passe,credit,administrateur from UTILISATEURS";
//	private static final String SELECTBYMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where email=?";
//	private static final String SELECTBYALIAS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where alias=?";
//	private final String LOGIN="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where email=? and mot_de_passe=?";



//Kam's Tables
	private static final String INSERT = "INSERT INTO USERS (userAlias,userLastName,userFirstName,userEmail,userTelephone, userStreet, userZipCode,"
			+ "userCity,userPassword,userCredit,userAdmin) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATEUSER = "UPDATE USERS SET userAlias=? ,userLastName=? ,userFirstName=? ,userEmail=?, userTelephone=?, userStreet=?, userZipCode=?, userCity=?, userCredit=?, userAdmin=? WHERE userID = ?";
	private static final String UPDATEPASSWORDBYID = "UPDATE USERS SET userPassword=? where userEmail=?";
	private static final String UPDATECREDITBYID = "UPDATE USERS SET userCredit=? where userID=?";
	private static final String UPDATEADMINBYID = "UPDATE USERS SET userAdmin=? where userID=?";
	private static final String DELETEBYID = "DELETE FROM USERS where userID=?";
	private static final String SELECTBYID = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userStreet, userZipCode,"
			+ "userCity,userPassword,userCredit,userAdmin from USERS WHERE  userID = ?";
	private static final String SELECTALL = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userStreet, userZipCode,"
			+ "userCity,userPassword,userCredit,userAdmin from USERS";
 static final String SELECTBYMAIL = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userTelephone, userStreet, userZipCode, userCity, userPassword, userCredit, userAdmin FROM USERS where userEmail=?";
	private static final String SELECTBYALIAS = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userTelephone, userStreet, userZipCode, userCity, userPassword, userCredit, userAdmin FROM USERS where userAlias=?";
	private final String LOGIN = "SELECT userID, userAlias, userLastName, userFirstName, userEmail, userTelephone, userStreet, userZipCode, userCity, userPassword, userCredit, userAdmin FROM USERS where userEmail=? and userPassword=?";

	

// ARX's Tables
//	private User getFields(ResultSet rs, User user) throws SQLException {
//		user.setUserId(rs.getInt("no_utilisateur"));
//		user.setAlias(rs.getString("pseudo"));
//		user.setLastName(rs.getString("nom"));
//		user.setFirstName(rs.getString("prenom"));
//		user.setEmail(rs.getString("email"));
//		user.setTelephone(rs.getString("telephone"));
//		user.setStreet(rs.getString("rue"));
//		user.setPostalCode(rs.getString("code_postal"));
//		user.setCity(rs.getString("ville"));
//		user.setPassword(rs.getString("mot_de_passe"));
//		user.setCredit(rs.getInt("credit"));
//		user.setamIAdmin(rs.getBoolean("administrateur"));
//		return user;
//	}

	private User getFields(ResultSet rs, User user) throws SQLException {
// ARX's Tables
//	user.setUserId(rs.getInt("no_utilisateur"));
//	user.setAlias(rs.getString("pseudo"));
//	user.setLastName(rs.getString("nom"));
//	user.setFirstName(rs.getString("prenom"));
//	user.setEmail(rs.getString("email"));
//	user.setStreet(rs.getString("rue"));
//	user.setPostalCode(rs.getString("code_postal"));
//	user.setCity(rs.getString("ville"));
//	user.setPassword(rs.getString("mot_de_passe"));
//	user.setTelephone(rs.getString("telephone"));
//	user.setCredit(rs.getInt("credit"));

//	Kam's Tables
    user.setUserId(Integer.valueOf(rs.getInt("userID")));
    user.setAlias(rs.getString("userAlias"));
    user.setLastName(rs.getString("userLastName"));
    user.setFirstName(rs.getString("userFirstName"));
    user.setEmail(rs.getString("userEmail"));
    user.setTelephone(rs.getString("userTelephone"));
    user.setStreet(rs.getString("userStreet"));
    user.setPostalCode(rs.getString("userZipCode"));
    user.setCity(rs.getString("userCity"));
    user.setPassword(rs.getString("userPassword"));
    user.setCredit(Integer.valueOf(rs.getInt("userCredit")));
    user.setamIAdmin(rs.getBoolean("userAdmin"));
    return user;
  }

	private void setFields(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getAlias());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getFirstName());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getTelephone());
		ps.setString(6, user.getStreet());
		ps.setString(7, user.getPostalCode());
		ps.setString(8, user.getCity());
		ps.setString(9, user.getPassword());
		ps.setInt(10, user.getCredit());
		ps.setBoolean(11, user.getAmIAdmin());

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
			e.printStackTrace();
		}
		return exist;
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
					PreparedStatement ps = connect.prepareStatement(UPDATEUSER)) {
				setFields(ps, user);
				ps.setInt(12, user.getUserId());
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updatePassword(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(UPDATEPASSWORDBYID)) {
				setFields(ps, user);
				ps.setInt(12, user.getUserId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void updateCredit(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(UPDATECREDITBYID)) {
				setFields(ps, user);
				ps.setInt(12, user.getUserId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void updateAdmin(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(UPDATEADMINBYID)) {
				setFields(ps, user);
				ps.setInt(12, user.getUserId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User Login(String userEmail, String pwd) {
		User tempUserDAO = new User();
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(LOGIN)) {
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
	public User selectById(int UserId) throws DALException {
		User tempUserDAO = new User();
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SELECTBYID)) {
			ps.setInt(1, UserId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				getFields(rs, tempUserDAO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempUserDAO;
	}

public User selectByMail(User user) throws DALException {
		
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(SELECTBYMAIL)) {
				ps.setString(1, user.getEmail());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public User selectByAlias(User user) throws DALException {
		if (user != null) {
			try (Connection connect = ConnectionProvider.getConnection();
					PreparedStatement ps = connect.prepareStatement(SELECTBYALIAS)) {
				ps.setString(1, user.getAlias());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					getFields(rs, user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
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
	public void delete(int UserId) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(DELETEBYID);
			ps.setInt(1, UserId);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteMe(int UserId) throws DALException {

		try (Connection connect = ConnectionProvider.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(DELETEBYID);
			ps.setInt(1, UserId);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(User object) throws DALException {
		// TODO Auto-generated method stub
		
	}

}

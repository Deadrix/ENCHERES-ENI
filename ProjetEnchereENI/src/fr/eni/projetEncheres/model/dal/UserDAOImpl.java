package fr.eni.projetEncheres.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.projetEncheres.model.bo.User;
import fr.eni.projetEncheres.model.dal.ConnectionProvider;
import fr.eni.projetEncheres.model.dal.UserDAO;
import fr.eni.projetEncheres.model.dal.DALException;

public class UserDAOImpl implements UserDAO {

	User tempUser = new User();

	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,rue,code_postal,"
			+ "ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,1)";
	private static final String UPDATE = "update UTILISATEURS SET (pseudo,nom,prenom,email,rue,code_postal,"
			+ "ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETEBYID = "delete from UTILISATEURS where no_utilisateur=?";
	private static final String SelectById = "SELECT no_utilisateur, pseudo, nom, prenom, email, rue, code_postal,"
			+ "ville,mot_de_passe,credit,administrateur from UTILISATEURS WHERE  no_utilisateur = ?";
	private static final String SELECTALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, rue, code_postal,"
			+ "ville,mot_de_passe,credit,administrateur from UTILISATEURS";
	private static final String SELECTBYKEYWORD = "SELECT pseudo FROM UTILISATEURS WHERE email like ? and mot_de_passe like ?";
	private final String SQLLOGIN="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where email=? and mot_de_passe=?";


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
		user.setUserId(rs.getInt("no_utilisateur"));
		user.setAlias(rs.getString("pseudo"));
		user.setLastName(rs.getString("nom"));
		user.setFirstName(rs.getString("prenom"));
		user.setEmail(rs.getString("email"));
		user.setStreet(rs.getString("rue"));
		user.setPostalCode(rs.getString("code_postal"));
		user.setCity(rs.getString("ville"));
		user.setPassword(rs.getString("mot_de_passe"));
		user.setTelephone(rs.getString("telephone"));
		user.setCredit(rs.getInt("credit"));
		return user;
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
	public User Login(String email, String pwd) {
		User tempUser = null;
		try {
			Connection con = connectionBDD();
			PreparedStatement ps = con.prepareStatement(SQLLOGIN);
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				tempUser = new User();
				getFields(rs, tempUser);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException d) {
			d.printStackTrace();
		}
		return tempUser;
	}

	@Override
	public User selectById(int userid) throws DALException {
		User tempUser = new User();
		try (Connection connect = ConnectionProvider.getConnection();
				PreparedStatement ps = connect.prepareStatement(SelectById)) {
			ps.setInt(1, userid);
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				getFields(rs, tempUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempUser;
	}

	@Override
	public List<User> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectByMotCle(String motCle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

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
	
	
	private static Connection connectionBDD() throws ClassNotFoundException, SQLException {
		
			
			Connection cnx=null;
			DataSource ds;
			InitialContext ctx;
			try 
			{
				ctx=new InitialContext();
				ds=(DataSource) ctx.lookup("java:comp/env/jdbc/pool_cnx");
				cnx=ds.getConnection();
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cnx;
			
		}
}

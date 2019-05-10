package fr.library.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.library.model.User;

/**
 * 
 * @author Titouan
 *
 */
public class UserDaoImpl implements IUserDao {

	private final static Logger logger = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {}
	/*
	 * Create a new user into the DB Library, table users
	 * @see fr.library.sql.UserDao#createUser(fr.library.entities.User)
	 */
	@Override
	public Long createUser(User user) {
		Connection connect = null;
		PreparedStatement prepared = null, ps_id = null;
		ResultSet res = null;
		String queryInsert = "INSERT INTO users(firstname, lastname, mail, password, connected) VALUES(?,?,?,?,?)";
		String queryId = "SELECT id FROM users WHERE lastname=? AND firstName=? AND mail=?;";
		Long id_user = null;
		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(queryInsert);
			prepared.setString(1, user.getFirstName());
			prepared.setString(2, user.getLastName());
			prepared.setString(3, user.getMail());
			prepared.setString(4, user.getPassword());
			prepared.setBoolean(5, false); //set to false during register
			prepared.executeUpdate();

			// Get the id of the new user
			ps_id = connect.prepareStatement(queryId);
			ps_id.setString(1, user.getLastName());
			ps_id.setString(2, user.getFirstName());
			ps_id.setString(3, user.getMail());
			res = ps_id.executeQuery();
			while(res.next()) {
				id_user = res.getLong("id");
			}			
		}catch(SQLException e) {
			logger.error("Create user : ",e);
		}
		//Close the connection
		finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(ps_id!=null) {
					ps_id.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("Create user/Close connection : ",e);
			}
		}
		return id_user; //return the id of the new user or null if there is a problem
	}

	@Override
	public User getById(Long id) {
		User user_return = null;
		Connection connect = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		String query = "SELECT * FROM users WHERE id=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, id);
			res = prepared.executeQuery();
			// Find the user associated to the id
			if(res.next()) {

				user_return = new User(res.getLong("id"),
						res.getString("firstname"),
						res.getString("lastname"),
						res.getString("mail"),
						res.getString("password"),
						res.getBoolean("connected"));
			}

		} catch (SQLException e) {
			logger.error("GetByIdUser :",e);
		}
		//Close the connection
		finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("Connection GetById :",e);
			}
		}
		return user_return;
	}

	@Override
	public void deleteItem(User user) {
		Connection connect = null;
		PreparedStatement prepared = null;
		String query = "DELETE FROM users WHERE id=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, user.getId());
			prepared.executeUpdate();
		} catch (SQLException e) {
			logger.error("GetById : ",e);
		}
		//Close the connection
		finally {
			try {
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("GetById/Close Connection : ",e);
			}
		}
	}

	@Override
	public void updateItem(User user) {
		Connection connect = null;
		PreparedStatement prepared = null;
		String query = "UPDATE users SET firstname=?, lastname=?, mail=?, password=?, connected=? WHERE id=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setString(1, user.getFirstName());
			prepared.setString(2, user.getLastName());
			prepared.setString(3, user.getMail());
			prepared.setString(4, user.getPassword());
			prepared.setBoolean(5, user.isConnected());
			prepared.setLong(6, user.getId());
			prepared.executeUpdate();
		} catch (SQLException e) {
			logger.error("UpdateItem : ",e);
		}
		//Close the connection
		finally {
			try {
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("UpdateItem/CloseConnection : ",e);
			}
		}

	}

	@Override
	public List<User> findAll() {
		List<User> list_users = new ArrayList<>();
		Connection connect = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		String query = "SELECT * FROM users;";

		try {
			connect =  DaoConnection.getInstance().getConnection(); //Get connection from factory
			prepared = connect.prepareStatement(query); 
			res = prepared.executeQuery();
			while(res.next()) {
				//Create user for each raw of the table and add it in list_users
				list_users.add(new User(res.getLong("id"),
						res.getString("firstname"),
						res.getString("lastname"),
						res.getString("mail"), 
						res.getString("password"), 
						res.getBoolean("connected")));
			}
		} catch (SQLException e) {
			logger.error("FindAll : ",e);
		}
		//Close the connection
		finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("FindAll/Close Connection : ",e);
			}
		}

		return list_users;
	}
	@Override
	public User userExists(String login) {
		Connection connect = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		User userReturn = null;
		String query = "SELECT * FROM users WHERE mail=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setString(1, login);
			res = prepared.executeQuery();
			if(res.next()) {
				userReturn = new User();
				userReturn.setId(res.getLong("id"));
				userReturn.setFirstName(res.getString("firstname"));
				userReturn.setLastName(res.getString("lastname"));
				userReturn.setPassword(res.getString("password"));
				userReturn.setMail(res.getString("mail"));
				userReturn.setConnected(res.getBoolean("connected"));
			}
		} catch (SQLException e) {
			logger.error("UserExists",e);
		}
		return userReturn;
	}

	@Override
	public boolean isConnected(Long id) {
		String query = "SELECT connected FROM users WHERE id=?;";
		ResultSet res = null;
		try(	//Using autoclosable
				Connection connect = DaoConnection.getInstance().getConnection();
				PreparedStatement prepared = connect.prepareStatement(query);
				) {
			prepared.setLong(1, id);
			res = prepared.executeQuery();
			if(res.next()) {
				return res.getBoolean("connected");
			}
		} catch (SQLException e) {
			logger.error("login", e);
		}finally {
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e) {
					logger.error("Close is Connected", e);
				}
			}
		}
		return false;
	}

	@Override
	public User login(String mail, String password){

		String query = "UPDATE users SET connected = TRUE WHERE id=?;";
		User user = userExists(mail);
		if(user!=null) {
			try(	//Using autoclosable
					Connection connect = DaoConnection.getInstance().getConnection();
					PreparedStatement prepared = connect.prepareStatement(query);
					) {
				prepared.setLong(1, user.getId());
				prepared.executeUpdate();
				return user;
			} catch (SQLException e) {
				logger.error("login", e);
			}
		}
		return null;
	}
	@Override
	public void logout(Long id) {
		String query = "UPDATE users SET connected=FALSE WHERE id=?;";

		if(isConnected(id)) {
			try(	//Using autoclosable
					Connection connect = DaoConnection.getInstance().getConnection();
					PreparedStatement prepared = connect.prepareStatement(query);
					) {
				prepared.setLong(1, id);
				prepared.executeUpdate();
			}catch(SQLException e) {
				logger.error("logout", e);
			}
		}
	}
}

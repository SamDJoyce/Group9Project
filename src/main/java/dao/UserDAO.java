package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import services.UserService;
import users.User;
import users.UserFactory;

/**
 * Implements UserService for MySQL.
 * Mediates all Employee table interactions.
 * 
 * @author Sam Joyce
 */
public class UserDAO implements UserService {

	private static final String usersTable = "users";
	
	public UserDAO() {
	}

	/**
	 * Generate a new Employee object, insert it into
	 * the DB, assign it an ID, then return the completed
	 * employee.
	 * 
	 * @param firstName	employee's first name. Max 50 characters
	 * @param lastName	employee's last name. Max 50 characters
	 * @param email		employee's email address
	 * @param type		fulltime, parttime, casual employment status
	 * @param passHash	the employee's hashed password value
	 * @return			the fully constructed new Employee object
	 */
	@Override
	public User createUser(	String firstName, 
							String lastName, 
							String email, 
							String type,
							String passHash) {
		
		User user = UserFactory.get(
								firstName, 
								lastName, 
								email, 
								type, 
								passHash
								);
		
		String insertUser = "INSERT INTO " + usersTable + " ("
				+ "firstName, lastName, email, type, seniority, passHash) "
				+ "VALUES (?,?,?,?,0,?)";
		
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		ResultSet		  generatedKeys = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(insertUser,
						Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, type);
			statement.setString(5, passHash);
			
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				int generatedUserID = generatedKeys.getInt(1);
				user.setUserId(generatedUserID);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (generatedKeys != null) {
					generatedKeys.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param userId the userId of the employee to be retrieved.
	 */
	@Override
	public User getUser(int userId) {
		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getUser = "SELECT * FROM " + usersTable + " WHERE userId = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getUser);
			statement.setInt(1, userId);
			resSet = statement.executeQuery();
			if (resSet.next()) {
				user = UserFactory.get(	resSet.getInt	("userId"), 
										resSet.getString("firstName"), 
										resSet.getString("lastName"), 
										resSet.getString("email"),
										resSet.getString("type"),
										resSet.getInt   ("seniority"),
										resSet.getString("passHash")
										);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getUser = "SELECT * FROM " + usersTable + " WHERE email = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getUser);
			statement.setString(1, email);
			resSet = statement.executeQuery();
			if (resSet.next()) {
				user = UserFactory.get(	resSet.getInt	("userId"), 
											resSet.getString("firstName"), 
											resSet.getString("lastName"), 
											resSet.getString("email"),
											resSet.getString("type"),
											resSet.getInt   ("seniority"),
											resSet.getString("passHash")
											);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resSet!=null) {
					resSet.close();
				}
				if (statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * @param userId the userId of the employee to be deleted.
	 */
	@Override
	public void deleteUser(int userId) {
	    String deleteEmpl = "DELETE FROM " + usersTable + " WHERE userId = ?";

	    try (Connection connection = DBConnection.getInstance().getConnection();
	         PreparedStatement statement = connection.prepareStatement(deleteEmpl)) {

	        statement.setInt(1, userId);
	        statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

	@Override
	public void updateUser(User user) {
		String updateUser = "UPDATE " + usersTable + " SET "
				+ "firstName = ?, "
				+ "lastName = ?, "
				+ "email = ?, "
				+ "type = ?, "
				+ "seniority = ?";
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(updateUser);
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getType().toString());
			statement.setInt   (5, user.getSeniority());
			
			statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

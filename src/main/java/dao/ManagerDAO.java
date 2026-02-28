package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import services.ManagerService;
import users.Employee;
import users.EmployeeFactory;
import users.Manager;

public class ManagerDAO implements ManagerService {

	private static final String managersTable = "managers";
	
	public ManagerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Generate a new Manager object, insert it into
	 * the DB, assign it an ID, then return the completed
	 * Manager.
	 * 
	 * @param firstName	Manager's first name. Max 50 characters
	 * @param lastName	Manager's last name. Max 50 characters
	 * @param email		Manager's email address
	 * @param passHash	the Manager's hashed password value
	 * @return			the fully constructed new Manager object
	 */
	@Override
	public Manager createManager(
			String firstName, 
			String lastName, 
			String email, 
			String passHash) {
		Manager man = new Manager.Builder()
								 .setFirstName(firstName)
								 .setLastName(lastName)
								 .setEmail(email)
								 .setPassHass(passHash)
								 .build();
		String insertNewManager = "INSERT INTO " + managersTable + " ("
				+ "firstName, lastName, email, seniority, passHash) "
				+ "VALUES (?,?,?,0,?)";
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		ResultSet		  generatedKeys = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(insertNewManager,
						Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, passHash);
			
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				int generatedUserID = generatedKeys.getInt(1);
				man.setUserId(generatedUserID);
			}
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
		return man;
	}

	@Override
	public Manager getManager(int userId) {
		Manager man = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getUser = "SELECT * FROM " + managersTable + " WHERE userId = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getUser);
			statement.setInt(1, userId);
			resSet = statement.executeQuery();
			if (resSet.next()) {
				String firstName = resSet.getString("firstName");
				String lastName  = resSet.getString("lastName");
				String email     = resSet.getString("email");
				int seniority    = resSet.getInt("seniority");
				String passHash  = resSet.getString("passHash");
				
				man = new Manager.Builder()
								 .setFirstName(firstName)
								 .setLastName(lastName)
								 .setEmail(email)
								 .setSeniority(seniority)
								 .setPassHass(passHash)
								 .build();
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
		return man;
	}

	@Override
	public Manager getManagerByEmail(String email) {
		Manager man = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getUser = "SELECT * FROM " + managersTable + " WHERE email = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getUser);
			statement.setString(1, email);
			resSet = statement.executeQuery();
			if (resSet.next()) {
				String firstName = resSet.getString("firstName");
				String lastName  = resSet.getString("lastName");
				int    seniority = resSet.getInt("seniority");
				String passHash  = resSet.getString("passHash");
				
				man = new Manager.Builder()
								 .setFirstName(firstName)
								 .setLastName(lastName)
								 .setEmail(email)
								 .setSeniority(seniority)
								 .setPassHass(passHash)
								 .build();
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
		return man;
	}

	@Override
	public void deleteManager(int userId) {
		String deleteManager = "DELETE FROM " + managersTable + " WHERE userId = ?";

	    try (Connection connection = DBConnection.getInstance().getConnection();
	         PreparedStatement statement = connection.prepareStatement(deleteManager)) {

	        statement.setInt(1, userId);
	        statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateManager(Manager manager) {
		String updateManager = "UPDATE " + managersTable + " SET "
				+ "firstName = ?, "
				+ "lastName = ?, "
				+ "email = ?, "
				+ "seniority = ?";
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(updateManager);
			
			statement.setString(1, manager.getFirstName());
			statement.setString(2, manager.getLastName());
			statement.setString(3, manager.getEmail());
			statement.setInt   (4, manager.getSeniority());
			
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

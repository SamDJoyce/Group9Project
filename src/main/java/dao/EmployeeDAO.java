package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import services.EmployeeService;
import users.Employee;
import users.EmployeeFactory;

/**
 * Implements EmployeeService for MySQL.
 * Mediates all Employee table interactions.
 * 
 * @author Sam Joyce
 */
public class EmployeeDAO implements EmployeeService {

	private static final String employeesTable = "employees";
	
	public EmployeeDAO() {
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
	 * @return			the fully constructed new Employee object
	 */
	@Override
	public Employee createEmployee(String firstName, 
								   String lastName, 
								   String email, 
								   String type,
								   String passHash) {
		Employee empl = EmployeeFactory.get(firstName, lastName, email, type, passHash);
		
		String insertNewEmployee = "INSERT INTO " + employeesTable + " ("
				+ "firstName, lastName, email, type, seniority) "
				+ "VALUES (?,?,?,?,0)";
		
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		ResultSet		  generatedKeys = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(insertNewEmployee,
						Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, type);
			
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				int generatedUserID = generatedKeys.getInt(1);
				empl.setUserId(generatedUserID);
			}
			return empl;
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
	public Employee getEmployee(int userId) {
		Employee empl = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resSet = null;
		String getUser = "SELECT * FROM " + employeesTable + " WHERE userId = ?";
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement  = connection.prepareStatement(getUser);
			statement.setInt(1, userId);
			resSet = statement.executeQuery();
			if (resSet.next()) {
				empl = EmployeeFactory.get(	resSet.getInt	("userId"), 
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
		return empl;
	}

	/**
	 * @param userId the userId of the employee to be deleted.
	 */
	@Override
	public void deleteEmployee(int userId) {
	    String deleteEmpl = "DELETE FROM " + employeesTable + " WHERE userId = ?";

	    try (Connection connection = DBConnection.getInstance().getConnection();
	         PreparedStatement statement = connection.prepareStatement(deleteEmpl)) {

	        statement.setInt(1, userId);
	        statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

	@Override
	public void updateEmployee(Employee employee) {
		String updateEmployee = "UPDATE " + employeesTable + " SET "
				+ "firstName = ?, "
				+ "lastName = ?, "
				+ "email = ?, "
				+ "type = ?, "
				+ "seniority = ?";
		Connection 		  connection    = null;
		PreparedStatement statement     = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(updateEmployee);
			
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getEmail());
			statement.setString(4, employee.getType().toString());
			statement.setInt   (5, employee.getSeniority());
			
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

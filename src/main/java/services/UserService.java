package services;

import users.Employee;
import users.User;

/**
 * Interface defining the available interactions with
 * a database for Employees
 * 
 * @author Sam Joyce
 */
public interface UserService {

	/**
	 * Create an Employee object and insert it
	 * into the DB.
	 * 
	 * @param name	employee's name
	 * @param email	employee's contact email
	 * @param type	employee type (FullTime, PartTime, Casual)
	 * @return		the new Employee object
	 */
	User createUser( String firstName,
						 String lastName,
						 String email,
						 String type,
						 String passHash);
	
	/**
	 * Retrieves an Employee from the DB
	 * then construct and return the object.
	 * 
	 * @param userId	Employee's userId
	 * @return			the Employee object
	 */
	User getUser(int userId);
	
	/**
	 * Retrieves an Employee from the DB
	 * then construct and return the object.
	 * 
	 * @param email		Employee's email address
	 * @return			the Employee object
	 */
	User getUserByEmail(String email);
	
	/**
	 * @param userId	Employee's userId
	 */
	void deleteUser(int userId);
	
	/**
	 * @param employee	object with new, unsaved
	 * 					Employee information.
	 */
	void updateUser(User user);
}

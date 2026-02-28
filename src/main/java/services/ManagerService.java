package services;

import users.Manager;

public interface ManagerService {
	/**
	 * Create an Employee object and insert it
	 * into the DB.
	 * 
	 * @param name	employee's name
	 * @param email	employee's contact email
	 * @param type	employee type (FullTime, PartTime, Casual)
	 * @return		the new Employee object
	 */
	Manager createManager( String firstName,
							 String lastName,
							 String email,
							 String passHash);
	
	/**
	 * Retrieves an Employee from the DB
	 * then construct and return the object.
	 * 
	 * @param userId	Employee's userId
	 * @return			the Employee object
	 */
	Manager getManager(int userId);
	
	/**
	 * Retrieves an Employee from the DB
	 * then construct and return the object.
	 * 
	 * @param email		Employee's email address
	 * @return			the Employee object
	 */
	Manager getManagerByEmail(String email);
	
	/**
	 * @param userId	Employee's userId
	 */
	void deleteManager(int userId);
	
	/**
	 * @param employee	object with new, unsaved
	 * 					Employee information.
	 */
	void updateManager(Manager manager);
}

package services;

import users.Employee;

/**
 * Interface defining the available interactions with
 * a database for Employees
 * 
 * @author Sam Joyce
 */
public interface EmployeeService {

	/**
	 * Create an Employee object and insert it
	 * into the DB.
	 * 
	 * @param name	employee's name
	 * @param email	employee's contact email
	 * @param type	employee type (FullTime, PartTime, Casual)
	 * @return		the new Employee object
	 */
	Employee createEmployee( String firstName,
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
	Employee getEmployee(int userId);
	
	/**
	 * Retrieves an Employee from the DB
	 * then construct and return the object.
	 * 
	 * @param email		Employee's email address
	 * @return			the Employee object
	 */
	Employee getEmployeeByEmail(String email);
	
	/**
	 * @param userId	Employee's userId
	 */
	void deleteEmployee(int userId);
	
	/**
	 * @param employee	object with new, unsaved
	 * 					Employee information.
	 */
	void updateEmployee(Employee employee);
}

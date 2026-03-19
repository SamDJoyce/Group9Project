package users;

import shiftEligibility.*;
/**
 * Create employees through a single uniform interface
 * Either create a new hire employee with no seniority
 * accrued, or construct an Employee object using all
 * details.
 * 
 * @author Sam Joyce
 */
public class EmployeeFactory {

	private EmployeeFactory() {
	}
	
	/**
	 * Create an Employee object
	 * 
	 * @param userId	The employee's system ID
	 * @param name		The employee's name
	 * @param email		The employee's email address
	 * @param type		Whether the employee is Full time,
	 * 					Part time, Casual
	 * @param seniority Total time worked, in minutes
	 * @return			An employee object.
	 */
	public static Employee get(	int    userId,
								String firstName,
								String lastName,
								String email,
								String type,
								int	   seniority,
								String passHash
								) {
		
			return new Employee.Builder()
							   .setUserId(userId)
							   .setFirstName(firstName)
							   .setLastName(lastName)
							   .setEmail(email)
							   .setType(EmployeeType.fromString(type))
							   .setSeniority(seniority)
							   .setEligibility(EligFactory.get(type))
							   .setPassHash(passHash)
							   .build();
	}
	

	/**
	 * Create a 'new hire' employee without an
	 * assigned userId and 0 seniority.
	 * 
	 * @param name	The employee's name
	 * @param email	The employee's email address
	 * @param type	Whether the employee is Full time, Part time, Casual
	 * @return		A new hire employee.
	 */
	public static Employee get(	String firstName,
								String lastName,
								String email,
								String type,
								String passHash
								) {
			return new Employee.Builder()
					   		   .setFirstName(firstName)
					   		   .setLastName(lastName)
							   .setEmail(email)
							   .setType(EmployeeType.fromString(type))
							   .setSeniority(0)
							   .setEligibility(EligFactory.get(type))
							   .setPassHash(passHash)
							   .build();
	}
}

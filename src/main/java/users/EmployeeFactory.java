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

	private static final String CASUAL    = "casual";
	private static final String PART_TIME = "parttime";
	private static final String FULL_TIME = "fulltime";

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
	 * @param seniority Total lifetime hours worked
	 * @return			An employee object.
	 */
	public static Employee get(	int    userId,
								String firstName,
								String lastName,
								String email,
								String type,
								Long   seniority
								) {
		if (FULL_TIME.equalsIgnoreCase(type)) {
			return new Employee.Builder()
							   .setUserId(userId)
							   .setFirstName(firstName)
							   .setLastName(lastName)
							   .setEmail(email)
							   .setType(EmployeeType.FULL_TIME)
							   .setSeniority(seniority)
							   .setEligibility(new FullTimeEligibility())
							   .build();
		}
		if (PART_TIME.equalsIgnoreCase(type)) {
			return new Employee.Builder()
					 		   .setUserId(userId)
							   .setFirstName(firstName)
							   .setLastName(lastName)
			 				   .setEmail(email)
			 				   .setType(EmployeeType.PART_TIME)
			 				   .setSeniority(seniority)
							   .setEligibility(new PartTimeEligibility())
			 				   .build();
		}
		if (CASUAL.equalsIgnoreCase(type)) {
			return new Employee.Builder()
							   .setUserId(userId)
							   .setFirstName(firstName)
							   .setLastName(lastName)
							   .setEmail(email)
							   .setType(EmployeeType.CASUAL)
							   .setSeniority(seniority)
							   .setEligibility(new CasualEligibility())
							   .build();
		}
		return null;
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
								String type
								) {
		if (FULL_TIME.equalsIgnoreCase(type)) {
			return new Employee.Builder()
					   		   .setFirstName(firstName)
					   		   .setLastName(lastName)
							   .setEmail(email)
							   .setType(EmployeeType.FULL_TIME)
							   .setSeniority(0L)
							   .setEligibility(new FullTimeEligibility())
							   .build();
		}
		if (PART_TIME.equalsIgnoreCase(type)) {
			return new Employee.Builder()
					   		   .setFirstName(firstName)
					   		   .setLastName(lastName)
			 				   .setEmail(email)
			 				   .setType(EmployeeType.PART_TIME)
			 				   .setSeniority(0L)
							   .setEligibility(new PartTimeEligibility())
			 				   .build();
		}
		if (CASUAL.equalsIgnoreCase(type)) {
			return new Employee.Builder()
					   		   .setFirstName(firstName)
					   		   .setLastName(lastName)
							   .setEmail(email)
							   .setType(EmployeeType.CASUAL)
							   .setSeniority(0L)
							   .setEligibility(new CasualEligibility())
							   .build();
		}
		return null;
	}

	

}

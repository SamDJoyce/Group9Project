package users;

import shiftEligibility.EligFactory;
/**
 * Create users through a single uniform interface
 * Either create a new hire user with no seniority
 * accrued, or construct an user object using all
 * details.
 * 
 * @author Sam Joyce
 */
public class UserFactory {

	private static final String MANAGER = "manager";

	/**
	 * Create an User object
	 * 
	 * @param userId	The user's system ID
	 * @param firstName	The user's first name
	 * @param lastName	The user's last name
	 * @param email		The user's email address
	 * @param type		Whether the user is Manager, 
	 * 					Full time, Part time, Casual
	 * @param seniority Total time worked, in minutes
	 * @param passHash	The user's hashed password
	 * @return			A User object.
	 */
	private UserFactory() {
	}
	
	public static User get(
			int    userId,
			String firstName,
			String lastName,
			String email,
			String type,
			int	   seniority,
			String passHash) {
		
		if (MANAGER.equalsIgnoreCase(type)) {
			return new Manager.Builder()
						.setUserId(userId)
						.setFirstName(firstName)
						.setLastName(lastName)
						.setEmail(email)
						.setSeniority(seniority)
						.setPassHash(passHash)
						.build();
		} else {
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
	}
	
	/**
	 * Create a 'new hire' user without an
	 * assigned userId and 0 seniority.
	 * 
	 * @param firstName	The user's first name
	 * @param lastName	The user's last name
	 * @param email		The user's email address
	 * @param type		Whether the user is Manager, Full time, Part time, Casual
	 * @param passHash	The user's hashed password
	 * @return			A new hire user.
	 */
	public static User get(
			String firstName,
			String lastName,
			String email,
			String type,
			String passHash) {
		
		if (MANAGER.equalsIgnoreCase(type)) {
			return new Manager.Builder()
						.setFirstName(firstName)
						.setLastName(lastName)
						.setEmail(email)
						.setSeniority(0)
						.setPassHash(passHash)
						.build();
		} else {
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

}

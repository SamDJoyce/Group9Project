package users;

public class UserFactory {

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
		
		if ("manager".equalsIgnoreCase(type)) {
			return new Manager.Builder()
					.setUserId(userId)
					.setFirstName(firstName)
					.setLastName(lastName)
					.setEmail(email)
					.setSeniority(seniority)
					.setPassHash(passHash)
					.build();
		} else {
			return EmployeeFactory.get(
					userId,
					firstName, 
					lastName, 
					email, 
					type, 
					seniority, 
					passHash
					);
		}
	}
	
	public static User get(
			String firstName,
			String lastName,
			String email,
			String type,
			String passHash) {
		
		if ("manager".equalsIgnoreCase(type)) {
			return new Manager.Builder()
					.setFirstName(firstName)
					.setLastName(lastName)
					.setEmail(email)
					.setSeniority(0)
					.setPassHash(passHash)
					.build();
		} else {
			return EmployeeFactory.get(
					firstName, 
					lastName, 
					email, 
					type,
					passHash
					);
		}
	}

}

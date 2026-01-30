package users;
/**
 * The User class forms the base for
 * Employees and Supervisors
 * 
 * @author Sam Joyce
 */
public abstract class User {

	protected User() {
	}

	
	// Fields
	protected int 	 userId;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String passHash;
	
	// Abstract Methods
	public abstract Boolean isSupervisor();
	
	// Methods
	
	public void login() {
		return;
	}
	
	public void logout() {
		return;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	// Builder
	public static abstract class Builder<T extends Builder<T>> {
		protected int 	 userId;
		protected String firstName;
		protected String lastName;
		protected String email;
		protected String passHash;
		
		// Type agnostic Setters

		public T setUserId(int userId) {
			this.userId = userId;
			return self();
		}
		
		public T setFirstName(String firstName) {
			this.firstName = firstName;
			return self();
		}
		
		public T setLastName(String lastName) {
			this.lastName = lastName;
			return self();
		}
		
		public T setEmail(String email) {
			this.email = email;
			return self();
		}
		
		public T setPassHass(String passHash) {
			this.passHash = passHash;
			return self();
		}
		
		protected abstract T self();
		public abstract User build();
	}
	
}

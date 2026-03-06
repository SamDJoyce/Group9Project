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
	protected int 	 seniority;
	protected String passHash;
	
	// Abstract Methods
	public abstract Boolean isManager();

	public abstract String getType();
	
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
	
	public int getSeniority() {
		return seniority;
	}

	/**
	 * @param seniority employee's total time worked, in minutes
	 */
	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}
	
	/**
	 * @param 	TimeWorked adds time worked to an employee's total.
	 * 			Stored in minutes
	 * @return	new total seniority
	 */
	public int addSeniority(int timeWorked) {
		return seniority += timeWorked;
	}
	
	public int removeSeniority(int time) {
		return seniority -= time;
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
		protected int 	 seniority;
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
		
		public T setSeniority(int seniority) {
			this.seniority = seniority;
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

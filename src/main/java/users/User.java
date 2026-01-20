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
	protected String name;
	protected String email;
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Builder
	public static abstract class Builder<T extends Builder<T>> {
		protected int 	 userId;
		protected String name;
		protected String email;
		
		// Type agnostic Setters

		public T setUserId(int userId) {
			this.userId = userId;
			return self();
		}
		
		public T setName(String name) {
			this.name = name;
			return self();
		}
		
		public T setEmail(String email) {
			this.email = email;
			return self();
		}
		
		protected abstract T self();
		public abstract User build();
	}
	
}

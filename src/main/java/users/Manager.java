package users;


public class Manager extends User {

	public Manager() {
	}

	public Boolean isManager() {
		return true;
	}
	
	public String getType() {
		return "Manager";
	}
	
	public static class Builder extends User.Builder<Builder>{
		
		@Override
		public Manager build() {
			Manager m = new Manager();
			
			m.userId = userId;
			m.firstName = firstName;
			m.lastName  = lastName;
			m.email = email;
			m.seniority = seniority;
			m.passHash = passHash;
			
			return m;
		}
		
		@Override
		protected Builder self() {
			return this;
		}

	}
}

package users;


public class Supervisor extends User {

	public Supervisor() {
	}

	public Boolean isSupervisor() {
		return true;
	}
	
	public static class Builder extends User.Builder<Builder>{

		
		@Override
		public User build() {
			Supervisor s = new Supervisor();
			
			s.userId = userId;
			s.firstName = firstName;
			s.email = email;
			s.passHash = passHash;
			
			return s;
		}
		
		@Override
		protected Builder self() {
			return this;
		}

	}
}

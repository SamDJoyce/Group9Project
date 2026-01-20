package users;

public class CasualEmployee extends Employee {

	private CasualEmployee() {
	}

	// Builder
	
	public static class Builder extends Employee.Builder<Builder>{
		
		public CasualEmployee build() {
			CasualEmployee c = new CasualEmployee();
			
			c.userId	= userId;
			c.name	  	= name;
			c.email 	= email;
			c.type      = type;
			c.seniority = seniority;
			c.elStrat   = elStrat;
			
			return c;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
	}
	
}


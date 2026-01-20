package users;

public class PartTimeEmployee extends Employee {

	private PartTimeEmployee() {
	}

	// Builder
	
	public static class Builder extends Employee.Builder<Builder>{
		
		@Override
		public PartTimeEmployee build() {
			PartTimeEmployee pt = new PartTimeEmployee();
			
			pt.userId	 = userId;
			pt.name	  	 = name;
			pt.email 	 = email;
			pt.type      = type;
			pt.seniority = seniority;
			pt.elStrat   = elStrat;
			
			return pt;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
	}
	
}

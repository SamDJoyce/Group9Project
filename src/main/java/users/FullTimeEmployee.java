package users;

public class FullTimeEmployee extends Employee {

	private FullTimeEmployee() {
	}

	// Builder
	
	public static class Builder extends Employee.Builder<Builder>{
		
		@Override
		public FullTimeEmployee build() {
			FullTimeEmployee ft = new FullTimeEmployee();
			
			ft.userId	 = userId;
			ft.name	  	 = name;
			ft.email 	 = email;
			ft.type      = type;
			ft.seniority = seniority;
			ft.elStrat   = elStrat;
			
			return ft;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
	}
	
}

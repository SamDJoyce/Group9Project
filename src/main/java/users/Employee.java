package users;

import shiftEligibility.ShiftEligibilityStrat;

/**
 * Employee forms the base class for the different
 * employee types: Full Time, Part Time, and Casual
 * 
 * @author Sam Joyce
 */ 
public class Employee extends User {

	// Fields
	protected EmployeeType type;
	/**
	 * Stored as minutes for precision
	 */
	protected int seniority;
	protected ShiftEligibilityStrat eligibility;
	
	// Constructors
	protected Employee() {
	}

	// Methods
	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
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
	
	public Boolean isFullTime() {
		return type.isFullTime();
	}
	
	public Boolean isPartTime() {
		return type.isPartTime();
	}
	
	public Boolean isCasual() {
		return type.isCasual();
	}
	
	public Boolean isType(EmployeeType type) {
		return type.isType(type);
	}
	
	public Boolean isSupervisor() {
		return false;
	}
	
	// Builder
	public static class Builder extends User.Builder<Builder> {
		protected EmployeeType type;
		protected int 		   seniority;
		protected ShiftEligibilityStrat eligibility;
		
		public Builder setType(EmployeeType type) {
			this.type = type;
			return self();
		}
		public Builder setSeniority(int seniority) {
			this.seniority = seniority;
			return self();
		}
		public Builder setEligibility(ShiftEligibilityStrat eligibility) {
			this.eligibility = eligibility;
			return self();
		}
		
		@Override
		public Employee build() {
			Employee e = new Employee();
			e.userId	  = userId;
			e.firstName	  = firstName;
			e.lastName    = lastName;
			e.email 	  = email;
			e.type        = type;
			e.seniority   = seniority;
			e.eligibility = eligibility;
			e.passHash	  = passHash;
			return e;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
		
	}
		

}

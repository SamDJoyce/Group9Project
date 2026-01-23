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
	protected Long 		   seniority;
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

	public Long getSeniority() {
		return seniority;
	}

	public void setSeniority(Long seniorityPoints) {
		this.seniority = seniorityPoints;
	}
	
	public Long addSeniority(Long hoursWorked) {
		return seniority += hoursWorked;
	}
	
	public Long removeSeniority(Long hours) {
		return seniority -= hours;
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
	
	// Builder
	public static class Builder extends User.Builder<Builder> {
		protected EmployeeType type;
		protected Long 		   seniority;
		protected ShiftEligibilityStrat eligibility;
		
		public Builder setType(EmployeeType type) {
			this.type = type;
			return self();
		}
		public Builder setSeniority(Long seniority) {
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
			return e;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
		
	}
		

}

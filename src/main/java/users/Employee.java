package users;

import shiftEligibility.ShiftEligibilityStrat;

/**
 * Employee forms the base class for the different
 * employee types: Full Time, Part Time, and Casual
 * 
 * @author Sam Joyce
 */
public abstract class Employee extends User {

	// Fields
	protected EmployeeType type;
	protected Long 		   seniority;
	protected ShiftEligibilityStrat elStrat;
	
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
	public abstract static class Builder<T extends Builder<T>>
     										extends User.Builder<T> {
		protected EmployeeType type;
		protected Long 		   seniority;
		protected ShiftEligibilityStrat elStrat;
		
		public T setType(EmployeeType type) {
			this.type = type;
			return self();
		}
		public T setSeniority(Long seniority) {
			this.seniority = seniority;
			return self();
		}
		public T setElStrat(ShiftEligibilityStrat elStrat) {
			this.elStrat = elStrat;
			return self();
		}
		
		
		
	}

}

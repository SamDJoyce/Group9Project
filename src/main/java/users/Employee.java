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
	protected int 		   seniority;
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

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniorityPoints) {
		this.seniority = seniorityPoints;
	}
	
	// Builder
	public abstract static class Builder<T extends Builder<T>>
     										extends User.Builder<T> {
		protected EmployeeType type;
		protected int 		   seniority;
		protected ShiftEligibilityStrat elStrat;
		
		public T setType(EmployeeType type) {
			this.type = type;
			return self();
		}
		public T setSeniority(int seniority) {
			this.seniority = seniority;
			return self();
		}
		public T setElStrat(ShiftEligibilityStrat elStrat) {
			this.elStrat = elStrat;
			return self();
		}
		
		
		
	}

}

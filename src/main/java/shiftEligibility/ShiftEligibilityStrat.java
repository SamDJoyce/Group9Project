package shiftEligibility;

import shifts.Shift;
import users.Employee;

public interface ShiftEligibilityStrat {
	
	public Boolean canSelectShift(Employee employee, Shift shift);
	
}

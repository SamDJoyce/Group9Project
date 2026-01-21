package shiftEligibility;

import shifts.Shift;
import users.Employee;

public class CasualEligibility implements ShiftEligibilityStrat {

	public CasualEligibility() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean canSelectShift(Employee employee, Shift shift) {
		// TODO Auto-generated method stub
		// Casual workers have no minimum hours,
		// and can only be assigned a maximum of
		// 25 hours per week.
		// Shifts can be between 1 and 8 hours.
		return null;
	}

}

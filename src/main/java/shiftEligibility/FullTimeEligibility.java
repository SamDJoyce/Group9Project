package shiftEligibility;

import shifts.Shift;
import users.Employee;

public class FullTimeEligibility implements ShiftEligibilityStrat {

	public FullTimeEligibility() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean canSelectShift(Employee employee, Shift shift) {
		// TODO Auto-generated method stub
		// Must work 40 hours per week
		// 8 hour shifts
		return null;
	}

}

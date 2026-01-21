package shiftEligibility;

import shifts.Shift;
import users.Employee;

public class PartTimeEligibility implements ShiftEligibilityStrat {

	public PartTimeEligibility() {
	}

	@Override
	public Boolean canSelectShift(Employee employee, Shift shift) {
		// TODO Auto-generated method stub
		// Part time workers must have at least 25 hours,
		// but no more than 40 hours per week.
		// Can be assigned shifts of no less than 3 hours,
		// and no more than 8 hours.
		return null;
	}

}

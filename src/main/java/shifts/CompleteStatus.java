package shifts;

import users.Employee;

public class CompleteStatus implements ShiftStatus {

	private static final String CANNOT_CHANGE = "Cannot change a completed shift.";

	public CompleteStatus() {
	}

	@Override
	public void assignEmployee(Shift shift, Employee employee) {
		throw new IllegalStateException(CANNOT_CHANGE);
	}

	@Override
	public void cancel(Shift shift) {
		throw new IllegalStateException(CANNOT_CHANGE);
	}

	@Override
	public void complete(Shift shift) {
		return;
	}



}

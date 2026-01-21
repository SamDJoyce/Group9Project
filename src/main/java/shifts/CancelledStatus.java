package shifts;

import users.Employee;

public class CancelledStatus implements ShiftStatus {

	private static final String CANNOT_COMPLETE = "Cannot complete a cancelled shift.";
	private static final String CANNOT_ASSIGN = "Cannot assign an employee to a cancelled shift.";

	public CancelledStatus() {
	}

	@Override
	public void assignEmployee(Shift shift, Employee employee) {
		throw new IllegalStateException(CANNOT_ASSIGN);
	}

	@Override
	public void cancel(Shift shift) {
		return;
	}

	@Override
	public void complete(Shift shift) {
		throw new IllegalStateException(CANNOT_COMPLETE);
	}


}

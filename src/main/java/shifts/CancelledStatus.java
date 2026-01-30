package shifts;

import users.Employee;

public class CancelledStatus implements ShiftStatus {

	private static final String CANNOT_OPEN = "Cannot open a cancelled shift.";
	private static final String CANNOT_COMPLETE = "Cannot complete a cancelled shift.";
	private static final String CANNOT_ASSIGN = "Cannot assign an employee to a cancelled shift.";
	private static final String CANCELLED = "cancelled";
	
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
	
	@Override
	public void open(Shift shift) {
		throw new IllegalStateException(CANNOT_OPEN);
	}
	
	@Override
	public String toString() {
		return CANCELLED;
	}

}

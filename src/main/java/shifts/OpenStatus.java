package shifts;

import users.Employee;

public class OpenStatus implements ShiftStatus {

	private static final String OPEN = "open";
	private static final String CANNOT_COMPLETE = "Cannot complete an open shift.";
	private static final String ASSIGNED  = "assigned";
	private static final String CANCELLED = "cancelled";

	public OpenStatus() {
		// TODO Send notifications
	}

	@Override
	public void assignEmployee(Shift shift, Employee employee) {
		shift.assignEmployee(employee);
		shift.setStatus(ShiftStatusFactory.get(ASSIGNED));
		// TODO Send notifications
	}

	@Override
	public void cancel(Shift shift) {
		shift.setStatus(ShiftStatusFactory.get(CANCELLED));
	}

	@Override
	public void complete(Shift shift) {
		throw new IllegalStateException(CANNOT_COMPLETE);
	}
	
	@Override
	public String toString() {
		return OPEN;
	}

}

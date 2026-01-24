package shifts;

import java.time.LocalDateTime;

import users.Employee;

/**
 * Indicates a shift is in the 'Assigned' state.
 * Contains methods for valid state changes.
 * 
 * @author Sam Joyce
 */
public class AssignedStatus implements ShiftStatus {

	private static final String EMPLOYEE_ALREADY_ASSIGNED = "An employee is already assigned to this shift.";
	private static final String CANCELLED = "cancelled";
	private static final String COMPLETED = "completed";
	private static final String ASSIGNED  = "assigned";
	
	public AssignedStatus() {
	}

	@Override
	public void assignEmployee(Shift shift, Employee employee) {
		throw new IllegalStateException(EMPLOYEE_ALREADY_ASSIGNED);
	}

	@Override
	public void cancel(Shift shift) {
		shift.setStatus(ShiftStatusFactory.get(CANCELLED));
		shift.setEnd(LocalDateTime.now());
		shift.getEmployee().addSeniority(Math.toIntExact(shift.getDuration().toMinutes()));
		// TODO Send notification
	}

	@Override
	public void complete(Shift shift) {
		shift.setStatus(ShiftStatusFactory.get(COMPLETED));
		shift.getEmployee().addSeniority(Math.toIntExact(shift.getDuration().toMinutes()));
		// TODO Send notification
	}

	@Override
	public String toString() {
		return ASSIGNED;
	}

}

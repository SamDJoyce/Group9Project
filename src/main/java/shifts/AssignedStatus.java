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
	private static final String OPEN	  = "open";
	
	public AssignedStatus() {
	}

	@Override
	public void assignEmployee(Shift shift, Employee employee) {
		throw new IllegalStateException(EMPLOYEE_ALREADY_ASSIGNED);
	}

	@Override
	public void cancel(Shift shift) {
		LocalDateTime time = LocalDateTime.now();
		shift.setStatus(ShiftStatusFactory.get(CANCELLED));
		
		if (shift.isAssigned() && time.isAfter(shift.getStart())) {
			shift.setEnd(time);
			shift.getEmployee().addSeniority(Math.toIntExact(shift.getDuration().toMinutes()));
			// TODO Send notification
		}
	}

	@Override
	public void complete(Shift shift) {
		shift.setStatus(ShiftStatusFactory.get(COMPLETED));
		LocalDateTime time = LocalDateTime.now();
		
		if (shift.isAssigned() && time.isAfter(shift.getStart())) {
			shift.getEmployee().addSeniority(Math.toIntExact(shift.getDuration().toMinutes()));
			// TODO Send notification
		}
	}
	
	@Override
	public void open(Shift shift) {
		shift.clearEmployee();
		shift.setStatus(ShiftStatusFactory.get(OPEN));
		// TODO send notification to cleared employee
		// TODO send notification to newly assigned employee
	}

	@Override
	public String toString() {
		return ASSIGNED;
	}

}

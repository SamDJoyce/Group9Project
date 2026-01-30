package shifts;

import java.time.Duration;
import java.time.LocalDateTime;

import users.Employee;

/**
 * Contains the logic for a single shift,
 * including when it is, the employee 
 * assigned, and current status.
 * 
 * @author Sam Joyce
 */
public class Shift {
	
	private static final String DURATION_EX = "Shift end must be after shift start.";

	public Shift() {
	}
	// Fields
	private int 		  shiftId;
	private LocalDateTime start;
	private LocalDateTime end;
	private ShiftStatus   status;
	private Employee	  employee;
	
	// Getters/Setters
	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	/**
	 * @return time the shifts starts
	 */
	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		if (end != null && end.isBefore(start)) {
			throw new IllegalStateException(DURATION_EX);
		} else 
		this.start = start;
	}

	/**
	 * @return the time the shifts ends
	 */
	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		if (start != null && end.isBefore(start)) {
			throw new IllegalStateException(DURATION_EX);
		} else {
			this.end = end;
		}
	}

	/**
	 * @return 	the duration of the shift, aka the amount
	 * 			of time between start and end.
	 */
	public Duration getDuration() {
		if (end.isBefore(start)) {
			throw new IllegalStateException(DURATION_EX);
		} else {
			return Duration.between(start, end);
		}
	}

	public ShiftStatus getStatus() {
		return status;
	}

	public void setStatus(ShiftStatus status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the person assigned to work this shift
	 */
	public void assignEmployee(Employee employee) {
		this.employee = employee;
	}
	
	/**
	 * Set assigned employee to null.
	 */
	public void clearEmployee() {
		this.employee = null;
	}
	
	/**
	 * @return true if employee is not null
	 */
	public Boolean isAssigned() {
		return employee != null;
	}
	
	/**
	 * Builder for Shift objects.
	 */
	public static class Builder {
		
		private static final String OPEN 	 = "open";
		private static final String ASSIGNED = "assigned";
		
		private int 		  shiftId;
		private LocalDateTime start;
		private LocalDateTime end;
		private ShiftStatus   status;
		private Employee	  employee;
		
		
		public Builder setShiftId(int shiftId) {
			this.shiftId = shiftId;
			return this;
		}
		
		public Builder setStart(LocalDateTime start) {
			this.start = start;
			return this;
		}
		
		public Builder setEnd(LocalDateTime end) {
			this.end = end;
			return this;
		}
		
		public Builder setStatus(ShiftStatus status) {
			this.status = status;
			return this;
		}
		
		public Builder assignEmployee(Employee employee) {
			this.employee = employee;
			return this;
		}
		
		public Shift build() {
			Shift s = new Shift();
			
			s.shiftId  = shiftId;
			s.start    = start;
			s.end 	   = end;
			s.status   = status != null ? status : 
						 employee != null ? ShiftStatusFactory.get(ASSIGNED) :
						 ShiftStatusFactory.get(OPEN);
			s.employee = employee;
			
			return s;
		}
		
	}

}

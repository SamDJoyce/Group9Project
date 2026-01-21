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
	
	private static final String DURATION_EX = "Shift end must be later than shift start.";

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

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		if (end != null && end.isBefore(start)) {
			throw new IllegalStateException(DURATION_EX);
		} else 
		this.start = start;
	}

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

	public void assignEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public static class Builder {
		
		private static final String OPEN = "open";
		
		private int 		  shiftId;
		private LocalDateTime start;
		private LocalDateTime end;
		private ShiftStatus   status;
		private Employee	  employee;
		
		
		public void setShiftId(int shiftId) {
			this.shiftId = shiftId;
		}
		
		public void setStart(LocalDateTime start) {
			this.start = start;
		}
		
		public void setEnd(LocalDateTime end) {
			this.end = end;
		}
		
		public void setStatus(ShiftStatus status) {
			this.status = status;
		}
		
		public void assignEmployee(Employee employee) {
			this.employee = employee;
		}
		
		public Shift build() {
			Shift s = new Shift();
			
			s.shiftId  = shiftId;
			s.start    = start;
			s.end 	   = end;
			s.status   = status != null ? status : ShiftStatusFactory.get(OPEN);
			s.employee = employee;
			
			return s;
		}
		
	}

}

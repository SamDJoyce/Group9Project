package shifts;

import java.time.LocalDateTime;

import users.Employee;

public class Shift {
	
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
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
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

	public void setEmployee(Employee employee) {
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

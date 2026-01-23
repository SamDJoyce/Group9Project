package schedule;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import shifts.Shift;

/**
 * Contains a list of shifts for a given calendar date,
 * the begining and end of the working day, and the logic
 * to determine how many hours have to be covered.
 * 
 * @author Sam Joyce
 */

public class WorkDay {
	
	// Fields
	
	private static final String SHIFT_OVERLAPS = "New shift overlaps with an existing shift for this employee";
	private LocalDate     date;
	private LocalDateTime openTime;
	private LocalDateTime closeTime;
	private List<Shift>   shifts;
	
	// Constructor
	
	public WorkDay(	LocalDate     date,
					LocalDateTime openTime,
					LocalDateTime closeTime) {
		this.date 	   = date;
		this.openTime  = openTime;
		this.closeTime = closeTime;
		this.shifts	   = new ArrayList<Shift>();
	}
	
	public WorkDay(LocalDate date) {
		this.date 	   = date;
		this.openTime  = null;
		this.closeTime = null;
		this.shifts	   = new ArrayList<Shift>();
	}
	
	// Getter/Setters 
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDateTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(LocalDateTime openTime) {
		this.openTime = openTime;
	}

	public LocalDateTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(LocalDateTime closeTime) {
		this.closeTime = closeTime;
	}

	public List<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}
	
	// Methods
	
	/**
	 * Validate a shift to ensure it follows the scheduling rules
	 * 
	 * @param shift	the shift to be validated
	 * @return		true if the shift begin and end times are good,
	 * 				the shift is on the correct day, and the employee
	 * 				isn't double booked.
	 */
	public Boolean isValid(Shift shift) {
	    LocalDateTime start = shift.getStart();
	    LocalDateTime end   = shift.getEnd();
	    
	    // Validate shift times make sense
	    if (end.isBefore(start) || end.isEqual(start)) {
	        return false;
	    }
	    // Validate shift is within working hours
	    if (start.isBefore(openTime) || end.isAfter(closeTime)) {
	        return false;
	    }
	    // Validate shift is on this day
	    if (!start.toLocalDate().equals(date)) {
	        return false;
	    }
	    // Validate this shift doesn't double book an employee
	     if (isDoubleBooked(shift)) {
	    	 return false;
	     }
	    return true;
	}
	
	/**
	 * Validates, then adds the desired shift to the day's schedule
	 * 
	 * @param shift	the shift to be added
	 */
	public void addShift(Shift shift) {
		if (!isValid(shift)) {
			throw new IllegalArgumentException(SHIFT_OVERLAPS);
		}
		shifts.add(shift);
	}
	
	/**
	 * Get the total hours worked for a single employee,
	 * on a single day.
	 * 
	 * @param userId	employee's user ID
	 * @return			duration of time worked on this day
	 * 					(across one or more shifts)
	 */
	public Duration getHoursWorked(int userId) {
		Duration hours = Duration.ZERO;
		for (Shift s : shifts) {
			if (s.getEmployee().getUserId() == userId) {
				hours.plus(s.getDuration());
			}
		}
		return hours;
	}
	
	/**
	 * @return	the total combined hours worked
	 * 			for all employees on a given day.
	 */
	public Duration getTotalHoursWorked() {
		Duration hours = Duration.ZERO;
		for (Shift s : shifts) {
			hours.plus(s.getDuration());
		}
		return hours;
	}
	
	/**
	 * @return 	the amount of time for which shifts 
	 * 			must be scheduled for some minimum
	 * 			number of employees.
	 */
	public Duration getHoursToCover() {
		return Duration.between(openTime, closeTime);
	}
	
	// Helper Methods
	
	/**
	 * Checks the proposed shift against all other shifts
	 * that day to ensure they are not double booked.
	 * 
	 * @param shift	the new shift to check for double booking
	 * @return		true if there is another shift with
	 * 				the same employee assigned that overlaps	
	 */
	private Boolean isDoubleBooked(Shift shift) {
	    LocalDateTime start = shift.getStart();
	    LocalDateTime end   = shift.getEnd();
	    // Only check if an employee is assigned to the shift
		if (shift.isAssigned()) {
			// Check each existing shift
	    	for (Shift existing : shifts) {
	    		// Ignore existing unassigned shifts
	            if (!existing.isAssigned()) {
	                continue;
	            }
	            // Only examine shifts for the same employee
	            if (!existing.getEmployee()
	                         .equals(shift.getEmployee())) {
	                continue;
	            }
	            
	            LocalDateTime existingStart = existing.getStart();
	            LocalDateTime existingEnd   = existing.getEnd();
				// Check if the already assigned shift overlaps with new shift
	            return  start.isBefore(existingEnd) ||
	                    end.isAfter(existingStart);
	    	}
	    }
		return false;
	}
}

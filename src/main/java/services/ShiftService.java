package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import shifts.Shift;
import users.Employee;

/**
 * Interface defining the available interactions with
 * a database for Shifts.
 * 
 * @author Sam Joyce
 */
public interface ShiftService {
	
	/**
	 * Create a shift with no employee assigned,
	 * and insert into the DB.
	 * 
	 * @param start	time shift beings
	 * @param end	time shift ends
	 * @return		a new shift object
	 */
	Shift createShift(LocalDateTime start,
					  LocalDateTime end);
	
	/**
	 * Create a shift with an employee assigned.
	 * 
	 * @param start		time shift begins
	 * @param end		time shift ends
	 * @param employee	the person assigned to this shift
	 * @return			a new shift object
	 */
	Shift createShift(LocalDateTime start,
			 		  LocalDateTime end,
			 		  Employee      employee);
	
	/**
	 * @param shiftId of the shift to be deleted
	 */
	void deleteShift(int shiftId);
	
	/**
	 * @param shiftId	the id of the Shift to be retrieved.
	 * @return			the corresponding shift.
	 */
	Shift getShift(int shiftId); 
	
	/**
	 * @param date	the desired date.
	 * @return		a list of all shifts on that day.
	 */
	List<Shift> getShiftsByDay(LocalDate date);
	
	// TODO get employee's shifts for the day
	// TODO get employee's shifts for the week
	
	/**
	 * @param shift object containing new shift information
	 */
	void updateShift(Shift shift);
	
}

package schedule;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Aggregates information across the week,
 * such as total hours worked, and therefore
 * pay. Contains exactly 7 WorkDays
 * 
 * @author Sam Joyce
 */

public class WorkWeek {

	private LocalDate weekStart; // Monday
	private Map<LocalDate, WorkDay> week;
	
	public WorkWeek(LocalDate start) {
		
		// No matter what date is entered, it will be
		// pushed back to the previous Monday.
		this.weekStart = start.with(
						 TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		this.week = new LinkedHashMap<>();
		// Add 7 days to the week
		for (int i = 0; i < 7; i++) {
			LocalDate date = weekStart.plusDays(i);
			week.put(date, new WorkDay(date));
		}
	}
	
    /**
     * Sums an Employee's hours worked for a given 7 day period.
     * 
     * @param userId	id of Employee whose work hours are being retrieved
     * @return			total hours worked during this 7 day period
     */
    public Duration getTotalHoursWorked(int userId) {
        Duration totalHours = Duration.ZERO;
    	for (WorkDay day : week.values()) {
        	totalHours = totalHours.plus(day.getHoursWorked(userId));
        }
    	return totalHours;
    }
    
    /**
     * Sums the total hours worked for all employees for the given 
     * 7 day period.
     * 
     * @return	sum of hours worked by all employees
     */
    public Duration getTotalHoursWorked() {
    	Duration totalHours = Duration.ZERO;
    	for (WorkDay day : week.values()) {
        	totalHours = totalHours.plus(day.getTotalHoursWorked());
        }
    	return totalHours;
    }

}

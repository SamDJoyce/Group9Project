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
	
	/**
	 * The day of the week (Monday, Tuesday, etc.)
	 * that will be week[0]
	 */
	private static DayOfWeek FIRST_DAY = DayOfWeek.MONDAY;
	
	private LocalDate weekStart; // Monday
	private Map<LocalDate, WorkDay> week;
	
	public WorkWeek(LocalDate start) {
		// No matter what date is entered, it will be
		// pushed back to the previous day corresponding
		// to FIRST_DAY (Monday, Tuesday, Wednesday, etc.).
		this.weekStart = start.with(
						 TemporalAdjusters.previousOrSame(FIRST_DAY) );
		this.week = new LinkedHashMap<>();
		// Add 7 days to the week
		for (int i = 0; i < 7; i++) {
			LocalDate date = weekStart.plusDays(i);
			week.put(date, new WorkDay(date));
		}
	}
	
    /**
     * Sums an Employee's time worked for a given 7 day period.
     * 
     * @param userId	id of Employee whose work hours are being retrieved
     * @return			total time worked during this 7 day period
     */
    public Duration getTotalTimeWorked(int userId) {
        Duration totalTime = Duration.ZERO;
    	for (WorkDay day : week.values()) {
        	totalTime = totalTime.plus(day.getTimeWorked(userId));
        }
    	return totalTime;
    }
    
    /**
     * Sums the total hours worked for all employees for the given 
     * 7 day period.
     * 
     * @return	sum of hours worked by all employees
     */
    public Duration getTotalTimeWorked() {
    	Duration totalHours = Duration.ZERO;
    	for (WorkDay day : week.values()) {
        	totalHours = totalHours.plus(day.getTotalTimeWorked());
        }
    	return totalHours;
    }

}

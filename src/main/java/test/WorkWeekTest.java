package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import schedule.WorkDay;
import schedule.WorkWeek;
import shifts.Shift;
import users.Employee;
import users.EmployeeFactory;

class WorkWeekTest {

	WorkWeek week;
	LocalDate monday;
	Employee empl;
	Employee empl2;
	LocalDateTime dayStart;
	LocalDateTime dayEnd;
	
	@BeforeEach
	void setUp() throws Exception {
		monday = LocalDate.of(2026, 1, 19); // Monday
	    dayStart = monday.atTime(9,0);
	    dayEnd   = monday.atTime(21,0);
        empl = EmployeeFactory.get(
        		1, 
        		"Crash", 
        		"Testman", 
        		"cTestman@tests.net", 
        		"fullTime", 
        		0, 
        		"123456789" );
        empl2 = EmployeeFactory.get(
        		2, 
        		"Smash", 
        		"Testman",
        		"sTestman@tests.net", 
        		"partTime", 
        		0, 
        		"987654321" );
	}

	@Test
	void constructorShouldSetWeekStartToMonday() {
	    LocalDate wednesday = monday.plusDays(2); // Wednesday

	    week = new WorkWeek(wednesday);

	    LocalDate expectedMonday =
	        wednesday.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

	    assertEquals(expectedMonday, week.getWeekStart());
	}
	
	@Test
	void weekShouldContainExactlySevenSequentialDays() {
	    LocalDate tuesday = monday.plusDays(1); // Tuesday
	   week = new WorkWeek(tuesday);

	    Map<LocalDate, WorkDay> days = week.getWeek();

	    assertEquals(7, days.size());

	    LocalDate monday =
	    		tuesday.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

	    int i = 0;
	    for (LocalDate date : days.keySet()) {
	        assertEquals(monday.plusDays(i++), date);
	    }
	}
	
	@Test
	void totalTimeWorkedShouldBeZeroWhenNoWorkExists() {
		monday = LocalDate.of(2026, 1, 19);
	    WorkWeek week = new WorkWeek(LocalDate.of(2026, 1, 19));

	    assertEquals(Duration.ZERO, week.getTotalTimeWorked());
	    assertEquals(Duration.ZERO, week.getTotalTimeWorked(1));
	}
	
	@Test
	void shouldAggregateTimeWorkedForSpecificEmployee() {
	    WorkWeek week = new WorkWeek(monday);
	    Map<LocalDate, WorkDay> days = week.getWeek();
	    Shift shift;

	    int i = 0;
	    for (WorkDay day : days.values()) {
	    	day.setDayStart(dayStart.plusDays(i));
	    	day.setDayEnd(dayEnd.plusDays(i));
	    	
	    	shift = new Shift.Builder()
	    					 .setStart(monday.plusDays(i).atTime(11,0))
	    					 .setEnd(monday.plusDays(i).atTime(13,0))
	    					 .assignEmployee(empl)
	    					 .build();
	        day.addShift(shift);
	        i++;
	    }

	    Duration total = week.getTotalTimeWorked();

	    assertEquals(Duration.ofHours(14), total);
	}
	
	@Test
	void shouldAggregateTotalTimeWorkedAcrossAllEmployees() {
	    WorkWeek week = new WorkWeek(monday);
	    Map<LocalDate, WorkDay> days = week.getWeek();
	    
	    int i = 0;
	    for (WorkDay day : days.values()) {
	    	day.setDayStart(dayStart.plusDays(i));
	    	day.setDayEnd(dayEnd.plusDays(i));
	        day.addShift(new Shift.Builder()
					 .setStart(monday.plusDays(i).atTime(11,0))
					 .setEnd(monday.plusDays(i).atTime(13,0))
					 .assignEmployee(empl)
					 .build());
	        day.addShift(new Shift.Builder()
					 .setStart(monday.plusDays(i).atTime(11,0))
					 .setEnd(monday.plusDays(i).atTime(13,0))
					 .assignEmployee(empl2)
					 .build());
	        i++;
	    }

	    Duration total = week.getTotalTimeWorked();

	    assertEquals(Duration.ofHours(28), total);
	}
}

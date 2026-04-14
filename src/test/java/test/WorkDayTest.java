package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import schedule.WorkDay;
import shifts.Shift;
import users.Employee;
import users.UserFactory;

class WorkDayTest {

    private WorkDay workDay;
    private LocalDate date;
    private LocalDateTime dayStart;
    private LocalDateTime dayEnd;
    private Employee empl;
    private Employee empl2;
    private Shift shift;

    @BeforeEach
    void setUp() {
    	date = LocalDate.of(2026, 1, 20);
    	dayStart = date.atTime(8, 0);
    	dayEnd   = date.atTime(21, 0);
        workDay  = new WorkDay(date,
        					   dayStart,
        					   dayEnd);
        empl = (Employee) UserFactory.get(
				        		1, 
				        		"Crash", 
				        		"Testman", 
				        		"cTestman@tests.net", 
				        		"fullTime", 
				        		0, 
				        		"123456789" );
        empl2 = (Employee) UserFactory.get(
				        		2, 
				        		"Smash", 
				        		"Testman",
				        		"sTestman@tests.net", 
				        		"partTime", 
				        		0, 
				        		"987654321" );
        shift = new Shift();
    }
	
    @Test
    void validShiftWithinDayShouldBeAccepted() {
        shift = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(9, 0))
					   .setEnd(date.atTime(13, 0))
					   .build();
        assertTrue(workDay.isValid(shift));
    }
    
    @Test
    void shouldRejectOverlappingShiftForSameEmployee() {

    	Shift first = new Shift.Builder()
        		.setShiftId(1)
        		.setStart(date.atTime(9,0))
        		.setEnd(date.atTime(13,0))
        		.assignEmployee(empl)
        		.build();
        
        Shift overlapping = new Shift.Builder()
        		.setShiftId(1)
        		.setStart(date.atTime(12,0))
        		.setEnd(date.atTime(16, 0))
        		.assignEmployee(empl)
        		.build();

        workDay.addShift(first);

        assertThrows(
            IllegalArgumentException.class,
            () -> workDay.addShift(overlapping)
        );
    }
    
    @Test
    void shouldRejectShiftThatFullyOverlapsExisting() {
        Shift first = new Shift.Builder()
                .setStart(date.atTime(10, 0))
                .setEnd(date.atTime(12, 0))
                .assignEmployee(empl)
                .build();

        Shift covering = new Shift.Builder()
                .setStart(date.atTime(9, 0))
                .setEnd(date.atTime(13, 0))
                .assignEmployee(empl)
                .build();

        workDay.addShift(first);

        // ❗ This SHOULD fail logically, but currently may pass
        assertThrows(IllegalArgumentException.class,
                () -> workDay.addShift(covering));
    }
    
    @Test
    void overlappingShiftForDifferentEmployeesAreAllowed() {
    	Shift first = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(10, 0))
					   .setEnd(date.atTime(14, 0))
					   .assignEmployee(empl)
					   .build();
		Shift second = new Shift.Builder()
					   .setShiftId(2)
					   .setStart(date.atTime(10, 0))
					   .setEnd(date.atTime(14, 0))
					   .assignEmployee(empl2)
					   .build();
		workDay.addShift(first);
		workDay.addShift(second);
		
		assertEquals(2, workDay.getShifts().size());
    }
    
    @Test
    void shiftsThatTouchButDoNotOverlapAreAllowed() {
        Shift first = new Shift.Builder()
                .setStart(date.atTime(9, 0))
                .setEnd(date.atTime(12, 0))
                .assignEmployee(empl)
                .build();

        Shift second = new Shift.Builder()
                .setStart(date.atTime(12, 0)) // touches boundary
                .setEnd(date.atTime(15, 0))
                .assignEmployee(empl)
                .build();

        workDay.addShift(first);
        workDay.addShift(second);

        assertEquals(2, workDay.getShifts().size());
    }
    
    @Test
    void shiftWithEndBeforeStartIsInvalid() {
        Shift shift = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(13, 0))
					   .setEnd(date.atTime(9, 0))
					   .build();
        assertFalse(workDay.isValid(shift));
    }
    
    @Test
    void shiftOutsideWorkingHoursIsInvalid() {
        Shift shift = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(1, 0))
					   .setEnd(date.atTime(5, 0))
					   .build();
        assertFalse(workDay.isValid(shift));
    }
    
    @Test
    void shiftOnDifferentDateIsInvalid() {
    	Shift shift = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.plusDays(1).atTime(1, 0))
					   .setEnd(date.plusDays(1).atTime(5, 0))
					   .build();
    	assertFalse(workDay.isValid(shift));
    }
    
    @Test
    void unassignedShiftsDoNotCauseDoubleBooking() {
    	Shift first = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(10, 0))
					   .setEnd(date.atTime(14, 0))
					   .build();
		Shift second = new Shift.Builder()
					   .setShiftId(2)
					   .setStart(date.atTime(10, 0))
					   .setEnd(date.atTime(14, 0))
					   .assignEmployee(empl)
					   .build();
		workDay.addShift(first);
		workDay.addShift(second);
	
		assertEquals(2, workDay.getShifts().size());
    }
    
    @Test
    void shouldSumTimeWorkedForSingleEmployee() {
    	Shift first = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(9, 0))
					   .setEnd(date.atTime(11, 0))
					   .assignEmployee(empl)
					   .build();
		Shift second = new Shift.Builder()
					   .setShiftId(2)
					   .setStart(date.atTime(16, 0))
					   .setEnd(date.atTime(18, 0))
					   .assignEmployee(empl)
					   .build();
		workDay.addShift(first);
		workDay.addShift(second);
		
		Duration worked = workDay.getTimeWorked(1);
		assertEquals(Duration.ofHours(4), worked);
    }
    
    @Test
    void shouldSumTimeWorkedForAllEmployees() {
    	Shift first = new Shift.Builder()
					   .setShiftId(1)
					   .setStart(date.atTime(9, 0))
					   .setEnd(date.atTime(11, 0))
					   .assignEmployee(empl)
					   .build();
		Shift second = new Shift.Builder()
					   .setShiftId(2)
					   .setStart(date.atTime(12, 0))
					   .setEnd(date.atTime(14, 0))
					   .assignEmployee(empl2)
					   .build();
		workDay.addShift(first);
		workDay.addShift(second);
		
		Duration worked = workDay.getTotalTimeWorked();
		assertEquals(Duration.ofHours(4), worked);
    }
    
    @Test
    void getTimeWorkedReturnsZeroWhenNoShifts() {
        Duration worked = workDay.getTimeWorked(1);
        assertEquals(Duration.ZERO, worked);
    }
    
    @Test
    void getTotalTimeWorkedIgnoresUnassignedShifts() {
        Shift assigned = new Shift.Builder()
                .setStart(date.atTime(9, 0))
                .setEnd(date.atTime(11, 0))
                .assignEmployee(empl)
                .build();

        Shift unassigned = new Shift.Builder()
                .setStart(date.atTime(12, 0))
                .setEnd(date.atTime(14, 0))
                .build();

        workDay.addShift(assigned);
        workDay.addShift(unassigned);

        Duration worked = workDay.getTotalTimeWorked();

        assertEquals(Duration.ofHours(2), worked);
    }
    
    @Test
    void shouldCalculateTimeToCover() {
        Duration coverage = workDay.getTimeToCover();
        assertEquals(Duration.ofHours(13), coverage);
    }
    
    @Test
    void getTimeToCoverWithNullTimesThrows() {
        WorkDay wd = new WorkDay(date);

        assertThrows(NullPointerException.class, wd::getTimeToCover);
    }
    
    @Test
    void setDayStartWithWrongDateThrows() {
        WorkDay wd = new WorkDay(date);

        assertThrows(IllegalStateException.class, () ->
                wd.setDayStart(date.plusDays(1).atTime(8, 0))
        );
    }

    @Test
    void setDayEndWithWrongDateThrows() {
        WorkDay wd = new WorkDay(date);

        assertThrows(IllegalStateException.class, () ->
                wd.setDayEnd(date.plusDays(1).atTime(20, 0))
        );
    }
    
    @Test
    void isTodayReturnsTrueForSameDate() {
        assertTrue(workDay.isToday(date.atTime(10, 0)));
    }

    @Test
    void isTodayReturnsFalseForDifferentDate() {
        assertFalse(workDay.isToday(date.plusDays(1).atTime(10, 0)));
    }
    
    @Test
    void addShiftRejectsInvalidShiftOutsideHours() {
        Shift invalid = new Shift.Builder()
                .setStart(date.atTime(1, 0))
                .setEnd(date.atTime(2, 0))
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> workDay.addShift(invalid));
    }

}

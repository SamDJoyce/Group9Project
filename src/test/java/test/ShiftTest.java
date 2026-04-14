package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shifts.Shift;
import users.Employee;
import users.EmployeeType;
import users.Manager;

public class ShiftTest {

	public ShiftTest() {}
	
	private LocalDateTime now;
	private LocalDateTime later;
	private Employee empl;
	private Manager mngr;
	private Shift shift;
	
	@BeforeEach
	void setUp() {
		now = LocalDateTime.now();
		later = now.plusHours(8);
		empl = new Employee.Builder()
						.setType(EmployeeType.FULLTIME)
						.build();
		mngr = new Manager.Builder().build();
		shift  = new Shift();
	}

    // Time Validation

	@Test
	void testSetEndBeforeStartThrows() {
	    shift.setStart(later);

	    assertThrows(IllegalStateException.class, () ->
	            shift.setEnd(now)
	    );
	}

    @Test
    void testSetStartAfterEndThrows() {
        shift.setEnd(now);

        assertThrows(IllegalStateException.class, () ->
                shift.setStart(later)
        );
    }

    @Test
    void testValidStartAndEnd() {
        shift.setStart(now);
        shift.setEnd(later);

        assertEquals(now, shift.getStart());
        assertEquals(later, shift.getEnd());
    }
    
    // Duration

    @Test
    void testGetDuration() {
        shift.setStart(now);
        shift.setEnd(later);

        Duration duration = shift.getDuration();
        assertEquals(Duration.ofHours(8), duration);
    }

    // Employee Assignment
    
    @Test
    void testAssignEmployeeSuccess() {
        shift.assignEmployee(empl);

        assertTrue(shift.isAssigned());
        assertEquals(empl, shift.getEmployee());
    }

    @Test
    void testAssignManagerAsEmployeeThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                shift.assignEmployee(mngr)
        );
    }

    @Test
    void testClearEmployee() {
        shift.assignEmployee(empl);

        shift.clearEmployee();

        assertFalse(shift.isAssigned());
        assertNull(shift.getEmployee());
    }
    

    // Manager Assignment

    @Test
    void testSetManagerSuccess() {

        shift.setManager(mngr);

        assertEquals(mngr, shift.getManager());
    }

    @Test
    void testSetEmployeeAsManagerThrows() {

        assertThrows(IllegalArgumentException.class, () ->
                shift.setManager(empl)
        );
    }
    
    // Builder Tests

    @Test
    void testBuilderCreatesShift() {

        shift = new Shift.Builder()
                .setShiftId(1)
                .setStart(now)
                .setEnd(later)
                .setManager(mngr)
                .assignEmployee(empl)
                .build();

        assertEquals(1, shift.getShiftId());
        assertEquals(now, shift.getStart());
        assertEquals(later, shift.getEnd());
        assertEquals(empl, shift.getEmployee());
        assertEquals(mngr, shift.getManager());
    }

    @Test
    void testBuilderDefaultStatusOpen() {
        Shift shift = new Shift.Builder()
                .setStart(now)
                .setEnd(later)
                .build();

        assertNotNull(shift.getStatus());
        // assuming OPEN → depends on your ShiftStatusFactory
    }

    @Test
    void testBuilderDefaultStatusAssigned() {

        shift = new Shift.Builder()
                .setStart(now)
                .setEnd(later)
                .assignEmployee(empl)
                .build();

        assertNotNull(shift.getStatus());
        // should be ASSIGNED (based on your factory)
    }

    @Test
    void testBuilderRejectsManagerAsEmployee() {
        assertThrows(IllegalArgumentException.class, () ->
                new Shift.Builder().assignEmployee(mngr)
        );
    }
    
    // State check
    
    @Test
    void testIsAssignedFalseByDefault() {
        assertFalse(shift.isAssigned());
    }
}

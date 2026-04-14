package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shifts.OpenStatus;
import shifts.Shift;
import shifts.ShiftStatus;
import users.Employee;
import users.EmployeeType;

public class OpenStatusTest {

	public OpenStatusTest() {}
	
	private Employee empl;
	private Shift shift;
	
	@BeforeEach
    void setUp() {
		empl = new Employee.Builder()
                .setType(EmployeeType.FULLTIME)
                .build();
		shift = new Shift.Builder().build();
	}
	
    // assignEmployee()

    @Test
    void testAssignEmployeeChangesToAssigned() {
        OpenStatus status = new OpenStatus();

        status.assignEmployee(shift, empl);

        assertEquals("assigned", shift.getStatus().toString());
        assertEquals(empl, shift.getEmployee());
        assertTrue(shift.isAssigned());
    }

    // cancel()

    @Test
    void testCancelChangesToCancelled() {
        OpenStatus status = new OpenStatus();
        status.cancel(shift);

        assertEquals("cancelled", shift.getStatus().toString());
    }

    // open() (no-op)

    @Test
    void testOpenDoesNothing() {
        OpenStatus status = new OpenStatus();
        ShiftStatus original = shift.getStatus();

        status.open(shift);

        assertEquals(original, shift.getStatus());
    }

    // complete() (invalid)

    @Test
    void testCompleteThrows() {
        OpenStatus status = new OpenStatus();

        assertThrows(IllegalStateException.class, () ->
                status.complete(shift)
        );
    }

    // toString()

    @Test
    void testToString() {
        OpenStatus status = new OpenStatus();
        assertEquals("open", status.toString());
    }
}

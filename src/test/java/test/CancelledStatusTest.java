package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shifts.CancelledStatus;
import shifts.Shift;
import users.Employee;
import users.EmployeeType;

public class CancelledStatusTest {

	public CancelledStatusTest() {}

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
    void testAssignEmployeeThrows() {
        CancelledStatus status = new CancelledStatus();

        assertThrows(IllegalStateException.class, () ->
                status.assignEmployee(shift, empl)
        );
    }
    
    // complete()

    @Test
    void testCompleteThrows() {
        CancelledStatus status = new CancelledStatus();

        assertThrows(IllegalStateException.class, () ->
                status.complete(shift)
        );
    }
    
    // open()

    @Test
    void testOpenThrows() {
        CancelledStatus status = new CancelledStatus();

        assertThrows(IllegalStateException.class, () ->
                status.open(shift)
        );
    }

    // cancel()

    @Test
    void testCancelDoesNothing() {
        CancelledStatus status = new CancelledStatus();

        assertDoesNotThrow(() -> status.cancel(shift));
    }

    // toString()

    @Test
    void testToString() {
        CancelledStatus status = new CancelledStatus();
        assertEquals("cancelled", status.toString());
    }
}

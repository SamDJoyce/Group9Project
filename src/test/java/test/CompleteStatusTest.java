package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shifts.CompleteStatus;
import shifts.Shift;
import users.Employee;
import users.EmployeeType;

public class CompleteStatusTest {

	private Employee empl;
	private Shift shift;
	
	public CompleteStatusTest() {}
	
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
        CompleteStatus status = new CompleteStatus();

        assertThrows(IllegalStateException.class, () ->
                status.assignEmployee(shift, empl)
        );
    }

    // cancel()

    @Test
    void testCancelThrows() {
        CompleteStatus status = new CompleteStatus();

        assertThrows(IllegalStateException.class, () ->
                status.cancel(shift)
        );
    }

    // open()

    @Test
    void testOpenThrows() {
        CompleteStatus status = new CompleteStatus();

        assertThrows(IllegalStateException.class, () ->
                status.open(shift)
        );
    }

    // complete() (no-op)

    @Test
    void testCompleteDoesNothing() {
        CompleteStatus status = new CompleteStatus();
        shift.setStatus(status);
        assertDoesNotThrow(() -> status.complete(shift));
        
        status.complete(shift);
        //ensure state is unchanged
        assertEquals(status, shift.getStatus());
    }

    // toString()

    @Test
    void testToString() {
        CompleteStatus status = new CompleteStatus();
        assertEquals("complete", status.toString());
    }

}

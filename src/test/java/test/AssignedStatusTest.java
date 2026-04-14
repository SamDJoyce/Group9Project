package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shifts.AssignedStatus;
import shifts.Shift;
import shifts.ShiftStatusFactory;
import users.Employee;
import users.EmployeeType;

public class AssignedStatusTest {

	public AssignedStatusTest() {}
	
	private Employee empl;
	private Shift shift;
	private LocalDateTime start;
    private LocalDateTime end;
	
	@BeforeEach
    void setUp() {
		start = LocalDateTime.now().minusHours(2);
		end = start.plusHours(8);
		empl = new Employee.Builder()
                .setType(EmployeeType.FULLTIME)
                .setSeniority(0)
                .build();
		
		shift = new Shift.Builder()
                .setStart(start)
                .setEnd(end)
                .assignEmployee(empl)
                .setStatus(ShiftStatusFactory.get("assigned"))
                .build();
	}
	
    // assignEmployee()

    @Test
    void testAssignEmployeeThrows() {
        AssignedStatus status = new AssignedStatus();

        assertThrows(IllegalStateException.class, () ->
                status.assignEmployee(shift, empl)
        );
    }
    
    // cancel()

    @Test
    void testCancelChangesStatus() {
        AssignedStatus status = new AssignedStatus();
        status.cancel(shift);

        assertEquals("cancelled", shift.getStatus().toString());
    }
    
    @Test
    void testCancelAddsSeniorityIfStarted() {

        int before = empl.getSeniority();

        AssignedStatus status = new AssignedStatus();
        status.cancel(shift);

        int after = empl.getSeniority();

        assertTrue(after > before);
    }
	
    // complete()

    @Test
    void testCompleteChangesStatus() {
        AssignedStatus status = new AssignedStatus();
        status.complete(shift);

        assertEquals("complete", shift.getStatus().toString());
    }

    @Test
    void testCompleteAddsSeniority() {
        int before = empl.getSeniority();

        AssignedStatus status = new AssignedStatus();
        status.complete(shift);

        int after = empl.getSeniority();

        assertTrue(after > before);
    }
    
    // open()

    @Test
    void testOpenClearsEmployee() {
        AssignedStatus status = new AssignedStatus();
        status.open(shift);

        assertFalse(shift.isAssigned());
        assertNull(shift.getEmployee());
    }

    @Test
    void testOpenChangesStatus() {
        AssignedStatus status = new AssignedStatus();
        status.open(shift);

        assertEquals("open", shift.getStatus().toString());
    }
    
    // toString()

    @Test
    void testToString() {
        AssignedStatus status = new AssignedStatus();
        assertEquals("assigned", status.toString());
    }
}

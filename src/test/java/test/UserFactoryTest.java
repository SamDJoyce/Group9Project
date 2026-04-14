package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import users.Employee;
import users.EmployeeType;
import users.Manager;
import users.User;
import users.UserFactory;

public class UserFactoryTest {
	
	private User user;
	private Employee empl;
    private static final int USER_ID = 11;
    private static final String FIRST = "Adam";
    private static final String LAST = "Smith";
    private static final String EMAIL = "aSmith@email.com";
    private static final String PASS = "password123";
    private static final String TYPE = EmployeeType.FULLTIME.toString();
    private static final String MANAGER = "Manager";
    private static final int SENIORITY = 120;

	public UserFactoryTest() {}
	
	// Full Constructor
	@Test
    void testGetManagerFull() {
        user = UserFactory.get(
                USER_ID, FIRST, LAST, EMAIL,
                MANAGER, SENIORITY, PASS
        );

        assertTrue(user instanceof Manager);
        assertTrue(user.isManager());
        assertEquals(MANAGER, user.getType());

        assertEquals(USER_ID, user.getUserId());
        assertEquals(FIRST, user.getFirstName());
        assertEquals(LAST, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(SENIORITY, user.getSeniority());
        assertEquals(PASS, user.getPassHash());
    }

    @Test
    void testGetEmployeeFull() {
        user = UserFactory.get(
                USER_ID, FIRST, LAST, EMAIL,
               TYPE, SENIORITY, PASS
        );

        assertTrue(user instanceof Employee);
        assertFalse(user.isManager());

        empl = (Employee) user;

        assertEquals(TYPE, empl.getType());
        assertEquals(USER_ID, empl.getUserId());
        assertEquals(SENIORITY, empl.getSeniority());
    }
    
    // NEW HIRE

    @Test
    void testGetManagerNewHire() {
        user = UserFactory.get(
                FIRST, LAST, EMAIL,
                MANAGER, PASS
        );

        assertTrue(user instanceof Manager);
        assertTrue(user.isManager());

        assertEquals(0, user.getSeniority());
        assertEquals(0, user.getUserId()); // default int
    }

    @Test
    void testGetEmployeeNewHire() {
        user = UserFactory.get(
                FIRST, LAST, EMAIL,
                TYPE, PASS
        );

        assertTrue(user instanceof Employee);
        assertFalse(user.isManager());

        empl = (Employee) user;

        assertEquals(TYPE, empl.getType());
        assertEquals(0, empl.getSeniority());
    }

}

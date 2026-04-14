package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shiftEligibility.ShiftEligibilityStrat;
import users.Employee;
import users.EmployeeType;

public class EmployeeTest {

	public EmployeeTest() {	}
	
	@Test
    void testBuilderCreatesEmployeeCorrectly() {
        EmployeeType type = EmployeeType.FULLTIME;
        ShiftEligibilityStrat strat = null; // or mock if needed

        Employee emp = new Employee.Builder()
                .setUserId(1)
                .setFirstName("Sam")
                .setLastName("Joyce")
                .setEmail("sam@test.com")
                .setSeniority(120)
                .setPassHash("hash123")
                .setType(type)
                .setEligibility(strat)
                .build();

        assertEquals(1, emp.getUserId());
        assertEquals("Sam", emp.getFirstName());
        assertEquals("Joyce", emp.getLastName());
        assertEquals("Sam Joyce", emp.getFullName());
        assertEquals("sam@test.com", emp.getEmail());
        assertEquals(120, emp.getSeniority());
        assertEquals("hash123", emp.getPassHash());
        assertEquals(type.toString(), emp.getType());
    }
	
    @Test
    void testIsManagerAlwaysFalse() {
        Employee emp = new Employee.Builder().build();
        assertFalse(emp.isManager());
    }
    
    @Test
    void testEmployeeTypeChecks() {
        Employee emp = new Employee.Builder()
                .setType(EmployeeType.FULLTIME)
                .build();

        assertTrue(emp.isFullTime());
        assertFalse(emp.isPartTime());
        assertFalse(emp.isCasual());
    }
    
    @Test
    void testGetTypeReturnsCorrectString() {
        Employee emp = new Employee.Builder()
                .setType(EmployeeType.PARTTIME)
                .build();

        assertEquals("PARTTIME", emp.getType());
    }
    
    @Test
    void testSeniorityAddAndRemove() {
        Employee emp = new Employee.Builder()
                .setSeniority(100)
                .build();

        int newTotal = emp.addSeniority(50);
        assertEquals(150, newTotal);
        assertEquals(150, emp.getSeniority());

        int reduced = emp.removeSeniority(20);
        assertEquals(130, reduced);
        assertEquals(130, emp.getSeniority());
    }
    
    @Test
    void testIsTypeMethod() {
        Employee emp = new Employee.Builder()
                .setType(EmployeeType.CASUAL)
                .build();

        assertTrue(emp.isType(EmployeeType.CASUAL));
        assertFalse(emp.isType(EmployeeType.FULLTIME));
    }

}

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import users.Manager;

public class ManagerTest {

	public ManagerTest() {}
	
	@Test
    void testBuilderCreatesManagerCorrectly() {
        Manager manager = new Manager.Builder()
                .setUserId(10)
                .setFirstName("Sam")
                .setLastName("Joyce")
                .setEmail("manager@test.com")
                .setSeniority(200)
                .setPassHash("secureHash")
                .build();

        assertEquals(10, manager.getUserId());
        assertEquals("Sam", manager.getFirstName());
        assertEquals("Joyce", manager.getLastName());
        assertEquals("Sam Joyce", manager.getFullName());
        assertEquals("manager@test.com", manager.getEmail());
        assertEquals(200, manager.getSeniority());
        assertEquals("secureHash", manager.getPassHash());
    }
	
	@Test
    void testIsManagerAlwaysTrue() {
        Manager manager = new Manager.Builder().build();
        assertTrue(manager.isManager());
    }

    @Test
    void testGetTypeReturnsManager() {
        Manager manager = new Manager.Builder().build();
        assertEquals("Manager", manager.getType());
    }

    @Test
    void testInheritedSeniorityMethods() {
        Manager manager = new Manager.Builder()
                .setSeniority(100)
                .build();

        int added = manager.addSeniority(50);
        assertEquals(150, added);
        assertEquals(150, manager.getSeniority());

        int reduced = manager.removeSeniority(20);
        assertEquals(130, reduced);
        assertEquals(130, manager.getSeniority());
    }

}

package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	AssignedStatusTest.class,
	CancelledStatusTest.class,
	CompleteStatusTest.class,
	OpenStatusTest.class,
	UserFactoryTest.class,
	EmployeeTest.class,
	EmployeeTypeTest.class,
	ManagerTest.class,
	ShiftTest.class,
	WorkDayTest.class,
	WorkWeekTest.class })
public class AllTests {

}

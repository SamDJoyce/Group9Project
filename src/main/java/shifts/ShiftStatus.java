package shifts;

import users.Employee;

public interface ShiftStatus {
	
	void assignEmployee(Shift shift, Employee employee);
	void cancel(Shift shift);
	void complete(Shift shift);
	void open(Shift shift);
	String toString();
}
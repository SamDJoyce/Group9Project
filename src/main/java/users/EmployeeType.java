package users;

public enum EmployeeType {
	FULL_TIME(40,40),
	PART_TIME(25,40),
	CASUAL(0,25);
	
	private final int minHours;
	private final int maxHours;
	
	EmployeeType (int minHours, int maxHours){
		this.minHours = minHours;
		this.maxHours = maxHours;
	}
	
	public int getMinHours() {
		return this.minHours;
	}
	
	public int getMaxHours() {
		return this.maxHours;
	}
	
	public static EmployeeType fromString(String type) {
		for (EmployeeType e : EmployeeType.values()) {
			if (e.toString().equalsIgnoreCase(type)) {
				return e;
			}
		}
		return null;
	}
}

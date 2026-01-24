package shiftEligibility;

public class EligFactory {
	private static final String CASUAL    = "casual";
	private static final String PART_TIME = "parttime";
	private static final String FULL_TIME = "fulltime";
	
	public EligFactory() {
	}
	
	public static ShiftEligibilityStrat get(String type) {
		if (FULL_TIME.equalsIgnoreCase(type)){
			return new FullTimeEligibility();
		}
		if (PART_TIME.equalsIgnoreCase(type)) {
			return new PartTimeEligibility();
		}
		if (CASUAL.equalsIgnoreCase(type)) {
			return new CasualEligibility();
		}
		return null;
	}

}

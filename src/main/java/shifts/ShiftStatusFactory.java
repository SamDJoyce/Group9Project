package shifts;

/**
 * Contains creation logic for ShiftStatus objects
 * 
 * @author Sam Joyce
 */
public class ShiftStatusFactory {

	private static final String CANCELLED = "cancelled";
	private static final String COMPLETE  = "complete";
	private static final String ASSIGNED  = "assigned";
	private static final String OPEN 	  = "open";

	private ShiftStatusFactory() {
	}

	/**
	 * Creates ShiftStatus objects from passed in strings
	 * 
	 * @param status	A string representing the desired status
	 * @return			The ShiftStatus object requested
	 */
	public static ShiftStatus get(String status) {
		if (OPEN.equalsIgnoreCase(status)) {
			return new OpenStatus();
		}
		if (ASSIGNED.equalsIgnoreCase(status)) {
			return new AssignedStatus();
		}
		if (COMPLETE.equalsIgnoreCase(status)) {
			return new CompleteStatus();
		}
		if (CANCELLED.equalsIgnoreCase(status)) {
			return new CancelledStatus();
		}
		return new OpenStatus();
	}
}

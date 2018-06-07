package stackoverflow.item.state;

public class States {
	private static final String[] states = new String[]{
			"IN_FIRST", "AFTER_FIRST", "IN_SECOND", "AFTER_SECOND"
	};
	
	public static String getStateName(int i){
		return states[i];
	}
}

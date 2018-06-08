package stackoverflow;

public class Logger {
	private static volatile boolean with = false;
	
	public static void disable(){
		with = false;
	}
	
	public static void enable(){
		with = true;
	}
	
	public static void log(String state, int printIx, String itemValue){
		if (with){
			System.out.println(state + "#" + printIx + " " + itemValue);
		}
	}
	
}

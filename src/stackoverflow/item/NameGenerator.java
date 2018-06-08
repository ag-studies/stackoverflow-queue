package stackoverflow.item;

public class NameGenerator {
	private static final int SIZE = 1;
	private static final String[] letters = new String[]{"a", "b", "c", "d"};
	
	public static String name(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < SIZE; i++){
			sb.append(letters[(int) (Math.random()*4)]);
		}
		return sb.toString();
	}
}

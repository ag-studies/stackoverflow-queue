package stackoverflow.item;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class HashCalculator {

	public static int calcule(String value){
		return new HashCodeBuilder().append(value).hashCode();
	}
	
}

package stackoverflow.item;

public class Item {
	private final int printIx;//only for printing
	private final String value;//real item value
	
	public Item(int i, String value) {
		this.printIx = i;
		this.value = value;
	}
	
	public int getPrintIx() {
		return printIx;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getValuesHashCode(){
		return HashCalculator.calcule(value);
	}
	
	public boolean sameThat(Item other){
		//attributes-based hashcode
		//it capacits the comparator to "equals, not identical"
		int thisHash = getValuesHashCode();
		int otherHash = HashCalculator.calcule(other.value);
		return thisHash == otherHash;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();//each new item has new hashcode
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);//it is true if hascode = other.hashcode
	}
}

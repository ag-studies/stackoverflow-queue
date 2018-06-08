package stackoverflow.task;

import java.util.Vector;
import java.util.concurrent.ConcurrentMap;

import stackoverflow.item.HashCalculator;
import stackoverflow.item.ItemState;

public class Searcher extends AbstractRunnable{
	private final ConcurrentMap<Integer, Vector<ItemState>> statesMap;
	
	public Searcher(ConcurrentMap<Integer, Vector<ItemState>> map) {
		this.statesMap = map;
		this.delay_time = 100;
	}
	
	@Override
	protected void op() {
		String name = "a";//NameGenerator.name();
		int key = HashCalculator.calcule(name);
		Vector<ItemState> items = statesMap.get(key);
		//checks item
		if (items == null) return;//has not item yet
		//prints states
		
		for (int i = items.size() - 1; i >= 0; i--) {
				ItemState is = items.get(i);
				System.out.println("Item#" + is.getItem().getPrintIx() + "#" + i + "\t: " + 
					is.getItem().getValue() + " - " + is.getState().getValue() + " ");
		}
	}

}

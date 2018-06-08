package stackoverflow.task;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

import stackoverflow.Logger;
import stackoverflow.item.Item;
import stackoverflow.item.ItemState;
import stackoverflow.item.NameGenerator;
import stackoverflow.item.state.ItemStateImpl;

public class CreatingTask extends AbstractRunnable{
	private volatile int i = 0;
	private final BlockingQueue<ItemState> queue;
	private final ConcurrentMap<Integer, Vector<ItemState>> map;
	
	public CreatingTask(BlockingQueue<ItemState> q, ConcurrentMap<Integer, Vector<ItemState>> m) {
		this.queue = q;
		this.map = m;
	}
	
	@Override
	protected void op() throws InterruptedException {
		//create item
		ItemState is = new ItemStateImpl(new Item(i++, NameGenerator.name()));
		//put in monitor and enqueue
		int key = is.getHashValue();
		Vector<ItemState> items = map.get(key);
		if (items == null){
			items = new Vector<>();
			map.put(key, items);
		}
		items.add(is);
		//enqueue
		queue.put(is);
		//log
		Logger.log("in_first", is.getItem().getPrintIx(), is.getItem().getValue());
	}
	
	
}

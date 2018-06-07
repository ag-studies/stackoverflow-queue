package stackoverflow.task;

import java.util.concurrent.BlockingQueue;

import stackoverflow.item.Item;
import stackoverflow.item.ItemState;
import stackoverflow.item.state.InFirstState;

public class CreatingTask extends AbstractRunnable{
	private volatile int i = 0;
	private final BlockingQueue<ItemState> queue;
	
	public CreatingTask(BlockingQueue<ItemState> q) {
		this.queue = q;
	}
	
	@Override
	protected void op() {
		//enqueue
		ItemState is = new InFirstState(new Item(i++));
		queue.add(is);
		System.out.println("Item " + is.getItem().getValue() + ": " + is.getState().getValue());
	}
	
	
}

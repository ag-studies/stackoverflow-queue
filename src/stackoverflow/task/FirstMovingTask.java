package stackoverflow.task;

import java.util.concurrent.BlockingQueue;

import stackoverflow.Logger;
import stackoverflow.item.ItemState;

public class FirstMovingTask extends AbstractRunnable{
	private final BlockingQueue<ItemState> firstQueue;
	private final BlockingQueue<ItemState> secondQueue;
	
	private ItemState process(ItemState is){
		delay((int)(Math.random()*5000));
		return is;
	}

	public FirstMovingTask(BlockingQueue<ItemState> f, BlockingQueue<ItemState> s) {
		this.firstQueue = f;
		this.secondQueue = s;
	}

	@Override
	protected void op() throws InterruptedException{
		try {
			//dequeue
			ItemState is0 = firstQueue.take();
			//log
			Logger.log("after_first", is0.getItem().getPrintIx(), is0.getItem().getValue());
			//process
			ItemState is1 = process(is0.next());
			//enqueue 
			secondQueue.put(is1.next());
			//log
			Logger.log("in_second", is1.getItem().getPrintIx(), is1.getItem().getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

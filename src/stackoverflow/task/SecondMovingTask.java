package stackoverflow.task;

import java.util.concurrent.BlockingQueue;

import stackoverflow.Logger;
import stackoverflow.item.ItemState;

public class SecondMovingTask extends AbstractRunnable{
	private final BlockingQueue<ItemState> secondQueue;
	
	private ItemState finalize(ItemState is){
		delay((int)(Math.random()*5000));
		return is;
	}

	public SecondMovingTask(BlockingQueue<ItemState> q) {
		this.secondQueue = q;
		this.delay_time = 2000;
	}
	
	@Override
	protected void op() {
		try {
			//denqueue
			ItemState is0 = secondQueue.take();
			//discard... or not!
			ItemState is1 =  finalize(is0.next());
			//log
			Logger.log("after_second", is1.getItem().getPrintIx(), is1.getItem().getValue());
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}

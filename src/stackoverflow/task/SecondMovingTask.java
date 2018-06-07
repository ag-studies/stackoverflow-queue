package stackoverflow.task;

import java.util.concurrent.BlockingQueue;

import stackoverflow.item.ItemState;
import stackoverflow.item.state.AfterSecondState;

public class SecondMovingTask extends AbstractRunnable{
	private final BlockingQueue<ItemState> secondQueue;

	public SecondMovingTask(BlockingQueue<ItemState> q) {
		this.secondQueue = q;
	}
	
	@Override
	protected void op() {
		try {
			//denqueue
			ItemState is = new AfterSecondState(secondQueue.take());
			System.out.println("Item " + is.getItem().getValue() + ": " + is.getState().getValue());
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}

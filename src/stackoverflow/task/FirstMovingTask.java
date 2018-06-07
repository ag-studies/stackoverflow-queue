package stackoverflow.task;

import java.util.concurrent.BlockingQueue;

import stackoverflow.item.ItemState;
import stackoverflow.item.state.AfterFirstState;
import stackoverflow.item.state.InSecondState;

public class FirstMovingTask extends AbstractRunnable{
	private final BlockingQueue<ItemState> firstQueue;
	private final BlockingQueue<ItemState> secondQueue;

	public FirstMovingTask(BlockingQueue<ItemState> f, BlockingQueue<ItemState> s) {
		this.firstQueue = f;
		this.secondQueue = s;
	}

	@Override
	protected void op() {
		try {
			//dequeue
			ItemState is0 = new AfterFirstState(firstQueue.take());
			System.out.println("Item " + is0.getItem().getValue() + ": " + is0.getState().getValue());
			//enqueue
			ItemState is1 = new InSecondState(is0);
			secondQueue.add(is1);
			System.out.println("Item " + is1.getItem().getValue() + ": " + is1.getState().getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

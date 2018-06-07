package stackoverflow;

import java.util.concurrent.LinkedBlockingQueue;

import stackoverflow.item.ItemState;
import stackoverflow.task.CreatingTask;
import stackoverflow.task.FirstMovingTask;
import stackoverflow.task.SecondMovingTask;

public class Main {
	
	private static void startTask(String name, Runnable r){
		Thread t = new Thread(r, name);
		t.start();
	}

	public static void main(String[] args) {
		//create queues
		LinkedBlockingQueue<ItemState> firstQueue = new LinkedBlockingQueue<ItemState>();
		LinkedBlockingQueue<ItemState> secondQueue = new LinkedBlockingQueue<ItemState>();
		//start three threads
		startTask("Thread#1", new CreatingTask(firstQueue));
		startTask("Thread#2", new FirstMovingTask(firstQueue, secondQueue));
		startTask("Thread#3", new SecondMovingTask(secondQueue));
	}
}

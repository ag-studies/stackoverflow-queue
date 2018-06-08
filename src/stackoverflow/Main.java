package stackoverflow;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import stackoverflow.item.ItemState;
import stackoverflow.task.CreatingTask;
import stackoverflow.task.FirstMovingTask;
import stackoverflow.task.Searcher;
import stackoverflow.task.SecondMovingTask;

public class Main {
	
	private static void startTask(String name, Runnable r){
		Thread t = new Thread(r, name); 
		t.start();
	}
	
	private static void searchItems(Searcher r){
		Thread t = new Thread(r, "Monitor");
		t.start();
	}

	public static void main(String[] args) {
		//log enable
		//Logger.enable();
		//create queues
		LinkedBlockingQueue<ItemState> firstQueue = new LinkedBlockingQueue<ItemState>(4);
		LinkedBlockingQueue<ItemState> secondQueue = new LinkedBlockingQueue<ItemState>(1);
		ConcurrentHashMap<Integer, Vector<ItemState>> statesMap = new ConcurrentHashMap<Integer, Vector<ItemState>>();
		//start three threads
		startTask("Thread#1", new CreatingTask(firstQueue, statesMap));
		startTask("Thread#2", new FirstMovingTask(firstQueue, secondQueue));
		startTask("Thread#3", new SecondMovingTask(secondQueue));
		//thread search
		searchItems(new Searcher(statesMap));
	}
}

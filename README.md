# stackoverflow-queue
Code for question: https://stackoverflow.com/questions/50625154/tracking-the-progress-between-queues-in-a-map

I see that your model might be improved in consistency, state control, and scaling.

A way of to implement this is accouple the item to your state, enqueue and dequeue this couple and create a mechanism to ensure state change.

My proposal can be see in figure below:

[![enter image description here][1]][1]

According with this model and your example, we can to do:


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

Each task runs the operations ```op()``` of according with below affirmation on *ItemState*:

> one of three dedicated thread moves it to secondQueue and finally
> another dedicated thread removes it.

```ItemState``` is a immutable object that contains ```Item``` and your ```State```. This ensures consistency between Item and State values.

ItemState has knowlegment about the next state creating a mechanism of self-controled state:

    protected void op() {
        try {
            //dequeue
            ItemState is0 = new ItemState(firstQueue.take());
            System.out.println("Item " + is0.getItem().getValue() + ": " + is0.getState().getValue());
            //process here
            //enqueue
            ItemState is1 = new ItemState(is0);
            secondQueue.add(is1);
            System.out.println("Item " + is1.getItem().getValue() + ": " + is1.getState().getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

With ItemState implemetation:

    public class ItemStateImpl implements ItemState {
    	private final Item item;
    	private final State state;
    	
    	public ItemStateImpl(Item i){
    		this.item = i;
    		this.state = new State();
    	}
    
    	public ItemStateImpl(ItemState is) {
    		this.item = is.getItem();
    		this.state = is.getState().next();
    	}
    
    	// gets attrs
    }

So this way is possible build solutions more elegant, flexible and scalable.
Scalable because you can to control more states only change ```next()``` and moving task can be generalized.

Results:

    Item 0: AFTER_FIRST
    Item 0: IN_FIRST
    Item 0: IN_SECOND
    Item 0: AFTER_SECOND
    Item 1: IN_FIRST
    Item 1: AFTER_FIRST
    Item 1: IN_SECOND
    Item 1: AFTER_SECOND
    Item 2: IN_FIRST
    Item 2: AFTER_FIRST
    Item 2: IN_SECOND
    ... others


  [1]: https://i.stack.imgur.com/llHwvl.png

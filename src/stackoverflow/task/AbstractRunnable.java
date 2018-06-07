package stackoverflow.task;

public abstract class AbstractRunnable implements Runnable {
	
	private void delay(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void op();
	
	public void run() {
		while(true){
			op();
			delay();
		}
	}
}

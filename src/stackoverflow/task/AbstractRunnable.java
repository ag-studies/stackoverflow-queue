package stackoverflow.task;

public abstract class AbstractRunnable implements Runnable {
	protected int delay_time = 100;
	
	protected void delay(int d){
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private void delay(){
		delay(delay_time);
	}
	
	protected abstract void op() throws InterruptedException;
	
	public void run() {
		try {
			while(true){
				op();
				delay();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}

package stackoverflow.item;

import stackoverflow.item.state.States;

public class State {
	private final int value;
	
	private State(int v){
		this.value = v;
	}
	
	public State(){
		this.value = 0;
	}
	
	public String getValue() {
		return States.getStateName(value);
	}
	
	public State next(){
		return new State((value+1) % 4);
	}
}

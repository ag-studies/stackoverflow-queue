package stackoverflow.item.state;

import stackoverflow.item.Item;
import stackoverflow.item.ItemState;
import stackoverflow.item.State;

public class ItemStateImpl implements ItemState {
	private final Item item;
	private State state;
	
	public ItemStateImpl(Item i){
		this.item = i;
		this.state = new State();
	}

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public State getState() {
		synchronized (state) {
			return state;
		}
	}

	@Override
	public int getHashValue() {
		return item.getValuesHashCode();
	}
	
	@Override
	public ItemState next() {
		synchronized (state) {
			state = state.next();
			return this;
		}
	}
	
}

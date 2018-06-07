package stackoverflow.item.state;

import stackoverflow.item.Item;
import stackoverflow.item.ItemState;
import stackoverflow.item.State;

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

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public State getState() {
		return state;
	}
}

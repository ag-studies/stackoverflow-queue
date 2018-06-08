package stackoverflow.item;

public interface ItemState {
	Item getItem();
	State getState();
	int getHashValue();
	ItemState next();
}

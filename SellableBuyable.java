public interface SellableBuyable {
	/*
	Indicates/enforces the ability of items to be bought and sold (that the item is able to perform transactions)
	*/
	
	String getItemName();
	int getCost();
	int getLevel();
}
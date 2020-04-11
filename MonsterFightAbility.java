public interface MonsterFightAbility {
	/*
	Indicates/enforces the ability of items to be bought and sold (that the item is able to perform transactions)
	*/
	
	void attack(Character hero);
	void magicdamagetaken(int damage,Spells spell);
	void damagetaken(int damage);
}
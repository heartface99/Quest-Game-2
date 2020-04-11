public interface HeroFightAbility {
	/*
	Indicates/enforces the ability of items to be bought and sold (that the item is able to perform transactions)
	*/
	
	void attack(Monster target);
	void magicattack(Monster target);
	void damagetaken(int damage);
}
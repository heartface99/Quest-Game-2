public interface HeroFightAbility {
	/*
	 An interface that enforces the hero to have some fight-specific methods
	*/
	
	void attack(Monster target);
	void magicattack(Monster target);
	void damagetaken(int damage);
}
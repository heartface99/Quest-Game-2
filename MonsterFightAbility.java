public interface MonsterFightAbility {
	/*
	Enforces the monster to have some fight-specific methods
	*/
	
	void attack(Character hero);
	void magicdamagetaken(int damage,Spells spell);
	void damagetaken(int damage);
}
public class Weapon extends Item {
	/*
		Represents a type of item that is used to attack the monster.
	*/ 
    private int damage;

    public Weapon(String name, int cost, int level, int damage){
        super(name,cost,level);
        this.damage = damage;
    }

    public int getDam(){
        return this.damage;
    }
}
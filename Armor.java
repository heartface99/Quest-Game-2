public class Armor extends Item {
	/*
		Armor is an item that lowers the damage from attacks.
	*/
    private int damage_reduction;
    
    public Armor(String name, int cost, int level, int damage_reduction){
        super(name,cost,level);
        this.damage_reduction = damage_reduction;
    }

    public int getDamageRed(){
        return this.damage_reduction;
	}	
}
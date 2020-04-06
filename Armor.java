public class Armor extends Item{
    private int damage_reduction;
    
    public Armor(String name, int cost, int level, int damage_reduction){
        super(name,cost,level);
        this.damage_reduction = damage_reduction;
    }

    public int getDamageRed(){
        return this.damage_reduction;
    
}
}
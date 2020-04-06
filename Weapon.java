public class Weapon extends Item{
    private int damage;

    public Weapon(String name, int cost, int level, int damage){
        super(name,cost,level);
        this.damage = damage;
    }
    public int getDam(){
        return this.damage;
    }
}
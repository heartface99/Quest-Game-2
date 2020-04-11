public class Item implements SellableBuyable {
    /*
        This is the base class for all items in which other items such as weapons, armor, potions all inherit from 
    */
    private String name;
    private int cost;
    private int required_level;

    public Item(String n, int c, int rl){
        this.name= n;
        this.cost = c;
        this.required_level= rl;
    }

    public String getItemName(){
        return this.name;
    }
    public int getCost(){
        return this.cost;
    } 
    public int getLevel(){
        return this.required_level;
    } 
}
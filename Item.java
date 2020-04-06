public class Item{
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
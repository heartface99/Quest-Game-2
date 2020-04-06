public class Character_monster{
    protected String name;
    protected int level;
    protected  int hp;
    protected int totalhp;
    
    public Character_monster(String name, int level){
        this.name=name;
        this.level= level;
        this.hp= 100*level;
        this.totalhp=hp;

    }

    // public int getTotalHp1(){
    //     return this.totalhp;
    // }
    
    // public String getName1(){
    //     return this.name;
    // }

    // public int getHp1(){
    //     return this.hp;
    // }
}
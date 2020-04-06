public class Potion extends Item{
    int hp;
    int mana;
    int dex;
    int str;
    int agi;
    int num_potion=1;
    boolean temp_increase;

    public Potion(String name,int cost, int required_level, int hp, int mana, int dex, int str,  int agi,boolean temp_increase){
        super(name,cost,required_level);
        this.hp=hp;
        this.mana=mana;
        this.dex=dex;
        this.str= str;
        this.agi=agi;
        this.temp_increase= temp_increase;
    }

    public void decreasePotion(){
        num_potion=num_potion-1;
        
    }
    public int getNum(){
        return num_potion;
    }
 
    public int getAgi() {
        return agi;
    }
    public int getStr(){
        return str;

    }
    public int getDex(){
        return dex;
    }

    public int getMana(){
        return mana;
    }

    public int getHp(){
        return this.hp;
    }
    public boolean getTemp(){
        return temp_increase;
    }
    public void add_num_potion(){
        this.num_potion++;
    }
}
import java.util.Random;
public class Monster extends Character_monster{
    private int damage;
    private int dodge_chance;
    private int defense;
    private String type;
    private int dodge_chance_perm;
    private int defense_perm;
    private int damage_perm;


    public Monster(String name, int level, int damage,  int defense,int dodge,String type){
        super(name,level);
        this.damage= damage;
        this.defense= defense;
        this.dodge_chance=dodge;
        this.type= type;
        this.defense_perm=defense;
        this.dodge_chance_perm=dodge;
        this.damage_perm=damage;


    }
    public void reset(){
        this.hp=totalhp;
        this.defense=defense_perm;
        this.dodge_chance=dodge_chance_perm;
        this.damage=damage_perm;
    }
    public int get_hp(){
        return this.hp;
    }
    public int get_totalhp(){
        return this.totalhp;
    }

    public int getLevel(){
        return this.level;
    }
    public String getname(){
        return this.name;
    }
    public int getDamage(){
        return this.damage;
    }

    public int getDef(){
        return this.defense;
    }

    public int getDodge(){
        return this.dodge_chance;
    }

    public String getType(){
        return this.type;
    }

    public void damagetaken(int damage){
        int hpnow;
        Random rand = new Random();
        int val = rand.nextInt(100);
        if (val>dodge_chance){
            damage=(int)(damage-(defense*0.30));
            hpnow=this.hp-damage;
            if(damage>0){
        
            if(hpnow<0){
                this.hp=0;
                System.out.println(name + "took "+ damage +" damage."+name+" has been defeated");
            }
            else{
             this.hp=hpnow;
             System.out.println(name + "took "+ damage +" damage."+name+" has "+this.hp+" left!");
        }
    }

    else{
        System.out.println(name+ "defense was too high and took no damage");
    }
   
        
    }
    else{
        System.out.println(name+" has dodge the attack!");
    }
    


    
    }

    public void magicdamagetaken(int damage,Spells spell){
        int hpnow;
        Random rand = new Random();
        int val = rand.nextInt(100);
        if (val>dodge_chance){
            damage=(int)(damage-(defense*0.30));
            hpnow=this.hp-damage;
        
            if(hpnow<0){
                this.hp=0;
                System.out.println(name + "took "+ damage +" damage."+name+" has been defeated");
            }
            else{
             this.hp=hpnow;
             this.damage=(int)(this.damage*spell.get_damage_red());
             this.dodge_chance=(int)(this.damage*spell.get_dodge_red());
             this.defense=(int)(this.damage*spell.get_defense_red());
             System.out.println(name + "took "+ damage +" damage."+name+" has "+this.hp+" left!");
        }
        
    }
    else{
        System.out.println(name+" has dodge the attack!");
    }
    


    
    }

    public void attackhero(Character hero){
        System.out.println(name+" has attacked "+ hero.getName());
        hero.damagetaken(damage);

    }


}
import java.util.*;  
public class Character extends Character_monster{
    // private String name;
    // private int level;
    // private  int hp;
    // private int totalhp;
    private int mana;
    private int totalmana;
    private  int strength;
    private int agility;
    private int dexterity;
    
    private  int temp_strength=0;
    private int temp_agility=0;
    private int temp_dexterity=0;

    private int starting_money;
    private int starting_experience;
    private String whatclass;
    // private int str_ratio;
    // private int dex_ratio;
    // private int agi_ratio;
    private int next_level_exp;
    private Armor char_armor=null;
    private Weapon char_weapon=null;
    private Spells char_spell=null;
    private ArrayList<Weapon> weapon_storage;
    private ArrayList<Armor> armor_storage;
    private ArrayList<Potion> potion_storage;
    private ArrayList<Spells> spell_storage;

    public Character(String n, int ma, int str, int agi, int dex, int sm, int sexp){
        super(n,1);
        this.mana= ma;
        this.totalmana= this.mana;
        this.strength= str;
        this.agility=agi;
        this.dexterity= dex;
        this.starting_money= sm;
        this.starting_experience=sexp;
        this.next_level_exp= this.level*10;
        this.weapon_storage= new  ArrayList<Weapon> ();
        this.armor_storage= new ArrayList<Armor>();
        this.potion_storage= new ArrayList<Potion>();
        this.spell_storage=new ArrayList<Spells>();

    }
    public void setclass(String s){
        this.whatclass=s;
    
    }
    public int getStr(){
        return this.strength+this.temp_strength;
    }
    public int getAgi(){
        return this.agility+this.temp_agility;
    }
    public int getDex(){
        return this.dexterity+this.temp_dexterity;
    }
    public int getTotalHp(){
        return this.totalhp;
    }
    public int getTotalMana(){
        return this.totalmana;
    }
    public int getMoney(){
        return this.starting_money;
    }
    public int getExp(){
        return this.starting_experience;
    }
    public int gettotalExp(){
        return this.next_level_exp;
    }
    public String getWhatClass(){
        return this.whatclass;
    }
    public String getName(){
        return this.name;
    }

    public int getLevel(){
        return this.level;
    }

    public int getHp(){
        return this.hp;
    }
    public int getMana(){
        return this.mana;

    }

    public void resettemp(){
        temp_strength=0;
        temp_agility=0;
        temp_dexterity=0;
        


    }

    // public Character clonecurrent(){
    //     Character newitem= new Character(this.name,this.mana,this.strength,this.agility,this.dexterity,this.starting_money,this.starting_experience);
    //     newitem.temp_strength=this.temp_strength;
    //     newitem.temp_agility=this.temp_agility;
    //     newitem.temp_dexterity=this.temp_dexterity;
    //     return newitem;
    // }
    public String getStats(){
        String s= " str: " + this.strength + " agi: " + this.agility + "dex: " + this.dexterity ;
        return s;
    }
    public Boolean is_Leveledup(){
        if ((this.starting_experience/ this.next_level_exp) >1){
            return true;
        }
        else{
            return false;
        }
    }

    public void levelUp(double agi_ratio,double str_ratio,double dex_ratio){
        if(starting_experience>= next_level_exp&& level<10){
        System.out.println("here");
        this.level=this.level+1;
        this.totalhp = 100*this.level;
        this.hp=totalhp;
        this.totalmana= this.totalmana+(int)(this.totalmana*0.1);
        this.mana=totalmana;
        this.agility= this.agility+ (int)(this.agility*agi_ratio);

        this.strength= this.strength+ (int)(this.strength*str_ratio);
        this.dexterity= this.dexterity+ (int)(this.dexterity*dex_ratio);
        this.starting_experience= this.starting_experience-this.next_level_exp;
        this.next_level_exp= this.level*10;
        System.out.println(name+" has leveled up to level "+ this.level);
    }
}

    public void gainMoney(int x){
        this.starting_money= this.starting_money +x;
        System.out.println(name+" has gain " +x+" money!");
    
    }
    public void gainedExp(int x){
        this.starting_experience=this.starting_experience +x;
        System.out.println(name+" has gain " +x+" exp!");
    }
    public void gainHp(int x){
        if((hp+x)>totalhp){
            hp= totalhp;
        }
        else{
            hp=hp+x;
        }
        System.out.println(name+" recovered "+ x +" hp.");
    }
    public void gainMana(int x){
        if((mana+x)>totalmana){
            mana=totalmana;
        }
        else{
            mana=mana+x;
        }
        System.out.println(name+" recovered "+ x +" mana.");
    }

    public void buyArmor(Armor x){
        String name= x.getItemName();
        if(armor_storage.contains(x)){
                System.out.println(this.name+" already have" +name);
            }
        
        else{
            if (x.getCost()> starting_money){
                System.out.println( this.name+" does not  have enough money for " + name);
            }
            else if(x.getLevel()> this.level){
                System.out.println(this.name +"'s level is too low to purchase "+ name);
            }
            else{
            starting_money= starting_money- x.getCost();
            armor_storage.add(x);
            System.out.println(name+" added to inventory! The remaing money on "+this.name+" is "+ starting_money) ;
        }
    }
}
    

    public void buyWeapon(Weapon x){
        String name= x.getItemName();
        if(weapon_storage.contains(x)){
                System.out.println(this.name+ " already have " +name);
            }
        
        else{
            if (x.getCost()> starting_money){
                System.out.println( this.name+ " does not have enough money for " + name);
            }
            else if(x.getLevel()> this.level){
                System.out.println(this.name +"'s level is too low to purchase "+ name);
            }
            else{
            starting_money= starting_money- x.getCost();
            weapon_storage.add(x);
            System.out.println(name+" added to inventory! The remaing money on "+this.name+" is "+ starting_money) ;
        }
    }
    }

    public void buySpell(Spells x){
        String name= x.getItemName();
        if(spell_storage.contains(x)){
                System.out.println(this.name+ " already have " +name);
            }
        
        else{
            if (x.getCost()> starting_money){
                System.out.println( this.name+ " does not have enough money for " + name);
            }
            else if(x.getLevel()> this.level){
                System.out.println(this.name +"'s level is too low to purchase "+ name);
            }
            else{
            starting_money= starting_money- x.getCost();
            spell_storage.add(x);
            System.out.println(name+" added to inventory! The remaing money on "+this.name+" is "+ starting_money) ;
        }
    }
    }
    public void buyPotion(Potion x){
        String name= x.getItemName();
        
            if (x.getCost()> starting_money){
                System.out.println( this.name+ " does not have enough money for " + name);
            }

            else if(x.getLevel()> this.level){
                System.out.println(this.name +"'s level is too low to purchase "+ name);
            }
            else{
            starting_money= starting_money- x.getCost();
            if(potion_storage.contains(x)){
                x.add_num_potion();
                System.out.println(name+" added to inventory! Now you have " +name+ '('+x.getNum()+") !"+ " The remaing money on "+this.name+" is "+ starting_money) ;
            }
            else{
            potion_storage.add(x);
            System.out.println(name+" added to inventory! The remaing money on "+this.name+" is "+ starting_money) ;
        }
    }
    }

    public void sellPotion(Potion x){
        String name= x.getItemName();
        int newcost= x.getCost()/2;
        if(x.getNum()==1){
                potion_storage.remove(x);
                starting_money= starting_money+newcost;
                System.out.println(name+" sold for "+ newcost+ " The remaing money on "+this.name+" is "+ starting_money) ;
            }
         else{
            starting_money= starting_money+newcost;
            x.decreasePotion();
            System.out.println(name+" sold for "+ newcost+ " The remaing money on "+this.name+" is "+ starting_money) ;
        }
    }

    public String pad (String input,int num_extended){
        int counter;
        while(input.length()<num_extended){
            counter=num_extended-input.length();
            input=input+" ";
            counter++;
        }
        return input;
    }
    public void printSpellSale(){
        Spells current;
        String name;
        String cost;
        String required_level;
        String damage;
        String manacost;
        String type;
        System.out.println("Spells"+ "                                " + "cost"+ "            "+"required_level"+  "        "+"damage "+"      " + "mana cost"+ "       "+"type");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        int i;
        for(i = 0; i < spell_storage.size(); i++){
            current= spell_storage.get(i);
            name= i+")"+current.getItemName();
            name= pad(name, 20);
            cost= Integer.toString(current.getCost()/2);
            cost=pad(cost,6);
            required_level= Integer.toString(current.getLevel());
            required_level=pad(required_level, 3);
            damage=Integer.toString(current.getDam());
            damage=pad(damage,5);
            manacost= Integer.toString(current.getMana());
            manacost= pad(manacost,5);
            type= current.getType();
    
           
            System.out.println(name+"                   " + cost+"               "+required_level+"               "+ damage+"          "+manacost+"        "+type);
        }
        System.out.println(i+")Quit");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    }
    public void printPotionSale(){
        Potion current;
        String name;
        String cost;
        String required_level;
        String hp;
        String mana;
        String strength;
        String dexterity;
        String agility;
        boolean temp;
        String num_potions;
        int i;
        System.out.println("Potion"+ "                     " + "cost"+ "          "+"required_level" +"       "+ "hp"+ "     "+ "mana"+ "   "+"strength"+ "    "+ "dexterity" + "     " +"agility"+ "      " +" num_potions"+ "  "+ "only for temporary effect during battle");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        for(i = 0; i < potion_storage.size(); i++){
            current= potion_storage.get(i);
            name= i+")"+current.getItemName();
            name= pad(name, 20);
            cost= Integer.toString(current.getCost()/2);
            cost=pad(cost,6);
            required_level= Integer.toString(current.getLevel());
            required_level=pad(required_level, 3);
            hp=Integer.toString(current.getHp());
            hp= pad(hp,3);
            mana=Integer.toString(current.getMana());
            mana=pad(mana,3);
            strength=Integer.toString(current.getStr());
            strength= pad(strength,3);
            dexterity=Integer.toString(current.getDex());
            dexterity=pad(dexterity,3);
            agility=Integer.toString(current.getAgi());
            agility=pad(dexterity,3);
            temp=current.getTemp();
            num_potions=Integer.toString(current.getNum());
            num_potions=pad(num_potions,3);
            System.out.println(name+"       " +cost+"              "+ required_level+"           "+ hp+"      "+mana+ "      "+ strength+"        "+dexterity+"           "+agility+"         "+num_potions+"           "+temp);
        }
        System.out.println(i+") Quit");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    
            
    }

    public void printArmorSale(){
        Armor current;
        String name;
        String cost;
        String required_level;
        String damage_reduction;
        System.out.println("Armor"+ "                                " + "cost"+ "            "+"required_level"+  "            "+"damage reduction");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        int i;
        for(i = 0; i < armor_storage.size(); i++){
            current= armor_storage.get(i);
            name= i+")"+current.getItemName();
            name= pad(name, 20);
            cost= Integer.toString(current.getCost()/2);
            cost=pad(cost,6);
            required_level= Integer.toString(current.getLevel());
            required_level=pad(required_level, 3);
            damage_reduction=Integer.toString(current.getDamageRed());
            System.out.println(name+"                 " + cost+"               "+required_level+"                    "+ damage_reduction+"    ");
        }
        System.out.println(i+")Quit");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    }
    public void printWeaponSale(){
        Weapon current;
        String name;
        String cost;
        String required_level;
        String damage;
        System.out.println("Weapon"+ "                                " + "cost"+ "            "+"required_level"+  "            "+"damage ");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        int i;
        for(i = 0; i < weapon_storage.size(); i++){
            current= weapon_storage.get(i);
            name= i+")"+current.getItemName();
            name= pad(name, 20);
            cost= Integer.toString(current.getCost()/2);
            cost=pad(cost,6);
            required_level= Integer.toString(current.getLevel());
            required_level=pad(required_level, 3);
            damage=Integer.toString(current.getDam());
            System.out.println(name+"                 " + cost+"               "+required_level+"                    "+ damage+"    ");
        }
        System.out.println(i+")Quit");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    }
    public void attack(Monster target){
        int damagedeal;
        if(char_weapon==null){
        damagedeal=(int)((temp_strength+strength) *0.05);
        }
        else{
            damagedeal= (int)(temp_strength+ strength+char_weapon.getDam()*0.05);
        }
        System.out.println(name+" attacked "+target.getname() );
        target.damagetaken(damagedeal);

    }
    public void magicattack(Monster target){
        int damagedeal;
        if(char_spell==null|| mana< char_spell.getMana()){
            System.out.println("This hero is unable to cast any spell so regular attack is used!");
            attack(target);
        }
        else{
            mana=mana-char_spell.getMana();
            damagedeal= (int)( char_spell.getDam() +((dexterity+temp_dexterity)/10000)*char_spell.getDam());
            System.out.println(name+" casted "+ char_spell.getItemName()+" on "+target.getname() );
            target.magicdamagetaken(damagedeal,this.char_spell);
        }
        

    }

    public void damagetaken(int damage){
        int hpnow;
        Random rand = new Random();
        int val = rand.nextInt(100);
        if (val>(agility*0.02)){

            if(char_armor==null==false){
            damage= (damage-char_armor.getDamageRed());
            }
            if(damage>0){
            hpnow=this.hp-damage;
            
            if(hpnow<0){
                this.hp=0;
                System.out.println(name + "took "+ damage +" damage."+name+" has been defeated");
            }
            else{
             this.hp=hpnow;
             System.out.println(name + "took "+ damage +" damage."+name+" has "+this.hp+" hp left!");
        }
        
    }
    else{
        System.out.println(name+ " defense was too high and took no damage.");
    }
}
    else{
        System.out.println(name+" has dodge the attack!");
    }
    


    
    }

    public  ArrayList<Armor> returnArmorStorage(){
        return this.armor_storage;
    }

    public Armor getArmor(int x){
        return this.armor_storage.get(x);
    }

    public  ArrayList<Weapon> returnWeaponStorage(){
        return this.weapon_storage;
    }

    public Weapon getWeapon(int x){
        return this.weapon_storage.get(x);
    }

    public  ArrayList<Potion> returnPotionStorage(){
        return this.potion_storage;
    }

    public Potion getPotion(int x){
        return this.potion_storage.get(x);
    }

    public  ArrayList<Spells> returnSpellStorage(){
        return this.spell_storage;
    }

    public Spells getSpell(int x){
        return this.spell_storage.get(x);
    }


    public void sellSpell(Spells x){
        String name= x.getItemName();
        int newcost= x.getCost()/2;
        spell_storage.remove(x);
        starting_money= starting_money+newcost;
        System.out.println(name+" sold for "+ newcost+ " The remaing money on "+this.name+" is "+ starting_money) ;
        }

    
    public void sellArmor(Armor x){
        String name= x.getItemName();
        int newcost= x.getCost()/2;
        armor_storage.remove(x);
        starting_money= starting_money+newcost;
        System.out.println(name+" sold for "+ newcost+ " The remaing money on "+this.name+" is "+ starting_money) ;
        }
    
    public void sellWeapon (Weapon x){
        String name= x.getItemName();
        int newcost= x.getCost()/2;
        weapon_storage.remove(x);
        starting_money= starting_money+newcost;
        System.out.println(name+" sold for "+ newcost+ " The remaing money on "+this.name+" is "+ starting_money) ;
            }

        public void equipArmor(Armor x){
             String xname= x.getItemName(); 
             
             if (this.char_armor==null){
                this.char_armor=x;
                armor_storage.remove(x);
                 System.out.println(xname+" has been equipped by "+name);
            }
            else{
                Armor currentarmor= this.char_armor;
                unequipArmor();
                equipArmor(x);


            }
        }

        public void unequipArmor(){
            if(this.char_armor==null){
            
                System.out.println(name+" is not holding any armor.");
            }
            else{
            
           
            System.out.println(this.char_armor.getItemName()+" has been unequipped by "+name);
            armor_storage.add(this.char_armor);
            this.char_armor=null;
        }
    }

        
        public void equipWeapon(Weapon x){
            String xname= x.getItemName();
            if(this.char_weapon==null){
            
            weapon_storage.remove(x);
            this.char_weapon=x;
            System.out.println(xname+" has been equipped by "+name);
            }
            else{
                
                unequipWeapon();
                equipWeapon(x);
            }
        }

        public void unequipWeapon(){
           if(this.char_weapon==null){
            System.out.println(name+ " is currently not holding any weapon.");
           }
           else{
            System.out.println(char_weapon.getItemName()+" has been unequipped by "+name);
            weapon_storage.add(char_weapon);
            this.char_weapon=null;
           }
        }

        
        
        public void equipSpell(Spells x){
            
            String xname= x.getItemName();
            if(this.char_spell==null){

            spell_storage.remove(x);
            this.char_spell=x;
            System.out.println(xname+" has been equipped by "+name);
            }
            else{
                unequipSpell();
                equipSpell(x);

            }
        }
        

        public void unequipSpell(){
            if(this.char_spell==null){

                System.out.println(name+" is currently not holding any spell.");
            }
            else{
            System.out.println(this.char_spell.getItemName()+" has been unequipped by "+name);
            this.spell_storage.add(char_spell);
            this.char_spell=null;
        }
    }

    public void add_hp(int x){
        int hp_now= this.hp+x;
        if(hp_now > totalhp){
            this.hp= totalhp;
        }
        else{
            this.hp= hp_now;
        }
    }
    public void add_mana(int x){
        int mana_now= this.mana+x;
        if(mana_now > totalmana){
            this.mana= totalmana;
        }
        else{
            this.mana= mana_now;
        }
    }


    public void usePotion(Potion x){
        if(x.getNum()==1){
            potion_storage.remove(x);
        }
        else{
            x.decreasePotion();
        }

        if (x.getTemp()==false){
            strength=strength+x.getStr();
            agility= agility+x.getAgi();
            dexterity=dexterity+x.getAgi();
            add_hp(x.getHp());
            add_mana(x.getMana());
            

        }
        else{
            temp_strength=temp_strength+x.getStr();
            temp_agility= temp_agility+x.getAgi();
            temp_dexterity=temp_dexterity+x.getAgi();
            
        }

    }

}
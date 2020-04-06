import java.util.*;

public class MainMarket{
    private ArrayList<Weapon> weapon_store;
    private ArrayList<Armor> armor_store;
    private ArrayList<Spells> spell_store;
    private ArrayList<Potion> potion_store;

public MainMarket(){
    
 
    weapon_store = new ArrayList<Weapon> ();
    armor_store= new ArrayList<Armor>();  
    potion_store= new ArrayList<Potion>();
    spell_store=new ArrayList<Spells>();
    this.int_weapon();
    this.int_armor();
    this.int_potion();
    this.int_spell();
}

public void int_spell(){
    Spells Snow_Canon= new Spells ("Snow_Canon",500,2,650,250,"ice");
    Spells Ice_Blade= new Spells ("Ice_Blade",250,1,450,100,"ice");
    Spells Frost_Blizzard = new Spells("Frost_Blizzard",750,5,850,350,"ice");
    Spells Artic_Storm = new Spells("Artic_Storm",700,6,800,300,"ice");
     spell_store.add(Ice_Blade);
     spell_store.add(Snow_Canon);
    spell_store.add(Frost_Blizzard);
    spell_store.add(Artic_Storm);
    Spells Flame_Tornado= new Spells("Flame_Tornado",700,4,850,300,"fire");
    Spells Breath_of_Fire= new Spells("Breath_of_Fire",350,1,450,100,"fire");
    Spells Heat_Wave= new Spells("Heat_Wave",450,2,600,150,"fire");
    Spells Lava_Commet= new Spells("Lava_Commet",800,7,1000,550,"fire");
    spell_store.add(Breath_of_Fire);
    spell_store.add(Heat_Wave);
    spell_store.add(Flame_Tornado);
    spell_store.add(Lava_Commet);

    Spells Lightning_Dagger=new Spells("Lightning_Dagger",400,1,500,150,"ligthning");
    Spells Thunder_Blast= new Spells("Thunder_Blast",750,4,950,400,"ligthning");
    Spells Electric_Arrows= new Spells("Electric_Arrows",550,5,650,200,"ligthning");
    Spells Spark_Needles= new Spells("Spark_Needle",500,2,600,200,"ligthning");

    spell_store.add(Lightning_Dagger);
    spell_store.add(Spark_Needles);
    spell_store.add(Thunder_Blast);
    spell_store.add(Electric_Arrows);


}
public void int_potion(){
    Potion Healing_potion= new Potion("Healing_Potion",100,1,100,0,0,0,0,false);
    Potion Strength_potion = new Potion("Strength_Potion",200,1,0,0,0,75,0,true);
    Potion Magic_potion=  new Potion("Magic_Potion", 350, 2, 0,100, 0, 0, 0, false);
    Potion Luck_Exlixir= new Potion ("Luck_Exlixir",500,4,0,0,65,65,65,true);
    Potion Mermaid_Tears= new Potion("Mermaid_Tears",500,4,0,0,100,100,100,true);
    Potion Ambrosia=new Potion("Ambrosia",1000,8,0,0,20,20,20,false);

    potion_store.add(Healing_potion);
    potion_store.add(Strength_potion);
    potion_store.add(Magic_potion);
    potion_store.add(Luck_Exlixir);
    potion_store.add(Mermaid_Tears);
    potion_store.add(Ambrosia);

}
public void int_weapon(){
    Weapon Sword= new Weapon("Sword",500,1,800);
    Weapon Bow= new Weapon("Bow",300,2,500);
    Weapon Scythe = new Weapon("Scythe",1000,6,1100);
    Weapon Axe = new Weapon("Axe",550,5,850);
    Weapon Shield= new Weapon("Shield",400,1,100);
    Weapon Tswords= new Weapon("Tswords",1400,8,1600);
    Weapon Dagger= new Weapon("Dagger",200,1,250);
    this.weapon_store.add(Sword);
    this.weapon_store.add(Bow);
    this.weapon_store.add(Axe);
    this.weapon_store.add(Scythe);
    this.weapon_store.add(Shield);
    this.weapon_store.add(Tswords);
    this.weapon_store.add(Dagger);
    



}
public void int_armor(){
    Armor Platinum_Shield= new Armor("Platinum_Shield",150,1,200);
    Armor Breastplate = new Armor("Breastplate",350,3,600);
    Armor Full_Body_Armor= new Armor("Full_Body_Armor",1000,8,1100);
    Armor Wizard_Shield =new Armor("Wizard_Shield",1200,10,1500);
    Armor Speed_Boots= new Armor("Speed_Boots",550,4,600);
    this.armor_store.add(Platinum_Shield);
    this.armor_store.add(Breastplate);
    this.armor_store.add(Full_Body_Armor);
    this.armor_store.add(Wizard_Shield);
    this.armor_store.add(Speed_Boots);

}
public boolean add_weapon(Weapon x){
    if(weapon_store.contains(x)){
        return false;
    }
    else{

        weapon_store.add(x);
        return true;
    }
}

public boolean add_Armor(Armor x){
    if(armor_store.contains(x)){
        return false;
    }
    else{

        armor_store.add(x);
        return true;
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

public void printPotion(){
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
    int i;
    System.out.println("Potion"+ "                     " + "cost"+ "          "+"required_level" +"       "+ "hp"+ "     "+ "mana"+ "   "+"strength"+ "    "+ "dexterity" + "     " +"agility"+ "      " + "only for temporary effect during battle");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    for(i = 0; i < potion_store.size(); i++){
        current= potion_store.get(i);
        name= i+")"+current.getItemName();
        name= pad(name, 20);
        cost= Integer.toString(current.getCost());
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
        System.out.println(name+"        " +cost+"              "+ required_level+"           "+ hp+"      "+mana+ "      "+ strength+"        "+dexterity+"           "+agility+"         "+temp);
    }
    System.out.println(i+") Quit");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");

        
}

public void printArmor(){
    Armor current;
    String name;
    String cost;
    String required_level;
    String damage_reduction;
    System.out.println("Armor"+ "                                " + "cost"+ "            "+"required_level"+  "            "+"damage reduction");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    int i;
    for(i = 0; i < armor_store.size(); i++){
        current= armor_store.get(i);
        name= i+")"+current.getItemName();
        name= pad(name, 20);
        cost= Integer.toString(current.getCost());
        cost=pad(cost,6);
        required_level= Integer.toString(current.getLevel());
        required_level=pad(required_level, 3);
        damage_reduction=Integer.toString(current.getDamageRed());
        System.out.println(name+"                 " + cost+"               "+required_level+"                    "+ damage_reduction+"    ");
    }
    System.out.println(i+")Quit");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
}
public void printWeapon(){
    Weapon current;
    String name;
    String cost;
    String required_level;
    String damage;
    System.out.println("Weapon"+ "                                " + "cost"+ "            "+"required_level"+  "            "+"damage ");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    int i;
    for(i = 0; i < weapon_store.size(); i++){
        current= weapon_store.get(i);
        name= i+")"+current.getItemName();
        name= pad(name, 20);
        cost= Integer.toString(current.getCost());
        cost=pad(cost,6);
        required_level= Integer.toString(current.getLevel());
        required_level=pad(required_level, 3);
        damage=Integer.toString(current.getDam());
        System.out.println(name+"                 " + cost+"               "+required_level+"                    "+ damage+"    ");
    }
    System.out.println(i+")Quit");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
}

public void printSpell(){
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
    for(i = 0; i < spell_store.size(); i++){
        current= spell_store.get(i);
        name= i+")"+current.getItemName();
        name= pad(name, 20);
        cost= Integer.toString(current.getCost());
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
    public ArrayList<Spells> returnSpellStore(){
        return this.spell_store;
    }
    public  ArrayList<Weapon> returnWeaponStore(){
        return this.weapon_store;
    }
    public Spells getSpell(int x){
        return this.spell_store.get(x);
    }

    public Armor getArmor(int x){
        return this.armor_store.get(x);
    }
    public Potion getPotion(int x){
        return this.potion_store.get(x);
    }
    public Weapon getWeapon(int x){
        return this.weapon_store.get(x);
    }
    public  ArrayList<Armor> returnArmorStore(){
        return this.armor_store;
    }
    public ArrayList<Potion> returnPotionStore(){
        return this.potion_store;
    }
    public static void main (String[] args){
        MainMarket a= new MainMarket();
        a.printSpell();
}
}
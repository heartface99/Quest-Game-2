import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class Spells extends Item {

    /* This class represents a type of item, Spells, which allows the hero perform spells to attack the monster */

    private int damage;
    private int mana_cost;
    private String typeofskill;
    private double damage_red=1.0;
    private double dodge_red=1.0;
    private double defense_red=1.0;

    public Spells(String name, int cost, int level, int damage, int manacost,String typeofskill){
        super(name,cost,level);
        this.damage=damage;
        this.mana_cost= manacost;
        this.typeofskill= typeofskill;
        seteffect();
    }

    public void seteffect(){
        // depending on the type of spell, determines the type of attack it will have
        if(typeofskill.equals("ice")){
            damage_red= 0.9;
        }
        else if( typeofskill.equals("fire")){
            defense_red=0.9;

        }
        else{
            dodge_red=0.9;
        }
    }

    public int getDam(){
        return this.damage;
    }
    public int getMana(){
        return this.mana_cost;
    }
    public double get_damage_red(){
        return this.damage_red;
    }
    public double get_dodge_red(){
        return this.dodge_red;
    }
    public double get_defense_red(){
        return this.defense_red;
    }
    public String getType(){
        return this.typeofskill;
    }
}
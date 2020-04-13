public class Sorcerer extends Character {
    /* 
        Represents a type of hero, sorcerer, which has more dexterity and agility
    */
    private double str_ratio;
    private double dex_ratio;
    private double agi_ratio;

    public Sorcerer (String n,int ma, int str, int agi, int dex, int sm, int sexp){
        super(n,ma,str,agi,dex,sm,sexp);
        setclass("Sorcerer");
        str_ratio= 0.1;
        dex_ratio=0.3;
        agi_ratio=0.3;
    }
    public double getAgi_raito() {
        
        return this.agi_ratio;
    }
    public double getStr_raito() {
        
        return this.str_ratio;
    }
    public double getDex_raito() {
        
        return this.dex_ratio;
    }

}
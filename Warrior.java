public class Warrior extends Character{
        private double str_ratio;
        private double dex_ratio;
        private double agi_ratio;
        // private String whatclass;

        public Warrior (String n,int ma, int str, int agi, int dex, int sm, int sexp){
        super(n,ma,str,agi,dex,sm,sexp);
        setclass("Warrior");
        str_ratio= 0.3;
        dex_ratio=0.1;
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
public class Character_monster {
    /*
    A class that respresents what a monster and a hero should all have 
    */
    
    protected String name;
    protected int level;
    protected int hp;
    protected int totalhp;
    protected String pieceName="";
    protected int row;
    protected int col;
 
    public Character_monster(String name, int level){
        this.name=name;
        this.level= level;
        this.hp= 100*level;
        this.totalhp=hp;

    }

    public void setpiecename(String s){
        //set the piece for the hero/monster aka H1.M1,etc
        pieceName = s;
    }

    public String getPieceName(){
        return this.pieceName;
    }
    public void setcol(int c){
        col = c;
    }

    public void setrow(int r){
        row = r;
    }

    public int getrow(){
        return row;
    }

    public int getcol(){
        return col;
    }
}
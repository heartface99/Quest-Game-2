
import java.util.*;  
//class for Player which have their name, their piece representation, postion and the list of hero that they hold.
public class Player{
    private String name;
    private char player_representation;
    private ArrayList<Character> player_heroes;
    private int row;
    private int col;
 
    //constructor
    public Player(String name,char piece){
        this.name= name;
        this.player_representation=piece;
        this.player_heroes= new ArrayList<Character>();
    
    }
    //getters and setters
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    public void setRow(int r){
        this.row=r;
    }
    public void setCol(int c){
        this.col=c;
    }
    public ArrayList<Character> getlist(){
        return player_heroes;
    }
    public Character getChar(int i){
        return player_heroes.get(i);

    }
    public char return_playerpiece(){
        return this.player_representation;
    }
    public String return_playername(){
        return this.name;
    }
    public ArrayList<Character> returnHerolist(){
        return player_heroes;
    }

    public void add_hero(Character char1){
        this.player_heroes.add(char1);
    }
    //return the max hero level
    public int returnmaxlevel(){
        int maxlevel=0;
        for(int i = 0; i < player_heroes.size(); i++){
            Character current=player_heroes.get(i);
            if(current.getLevel()>maxlevel){
                maxlevel= current.getLevel();

            }

    }
    return maxlevel;
    }

    //padding for the printing of the hero to look neater
    public String pad (String input,int num_extended){
        int counter;
        while(input.length()<num_extended){
            counter=num_extended-input.length();
            input=input+" ";
            counter++;
            }
            return input;
        }
        
    //print the current status of all the hero the player have
    public void printlist(){
        
        Character current;
        String name;
        String mana;
        String curclass;
        String str;
        String agi;
        String dex;
        String sm;
        String sexp;
        String hp;
        System.out.println("Name"+ "                                " + "class"+ "            "+"hp"+ "                "+ "mana" + "             "+ "strength"+ "          "+ "agility"+ "        "+"dexterity"+ "        "+"Starting_Money"+ "        "+ "Starting_Experience");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        for(int i = 0; i < player_heroes.size(); i++){
            current= player_heroes.get(i);
            name= i+") "+current.getName();
            name=pad(name,25);
            hp= Integer.toString(current.getHp())+"/" + Integer.toString(current.getTotalHp());
            hp=pad(hp,10);
            mana= Integer.toString(current.getMana())+"/"+Integer.toString(current.getTotalMana());
            mana=pad(mana,10);
            curclass=current.getWhatClass();
            curclass=pad(curclass,9);
            str= Integer.toString(current.getStr());
            str=pad(str,7);
            agi= Integer.toString(current.getAgi());
            agi=pad(agi,7);
            dex=  Integer.toString(current.getDex());
            dex=pad(dex,13);
            sm=Integer.toString(current.getMoney());
            sm=pad(sm,20);
            sexp= Integer.toString(current.getExp())+"/"+ Integer.toString(current.gettotalExp());
            System.out.println(name+"          "+curclass + "        "+hp+"        "  + mana+ "           " + str+ "           " + agi +"       "+ dex+ "     "+ sm + "      " + sexp);
        }
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");

}

//another verison of print which only has the hero and money which is helpful just when player enters the market so they will know
//how much money they had. 
public void printheros(){
        
    Character current;
    String name;
    String sm;
   int i;
    System.out.println("Name"+ "                               " +"Money");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    for(i = 0; i < player_heroes.size(); i++){
        current= player_heroes.get(i);
        name= i+") "+current.getName();
        name=pad(name,25);
        
        sm=Integer.toString(current.getMoney());
        sm=pad(sm,20);
        
        System.out.println(name+"          "+ sm) ;
    }
    System.out.println(i+") Quit");
    System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");

}
//set the hero piece of each hero, in order in which they have been added. Hence H1,H2,H3.
//also sets their col and row so they spawn at nexus 
public void setHero_piece(int c){
    int counter=0;
    for(int i = 0; i < player_heroes.size(); i++){
        Character current= player_heroes.get(i);
        current.setpiecename("H"+ (i+1));
        current.setrow(counter);
        current.setcol(c-1);
        
        counter+=3;

    
}

}
}



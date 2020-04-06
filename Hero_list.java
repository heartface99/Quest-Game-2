 import java.util.*; 
 public class Hero_list{
    
    private ArrayList<Character> herolist;

    public Hero_list(){
        herolist= new ArrayList<Character>();
        initalize_paladin();
        initalize_warrior();
        initalize_sorcrer();

    }
    public ArrayList<Character> getlist(){
        return herolist;
    }
    public void initalize_paladin(){
        Paladin Solonor_Thelandira= new Paladin("Solonor_Thelandira",300,750,650,700,2500,7);
        Paladin Sehanine_Moonbow = new Paladin("Sehanine_Moonbow",300,750,700,700,2500,7);
        Paladin Skoraeus_Stonebones = new Paladin("Skoraeus_Stonebones",250,650,600,350,2500,4);
        Paladin Garl_Glittergold = new Paladin("Garl_Glittergold", 100,600,500,400,2500,5);
        herolist.add(Solonor_Thelandira);
        herolist.add(Sehanine_Moonbow);
        herolist.add(Skoraeus_Stonebones);
        herolist.add(Garl_Glittergold);
    }

    public void initalize_warrior(){
        Warrior Gaerdal_Ironhand = new Warrior("Gaerdal_Ironhand",100,700,500,600,1354,7);
        Warrior Sehanine_Monnbow = new Warrior("Sehanine_Monnbow",600,700,800,500,2500,8);
        Warrior Muamman_Duathall = new Warrior("Muamman_Duathall", 300,900,500,750,2546,6);
        Warrior Flandal_Steelskin  = new Warrior("Flandal_Steelskin",200,750,650,700,2500,7);
        herolist.add(Gaerdal_Ironhand );
        herolist.add(Sehanine_Monnbow);
        herolist.add(Muamman_Duathall);
        herolist.add(Flandal_Steelskin );
 
    }
    public void initalize_sorcrer(){
        Sorcerer Carl_GlitSilver= new Sorcerer("Carl_GlitSilver",700,550,600,500,2500,7);
        Sorcerer Rillifane_Rallathil= new Sorcerer("Rillifane_Rallathil",1300,750,450,500,2500,9);
        Sorcerer Segojan_Earthcaller = new Sorcerer("Segojan_Earthcaller",900,800,500,650,2500,5);
        Sorcerer Aues_Sorrowin= new Sorcerer("Aues_Sorrowin",800,850,600,450,2500,6); 
        herolist.add(Carl_GlitSilver);
        herolist.add(Rillifane_Rallathil);
        herolist.add(Segojan_Earthcaller);
        herolist.add(Aues_Sorrowin);
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
        int i;
        System.out.println("Name"+ "                                " + "class"+ "            "+"hp"+ "                "+ "mana" + "             "+ "strength"+ "          "+ "agility"+ "        "+"dexterity"+ "        "+"Starting_Money"+ "        "+ "Starting_Experience");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        for(i = 0; i < herolist.size(); i++){
            current= herolist.get(i);
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
    public void recover(ArrayList<Character> list){
       
        for(int i=0;i<list.size();i++){
            Character current= list.get(i);
            current.gainHp((int)(current.getTotalHp()*0.05));
            current.gainMana((int)(current.getTotalMana()*0.05));

        }

            

    }

    public ArrayList<Character> removenohp(ArrayList<Character> list, ArrayList<Character> deadcharacters){
        for(int i=0;i<list.size();i++){
           
            if(list.get(i).getHp()<=0){
                
                deadcharacters.add(list.get(i));
                list.remove(list.get(i));
            }
    }
        return list;

    }

    public void reset(ArrayList<Character> list){
        for(int i=0;i<list.size();i++){
            Character curr= list.get(i);
           if(curr.getHp()<=0){
               curr.add_hp(curr.totalhp/2);
            
            }
            curr.resettemp();
    }
}

    public  void win(ArrayList<Character> list){
        System.out.println("You have won the battle");
        for(int i=0;i<list.size();i++){
            list.get(i).gainMoney(150);
            list.get(i).gainedExp(2);
         

            if (list.get(i) instanceof Paladin) {
                Paladin curclass= (Paladin) list.get(i);
                list.get(i).levelUp(curclass.getAgi_raito(),curclass.getStr_raito(),curclass.getDex_raito());
            }
            else if (list.get(i) instanceof Sorcerer) {
                Sorcerer curclass= (Sorcerer) list.get(i);
                list.get(i).levelUp(curclass.getAgi_raito(),curclass.getStr_raito(),curclass.getDex_raito());
            }
            else if (list.get(i) instanceof Warrior) {
                Warrior curclass= (Warrior) list.get(i);
                list.get(i).levelUp(curclass.getAgi_raito(),curclass.getStr_raito(),curclass.getDex_raito());
            }
            
        }
    }


    public static void main (String[] args){
        Hero_list a= new Hero_list();
        a.printlist();
}

 }
import java.util.*;  
public class Monsterlist{
    
private ArrayList<Monster> monsters;

public Monsterlist(){
    monsters=new ArrayList<Monster> ();

    init_Monster();
}

public void init_Monster(){
    Monster Desghidorrah=new Monster("Desghidorrah",3,300,400,35,"Dragon");
    Monster Chrysophylax=new Monster("Chrysophylax",2,200,500,20,"Dragon");
    Monster BunsenBurner=new Monster("BunsenBurner",4,400,500,45,"Dragon");
    Monster Natsunomeryu=new Monster("Natsunomeryu",1,100,200,10,"Dragon");
    Monster TheScaleless=new Monster("TheScaleless", 7,700,600,75,"Dragon");
    Monster Kas_Ethelinh= new Monster("Kas_Ethelinh", 5,600,500,60,"Dragon");
    Monster Alexstraszan= new Monster("Alexstraszan", 10,1000,9000,55,"Dragon");
    Monster Phaarthurnax= new Monster("Phaarthurnax", 6,600,700,60,"Dragon");
    Monster D_Maleficent= new Monster("D_Maleficent", 9,900,950,85,"Dragon");
    Monster TheWeatherbe = new Monster("TheWeatherbe ", 8,800,900,80,"Dragon");

    monsters.add(Desghidorrah);
    monsters.add(Chrysophylax);
    monsters.add(BunsenBurner);
    monsters.add(Natsunomeryu);
    monsters.add(TheScaleless);
    monsters.add(Kas_Ethelinh);
    monsters.add(Alexstraszan);
    monsters.add(Phaarthurnax);
    monsters.add(D_Maleficent);
    monsters.add(TheWeatherbe);

    Monster Cyrrollalee= new Monster("Cyrrollalee",7,700,800,75,"Exoskeletons");
    Monster Brandobaris= new Monster("Brandobaris",3,350,450,30,"Exoskeletons");
    Monster BigBadWolf= new Monster("BigBadWolf",1,150,250,15,"Exoskeletons");
    Monster WickedWitch= new Monster("WickedWitch",2,250,350,25,"Exoskeletons");
    Monster Aasterinian= new Monster("Aasterinian",4,400,500,45,"Exoskeletons");
    Monster Chronepsish= new Monster("Chronepsish",6,650,750,60,"Exoskeletons");
    Monster Kiaransalee= new Monster("Kiaransalee",8,850,950,85,"Exoskeletons");
    Monster St_Shargaas= new Monster("St_Shargaas ",5,550,650,55,"Exoskeletons");
    Monster Merrshaullk= new Monster("Merrshaullk",10,1000,900,55,"Exoskeletons");
    Monster St_Yeenoghu= new Monster("St_Yeenoghu",9,950,850,90,"Exoskeletons");

    monsters.add(Cyrrollalee);
    monsters.add(Brandobaris);
    monsters.add(BigBadWolf);
    monsters.add(WickedWitch);
    monsters.add(Aasterinian);
    monsters.add(Chronepsish);
    monsters.add(Kiaransalee);
    monsters.add(St_Shargaas);  
    monsters.add(Merrshaullk);
    monsters.add(St_Yeenoghu);

    Monster Andrealphus= new Monster("Andrealphus",2,600,500,40,"Spirits");
    Monster Aim_Haborym= new Monster("Aim_Haborym",1,450,350,35,"Spirits");
    Monster Andromalius= new Monster("Andromalius",3,550,450,25,"Spirits");
    Monster Chiang_shih= new Monster("Chiang_shih",4,700,600,40,"Spirits");
    Monster FallenAngel= new Monster("FallenAngel",5,800,700,50,"Spirits");
    Monster Ereshkigall= new Monster("Ereshkigall",6,950,450,35,"Spirits");
    Monster Melchiresas= new Monster("Melchiresas",7,350,150,75,"Spirits");
    Monster Jormunngand= new Monster("Jormunngand",8,600,900,20,"Spirits");
    Monster Rakkshasass= new Monster("Rakkshasass",9,550,600,35,"Spirits");
    Monster Taltecuhtli= new Monster("Taltecuhtli",10,300,200,50,"Spirits");

    monsters.add(Andrealphus);
    monsters.add(Aim_Haborym);
    monsters.add(Andromalius);
    monsters.add(Chiang_shih);
    monsters.add(FallenAngel);
    monsters.add(Ereshkigall);
    monsters.add(Melchiresas);
    monsters.add(Jormunngand);
    monsters.add(Rakkshasass);
    monsters.add(Taltecuhtli);
    

}
     
    public ArrayList<Monster> matchLevel(int level,int num_monsters){
        ArrayList<Monster> monsterlist= new ArrayList<Monster> ();
        Monster current;
        int i;
        for(i = 0; i < monsters.size(); i++){
            current= monsters.get(i);
            if(current.getLevel()==level){
                monsterlist.add(current);
            }
    }
    Collections.shuffle(monsterlist);
    while(monsterlist.size()> num_monsters){
        monsterlist.remove(0);
    }

    return monsterlist;
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

    public void printMonster(ArrayList<Monster>x){
        String name;
        String hp;
        String level;
        String damage;
        String defense;
        String dodge;
        String type;
        int i;
        System.out.println("Name"+ "                               "+"hp" + "        "+"level"+"      "+"damage"+"     "+"defense"+"      "+"type");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        for(i = 0; i < x.size(); i++){
            Monster curr= x.get(i);
            name= curr.getname();
            name= pad(name,20);
            hp= curr.get_hp()+ "/"+curr.get_totalhp();
            hp=pad(hp,12);
            level= Integer.toString(curr.getLevel());
            level=pad(level,3);
            damage= Integer.toString(curr.getDamage());
            damage=pad(damage,5);
            defense= Integer.toString(curr.getDef());
            defense=pad(defense,5);
            dodge= Integer.toString(curr.getDodge());
            dodge= pad(dodge,5);
            type=curr.getType();
        
            System.out.println(i+") "+name+ "          "+hp+ "  "+level + "       "+damage+"       "+defense+"      "+type );
        }
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");

            
    }

    public ArrayList<Monster> getMonArray(){
        return this.monsters;
    }

    public ArrayList<Monster> removenohp(ArrayList<Monster> list,ArrayList<Monster> dead){
        for(int i=0;i<list.size();i++){
            if(list.get(i).get_hp()<=0){
                dead.add(list.get(i));
                list.remove(list.get(i));
            }
    }
        return list;
    }

   

    public void reset(ArrayList<Monster> list){
        monsters.addAll(list);
        for(int i=0;i<monsters.size();i++){
            Monster curr= monsters.get(i);
        
            curr.reset();
    }
}
    public static void main (String[] args){
        Monsterlist a= new Monsterlist();
        a.printMonster(a.monsters);
}


    }



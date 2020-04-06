import java.util.Random;
import java.util.*;
public class Play{
    private static Player currentplayer;
    private static Hero_list possiblehero= new Hero_list();
    public static Scanner scannername = new Scanner (System.in);
    private static int num_hero;
    private static Board playingboard;
    private static Monsterlist possiblemoster= new Monsterlist();
    // private static ArrayList<Character>current_heros;
    // private static ArrayList<Monster> current_monsters;
    private static ArrayList<Character> deadcharacters= new ArrayList<Character>();
    private static ArrayList<Monster> deadMonster= new ArrayList<Monster>();
    
    public static void add_hero(int x){

        while (valid_input2(x,possiblehero.getlist().size())==false)  {
            System.out.println("Incorrect input!" + "Please choose a number from 0-"+ (possiblehero.getlist().size()-1));
             scannername.nextLine();
            x= isInt();
        }
        Character added_hero= possiblehero.getlist().get(x);
        if(currentplayer.getlist().contains(added_hero)){
            System.out.println("You have already choosent that hero!" + "Please choose a number from 0-"+ (possiblehero.getlist().size()-1));
            scannername.nextLine();
            x= isInt();
            add_hero(x);
          
        }
        else{
            currentplayer.add_hero(added_hero);

        }

    }
    public static void adding_hero_final(int loop){
        while(loop >0){
            System.out.println("Please choose a number from 0-"+ (possiblehero.getlist().size()-1));
            scannername.nextLine();
            int choice= isInt();
            add_hero(choice);
           
            loop--;
            }

    }



    public static boolean valid_input(int x){
        if (x== 3 || x==2 || x ==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean valid_input2(int x,int bound){
        if ((x>=0) && (x<= bound)){
            return true;
        }
        else{
            return false;
        }
    
    }
    public static void buyPotion(Character currentchar){
        MainMarket market= playingboard.getMarket();
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the potions sold in the shop.Some potions are only for temporary effect and will effect will be gone after battle.");
        market.printPotion();
        System.out.print("Enter a number from 0-" + (market.returnPotionStore().size())+" to buy: ");
        scannername.nextLine();
        int y= isInt();
         while (valid_input2(y,market.returnPotionStore().size())== false){
                System.out.print("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                y= isInt();
            }
            if(y==(market.returnPotionStore().size())){
                Marketplace();
            }
            else if (y==(market.returnWeaponStore().size())==false){
                currentchar.buyPotion(market.getPotion(y));
                scannername.nextLine();
                System.out.println("Would you like to buy another potion? (y/n):");
                yesno = scannername.nextLine();
                yesno=yesno.toLowerCase();
                while(yesno.equals("y")==false&& yesno.equals("n")==false){
                    System.out.println("Invalid input! Enter (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                }
                if(yesno.equals("y")){
                    buyPotion(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace();
                }
            }
        }
    public static void buyWeapon(Character currentchar){
        MainMarket market= playingboard.getMarket();
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the weapons sold in the shop.");
        market.printWeapon();
        System.out.println("Enter a number from 0-" + (market.returnWeaponStore().size())+" to buy: ");
        scannername.nextLine();
        int y= isInt();
         while (valid_input2(y,market.returnWeaponStore().size())== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                y= isInt();
            }
            if(y==(market.returnWeaponStore().size())){
                Marketplace();
            }
            else if (y==(market.returnWeaponStore().size())==false){
                currentchar.buyWeapon(market.getWeapon(y));
                scannername.nextLine();
                System.out.println("Would you like to buy another weapon? (y/n):");
                yesno = scannername.nextLine();
                yesno=yesno.toLowerCase();
                while(yesno.equals("y")==false&& yesno.equals("n")==false){
                    System.out.println("Invalid input! Enter (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                }
                if(yesno.equals("y")){
                    buyWeapon(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace();
                }
            }
        }



    public static void buyArmor(Character currentchar){
        MainMarket market= playingboard.getMarket();
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the armors sold in the shop.");
        market.printArmor();
        System.out.println("Enter a number from 0-" + (market.returnArmorStore().size())+" to buy: ");
        scannername.nextLine();
        int y= isInt();
         while (valid_input2(y,market.returnArmorStore().size())== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                y= isInt();
            }
            if(y==(market.returnArmorStore().size())){
                Marketplace();
            }
            else if (y==(market.returnArmorStore().size())==false){
                currentchar.buyArmor(market.getArmor(y));
                scannername.nextLine();
                System.out.println("Would you like to buy another armor? (y/n):");
                yesno = scannername.nextLine();
                yesno=yesno.toLowerCase();
                while(yesno.equals("y")==false&& yesno.equals("n")==false){
                    System.out.println("Invalid input! Enter (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                }
                if(yesno.equals("y")){
                    buyArmor(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace();
                }
            }
        }

        public static void buySpell(Character currentchar){
            MainMarket market= playingboard.getMarket();
            String yesno; 
            System.out.println(currentchar.getName()+" ,here is all the spells sold in the shop.");
            System.out.println("Ice spell reduce enemey damage, fire spell reduce their defense, lightning spell reduce their dodge change!");
            market.printSpell();
            System.out.println("Enter a number from 0-" + (market.returnSpellStore().size())+" to buy: ");
            scannername.nextLine();
            int y= isInt();
             while (valid_input2(y,market.returnSpellStore().size())== false){
                    System.out.println("Incorrect input! Please choose a correct number: ");
                    scannername.nextLine();
                    y= isInt();
                }
                if(y==(market.returnSpellStore().size())){
                    Marketplace();
                }
                else if (y==(market.returnSpellStore().size())==false){
                    currentchar.buySpell(market.getSpell(y));
                    scannername.nextLine();
                    System.out.println("Would you like to buy another spell? (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                    while(yesno.equals("y")==false&& yesno.equals("n")==false){
                        System.out.println("Invalid input! Enter (y/n):");
                        yesno = scannername.nextLine();
                        yesno=yesno.toLowerCase();
                    }
                    if(yesno.equals("y")){
                        buySpell(currentchar);
                        }
                    else if (yesno.equals("n")){
                        Marketplace();
                    }
                }
            }
    
        public static void sellArmor(Character currentchar){
                String yesno; 
                System.out.println(currentchar.getName()+" ,here is all the armors owned by this hero.");
                currentchar.printArmorSale();
                System.out.println("Enter a number from 0-" + (currentchar.returnArmorStorage().size())+" to sell: ");
                scannername.nextLine();
                int y= isInt();
                 while (valid_input2(y,currentchar.returnArmorStorage().size())== false){
                        System.out.println("Incorrect input! Please choose a correct number: ");
                        scannername.nextLine();
                        y= isInt();
                    }
                    if(y==(currentchar.returnArmorStorage().size())){
                        Marketplace();
                    }
                    else if (y==(currentchar.returnArmorStorage().size())==false){
                        currentchar.sellArmor(currentchar.getArmor(y));
                        scannername.nextLine();
                        System.out.println("Would you like to sell another armor? (y/n):");
                        yesno = scannername.nextLine();
                        yesno=yesno.toLowerCase();
                        while(yesno.equals("y")==false&& yesno.equals("n")==false){
                            System.out.println("Invalid input! Enter (y/n):");
                            yesno = scannername.nextLine();
                            yesno=yesno.toLowerCase();
                        }
                        if(yesno.equals("y")){
                            sellArmor(currentchar);
                            }
                        else if (yesno.equals("n")){
                            Marketplace();
                        }
                    }
                }
        
    
            public static void sellPotion(Character currentchar){
            
                    String yesno; 
                    System.out.println(currentchar.getName()+" ,here is all the potions own by the hero.");
                    currentchar.printPotionSale();
                    System.out.println("Enter a number from 0-" + (currentchar.returnPotionStorage().size())+" to sell: ");
                    scannername.nextLine();
                    int y= isInt();
                     while (valid_input2(y,currentchar.returnPotionStorage().size())== false){
                            System.out.println("Incorrect input! Please choose a correct number: ");
                            scannername.nextLine();
                            y= isInt();
                        }
                        if(y==(currentchar.returnPotionStorage().size())){
                            Marketplace();
                        }
                        else if (y==(currentchar.returnPotionStorage().size())==false){
                            currentchar.sellPotion(currentchar.getPotion(y));
                            scannername.nextLine();
                            System.out.println("Would you like to sell another potion? (y/n):");
                            yesno = scannername.nextLine();
                            yesno=yesno.toLowerCase();
                            while(yesno.equals("y")==false&& yesno.equals("n")==false){
                                System.out.println("Invalid input! Enter (y/n):");
                                yesno = scannername.nextLine();
                                yesno=yesno.toLowerCase();
                            }
                            if(yesno.equals("y")){
                                sellPotion(currentchar);
                                }
                            else if (yesno.equals("n")){
                                Marketplace();
                            }
                        }
                    }

        public static void sellWeapon(Character currentchar){
                    
                    String yesno; 
                    System.out.println(currentchar.getName()+" ,here is all the weapons owned by the hero.");
                    currentchar.printWeaponSale();
                    System.out.println("Enter a number from 0-" + (currentchar.returnWeaponStorage().size())+" to sell: ");
                    scannername.nextLine();
                    int y= isInt();
                     while (valid_input2(y,currentchar.returnWeaponStorage().size())== false){
                            System.out.println("Incorrect input! Please choose a correct number: ");
                            scannername.nextLine();
                            y= isInt();
                        }
                        if(y==(currentchar.returnWeaponStorage().size())){
                            Marketplace();
                        }
                        else if (y==(currentchar.returnWeaponStorage().size())==false){
                            currentchar.sellWeapon(currentchar.getWeapon(y));
                            scannername.nextLine();
                            System.out.println("Would you like to sell another weapon? (y/n):");
                            yesno = scannername.nextLine();
                            yesno=yesno.toLowerCase();
                            while(yesno.equals("y")==false&& yesno.equals("n")==false){
                                System.out.println("Invalid input! Enter (y/n):");
                                yesno = scannername.nextLine();
                                yesno=yesno.toLowerCase();
                            }
                            if(yesno.equals("y")){
                                sellWeapon(currentchar);
                                }
                            else if (yesno.equals("n")){
                                Marketplace();
                            }
                        }
                    }
    public static void sellSpell(Character currentchar){
           
            String yesno; 
            System.out.println(currentchar.getName()+" ,here is all the spells own by the hero.");

            currentchar.printSpellSale();
            System.out.println("Enter a number from 0-" + (currentchar.returnSpellStorage().size())+" to sell: ");
            scannername.nextLine();
            int y= isInt();
            while (valid_input2(y,currentchar.returnSpellStorage().size())== false){
                    System.out.println("Incorrect input! Please choose a correct number: ");
                    scannername.nextLine();
                    y= isInt();
                            }
                    if(y==(currentchar.returnSpellStorage().size())){
                                Marketplace();
                            }
                    else if (y==(currentchar.returnSpellStorage().size())==false){
                        currentchar.sellSpell(currentchar.getSpell(y));
                        scannername.nextLine();
                        System.out.println("Would you like to sell another spell? (y/n):");
                        yesno = scannername.nextLine();
                        yesno=yesno.toLowerCase();
                        while(yesno.equals("y")==false&& yesno.equals("n")==false){
                            System.out.println("Invalid input! Enter (y/n):");
                            yesno = scannername.nextLine();
                            yesno=yesno.toLowerCase();
                                }
                            if(yesno.equals("y")){
                                sellSpell(currentchar);
                                    }
                            else if (yesno.equals("n")){
                                    Marketplace();
                                }
                            }
                        }
    public static void sell(int x, Character currentchar){
        if(x==0){
            sellArmor(currentchar);
        }
        else if(x==1){
            sellWeapon(currentchar);
        }
        else if (x==2){
            sellPotion(currentchar);
        }
        else if(x==3){
            sellSpell(currentchar);
        }


    }
    public static void buy(int x,Character currentchar){

        if(x==0){ 
            buyArmor(currentchar);
            }

        else if(x==1){
            buyWeapon(currentchar);
        }
        else if(x==2){
            buyPotion(currentchar);
        }
        else if(x==3){
            buySpell(currentchar);
        }
        
    }

    
  
    public static void Marketplace(){

        System.out.println("Hello, you have enter the market.Which of your hero(es) would like to take a look at our products first?");
        
        currentplayer.printheros();
        System.out.println("Enter a number from 0-"+ (num_hero)+" : ");
        int x= isInt();
        
        while (valid_input2(x,num_hero)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            x= isInt();
           
        }
        
        if(x== num_hero){
            System.out.println("Goodbye!Thanks for visting the market!");
            playingboard.printBoard();
            scannername.nextLine();
            actual_game();

        }
        else{
        Character currentchar= currentplayer.getChar(x);
        System.out.println(currentchar.getName()+" is now looking at the shop! Is " + currentchar.getName()+ " interested in buying or selling?");
        System.out.println("0) Buy");
        System.out.println("1) Sell");
        System.out.println("Enter a number from 0-1: ");
        scannername.nextLine();
        x= isInt();
        while (valid_input2(x,1)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            scannername.nextLine();
            x= isInt();
        }
        
        if(x==0){

        System.out.println();
        System.out.println("0) Armor Shop");
        System.out.println("1) Weapon Shop");
        System.out.println("2) Potion Shop");
        System.out.println("3) Spell Shop");
        System.out.println("4) Quit");
        System.out.println("Enter a number from 0-4: ");
        scannername.nextLine();
        x=isInt();
        while (valid_input2(x,4)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            scannername.nextLine();
            x= isInt();
        }
        if(x== 4){
            Marketplace();
        }
        else{
        buy(x,currentchar);
        }
    }

        else if(x==1){
            System.out.println();
            System.out.println("0) Sell Armor ");
            System.out.println("1) Sell Weapon ");
            System.out.println("2) Sell Potion ");
            System.out.println("3) Sell Spell");
            System.out.println("4) Quit");
            System.out.println("Enter a number from 0-4: ");
            scannername.nextLine();
            x=isInt();
            while (valid_input2(x,4)== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                x= isInt();
            }
            if(x== 4){
                Marketplace();
            }
            else{
            sell(x,currentchar);
        }
    }
    }
}
    public static void EquipWeapon(Character currentchar){
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the weapons owned by the hero.");
        currentchar.printWeaponSale();
        System.out.println("Enter a number from 0-" + (currentchar.returnWeaponStorage().size())+" to equip: ");
        scannername.nextLine();
        int y= isInt();
         while (valid_input2(y,currentchar.returnWeaponStorage().size())== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                y= isInt();
            }
            if(y==(currentchar.returnWeaponStorage().size())){
                inventory();
            }
            else if (y==(currentchar.returnWeaponStorage().size())==false){
                currentchar.equipWeapon(currentchar.getWeapon(y));
                scannername.nextLine();
                System.out.println("Would you like to equip a different weapon instead? (y/n):");
                yesno = scannername.nextLine();
                yesno=yesno.toLowerCase();
                while(yesno.equals("y")==false&& yesno.equals("n")==false){
                    System.out.println("Invalid input! Enter (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                }
                if(yesno.equals("y")){
                    EquipWeapon(currentchar);
                    }
                else if (yesno.equals("n")){
                    inventory();
                }
            }

    }
    public static void EquipSpell(Character currentchar){
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the spells own by the hero.");

        currentchar.printSpellSale();
        System.out.println("Enter a number from 0-" + (currentchar.returnSpellStorage().size())+" to equip: ");
        scannername.nextLine();
        int y= isInt();
        while (valid_input2(y,currentchar.returnSpellStorage().size())== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                y= isInt();
                        }
                if(y==(currentchar.returnSpellStorage().size())){
                            inventory();
                        }
                else if (y==(currentchar.returnSpellStorage().size())==false){
                    currentchar.equipSpell(currentchar.getSpell(y));
                    scannername.nextLine();
                    System.out.println("Would you like to equip another spell instead? (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                    while(yesno.equals("y")==false&& yesno.equals("n")==false){
                        System.out.println("Invalid input! Enter (y/n):");
                        yesno = scannername.nextLine();
                        yesno=yesno.toLowerCase();
                            }
                        if(yesno.equals("y")){
                            EquipSpell(currentchar);
                                }
                        else if (yesno.equals("n")){
                                inventory();
                            }
                        }
                    }

    public static void EquipArmor(Character currentchar){
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the armors owned by this hero.");
        currentchar.printArmorSale();
        System.out.println("Enter a number from 0-" + (currentchar.returnArmorStorage().size())+" to equip: ");
        scannername.nextLine();
        int y= isInt();
                 while (valid_input2(y,currentchar.returnArmorStorage().size())== false){
                        System.out.println("Incorrect input! Please choose a correct number: ");
                        scannername.nextLine();
                        y= isInt();
                    }
                    if(y==(currentchar.returnArmorStorage().size())){
                        inventory();
                    }
                    else if (y==(currentchar.returnArmorStorage().size())==false){
                        currentchar.equipArmor(currentchar.getArmor(y));
                        scannername.nextLine();
                        System.out.println("Would you like to equip a different armor instead? (y/n):");
                        yesno = scannername.nextLine();
                        yesno=yesno.toLowerCase();
                        while(yesno.equals("y")==false&& yesno.equals("n")==false){
                            System.out.println("Invalid input! Enter (y/n):");
                            yesno = scannername.nextLine();
                            yesno=yesno.toLowerCase();
                        }
                        if(yesno.equals("y")){
                            EquipArmor(currentchar);
                            }
                        else if (yesno.equals("n")){
                            inventory();
                        }
                    }

    }
    public static void usePotion_battle(Character currentchar,ArrayList<Monster> current_monsters){
        System.out.println(currentchar.getName()+" ,here is all the potions own by the hero.");
        currentchar.printPotionSale();
        System.out.println("Enter a number from 0-" + (currentchar.returnPotionStorage().size())+" to use: ");
        // scannername.nextLine();
        int y= isInt();
         while (valid_input2(y,currentchar.returnPotionStorage().size())== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                
                y= isInt();
                
            }
            if(y==(currentchar.returnPotionStorage().size())){
                playermakesmove(currentchar, current_monsters);
            }
            else if (y==(currentchar.returnPotionStorage().size())==false){
                currentchar.usePotion(currentchar.getPotion(y));
                scannername.nextLine();
              
            }
        

    }

    public static void usePotion(Character currentchar){
        String yesno; 
        System.out.println(currentchar.getName()+" ,here is all the potions own by the hero.");
        currentchar.printPotionSale();
        System.out.println("Enter a number from 0-" + (currentchar.returnPotionStorage().size())+" to use: ");
        scannername.nextLine();
        int y= isInt();
         while (valid_input2(y,currentchar.returnPotionStorage().size())== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                y= isInt();
            }
            if(y==(currentchar.returnPotionStorage().size())){
                inventory();
            }
            else if (y==(currentchar.returnPotionStorage().size())==false){
                currentchar.usePotion(currentchar.getPotion(y));
                scannername.nextLine();
                System.out.println("Would you like to use another potion? (y/n):");
                yesno = scannername.nextLine();
                yesno=yesno.toLowerCase();
                while(yesno.equals("y")==false&& yesno.equals("n")==false){
                    System.out.println("Invalid input! Enter (y/n):");
                    yesno = scannername.nextLine();
                    yesno=yesno.toLowerCase();
                }
                if(yesno.equals("y")){
                    usePotion(currentchar);
                    }
                else if (yesno.equals("n")){
                   inventory();
                }
            }
        

    }
    
    public static void Equip(int x,Character currentchar){
        if (x==0){
            EquipArmor(currentchar);

        }
        else if(x==1){
            EquipWeapon(currentchar);

        }
        else if(x==2){
            usePotion(currentchar);
        }
        else if (x==3){
            EquipSpell(currentchar);
        }

        else if(x==4){
            
            currentchar.unequipArmor();
            inventory();
            
        }
        else if (x==5){
            currentchar.unequipWeapon();
            inventory();
        }
        else if(x==6){
            currentchar.unequipSpell();
            inventory();
        }
       

    }
    public static void inventory(){
        System.out.println("Choose a hero to use/equip/unequip item!");
        
        currentplayer.printheros();
        System.out.println("Enter a number from 0-"+ (num_hero)+" : ");
        
        int x= isInt();
        while (valid_input2(x,num_hero)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            scannername.nextLine();
            x= isInt();
        }
        
        if(x== num_hero){
            playingboard.printBoard();
            scannername.nextLine();
            actual_game();

        }

        else{
            Character currentchar= currentplayer.getChar(x);
    
            System.out.println("What operations to perform?");
            System.out.println("0) Change Armor");
            System.out.println("1) Change Weapon");
            System.out.println("2) Use Potion");
            System.out.println("3) Change Spell");
            System.out.println("4) Unequip current Armor");
            System.out.println("5) Unequip current Weapon");
            System.out.println("6) Unequip current Spell");
            System.out.println("7) Quit");
            System.out.println("Enter a number from 0-7: ");
            scannername.nextLine();
            x=isInt();
            while (valid_input2(x,7)== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                scannername.nextLine();
                x= isInt();
            }
            if(x== 7){
                playingboard.printBoard();
                scannername.nextLine();
                actual_game();
            }
            else{
                Equip(x,currentchar);
            }
        }
    
            
    
        
    }
        


    

    public static void herostat(){
        currentplayer.printlist();
        System.out.print("Enter anything to return to map: ");
        String x= scannername.nextLine();
        playingboard.printBoard();
        actual_game();

    
    }
    public static Monster whichenemy(ArrayList<Monster> monsters){
           
        System.out.println("Which enemey do you want to target?");
      
        possiblemoster.printMonster(monsters);
        
        int x= isInt();
        scannername.nextLine();
        while (valid_input2(x,monsters.size()-1)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            // scannername.nextLine();
            x= isInt();
        }
        Monster choosenmonster= monsters.get(x);
        return choosenmonster;

    }
    public static void playermakesmove(Character currentchar,ArrayList<Monster> current_monsters){
        Monster choosen;
        System.out.println("Select a move for "+ currentchar.getName());
        System.out.println("0)Attack");
        System.out.println("1)Cast Spell");
        System.out.println("2)Use Potion");
        System.out.println("3)Check Enemy stats");
        System.out.println("4)Check Hero Stats");
        System.out.println("Enter a number from 0-4: ");
        int x= isInt();
        scannername.nextLine();
        while (valid_input2(x,4)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            scannername.nextLine();
            x= isInt();
            
        }

        if(x==0){
            choosen=whichenemy(current_monsters);
            currentchar.attack(choosen);
        }
        if(x==1){
            choosen=whichenemy(current_monsters);
            currentchar.magicattack(choosen);

        }
        if(x==2){
            usePotion_battle(currentchar, current_monsters);
        }
        if(x==3){
            possiblemoster.printMonster(current_monsters);
            playermakesmove(currentchar,current_monsters);
           
        }
        if(x==4){
            currentplayer.printlist();
            playermakesmove(currentchar,current_monsters);
        }
            

        }
        

    
    public static void monstermakesmove(Monster currentMonster,ArrayList<Character>current_heros){
        Random rand = new Random();
        int val = rand.nextInt(current_heros.size());
        Character heroattacked= current_heros.get(val);
        currentMonster.attackhero(heroattacked);
        

    }
    public static void round(int counter,ArrayList<Character>current_heros,ArrayList<Monster> current_monsters){
        


     if (current_monsters.size()==0&& current_heros.size()>0){
           possiblehero.win(current_heros);
           return;
          
       }
        else if(current_heros.size()==0){
            
           System.out.println("You have been defeated and had to ran away to heal up.");
            return;
       }
      
       while( (current_heros.size()>0) && (current_monsters.size()>0)){
            System.out.println("Round "+counter);

            for(int i=0;i<current_heros.size();i++){
            Character current = current_heros.get(i);
            current_monsters=possiblemoster.removenohp(current_monsters,deadMonster);
            if(current_monsters.size()==0){
                break;
            }
            playermakesmove(current,current_monsters);
            current_monsters=possiblemoster.removenohp(current_monsters,deadMonster);
            }
           
            for(int i=0;i<current_monsters.size();i++){
            current_heros=possiblehero.removenohp(current_heros,deadcharacters);
            if(current_heros.size()==0){
                break;
            }
            monstermakesmove(current_monsters.get(i),current_heros);
            current_heros=possiblehero.removenohp(current_heros,deadcharacters);
           
        }
       
        current_heros=possiblehero.removenohp(current_heros,deadcharacters);
        possiblehero.recover(current_heros);
        current_monsters=possiblemoster.removenohp(current_monsters,deadMonster);

        counter++;
        round(counter,current_heros,current_monsters);
    }
        


    }

    public static void battle(){
        Random rand = new Random();
        int val = rand.nextInt(100);
    

        if(val>=49){
        
        System.out.println("Oh no! You have been attacked!");
        ArrayList<Character>current_heros= currentplayer.returnHerolist();
        ArrayList<Monster> current_monsters= possiblemoster.matchLevel(currentplayer.returnmaxlevel(), num_hero);
        round(0,current_heros,current_monsters);
        System.out.println("Effect of any existing temporary potions have worn off.");
        currentplayer.returnHerolist().addAll(deadcharacters);      
        possiblehero.reset(currentplayer.returnHerolist());
        possiblemoster.reset(deadMonster);
        deadMonster.removeAll(deadMonster);
        deadcharacters.removeAll(deadcharacters);

        

        }

        }
        
        
    


    public static void whattodonext(String instruction){
        char playpiece= currentplayer.return_playerpiece();
        int currentrow=currentplayer.getRow();
        int currentcol=currentplayer.getCol();
        int nextrow;
        int nextcol;
        if(instruction.equals("a")){
            nextrow=currentrow;
            nextcol= currentcol-1;
        
            if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="OK"){
            playingboard.setPiece(currentrow,currentcol,' ');
            currentplayer.setRow(nextrow);
            currentplayer.setCol(nextcol);
            battle();
            
            
            }
            else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="M"){
                
                Marketplace();

            }
            else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="X"){
                actual_game_wronginput();
            }
        }
          
        if(instruction.equals("w")){
            nextrow=currentrow-1;
            nextcol= currentcol;
            if (playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="OK"){
            playingboard.setPiece(currentrow,currentcol,' ');
            currentplayer.setRow(nextrow);
            currentplayer.setCol(nextcol);
            battle();
           
            }
            else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="M"){
                Marketplace();

            }
            else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="X") {
                actual_game_wronginput();
            }
        }
            
        if(instruction.equals("s")){
            nextrow=currentrow+1;
            nextcol= currentcol;
            if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="OK"){
            playingboard.setPiece(currentrow,currentcol,' ');
            currentplayer.setRow(nextrow);
            currentplayer.setCol(nextcol);
            battle();
            
            }
            else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="M"){
            
                Marketplace();

            }

        else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="X"){
            actual_game_wronginput();
        }
    }
       
        if(instruction.equals("d")){
            nextrow=currentrow;
            nextcol= currentcol+1;
            if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="OK"){
            playingboard.setPiece(currentrow,currentcol,' ');
            currentplayer.setRow(nextrow);
            currentplayer.setCol(nextcol);
            battle();
        }
        else if(playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="M"){
         
            Marketplace();
        }
        else if (playingboard.movingtoempyspace(nextrow,nextcol,playpiece)=="X"){
            actual_game_wronginput();
        }

    }
    if(instruction.equals("t")){
        herostat();

    }
    if(instruction.equals("i")){
        inventory();
    }

    if(instruction.equals("r")){
        playingboard.intialize_board();
        currentplayer.setCol(8/2);
        currentplayer.setRow(8/2);
        playingboard.SetPlayerPiece(currentplayer.return_playerpiece());
        
    }
    if(instruction.equals("q")){
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
        playingboard.printBoard();
        
        actual_game();
        // if(instruction.equals("e")){

        // }
        // if(instruction.equals("t")){
    
        // }

    }
    public static void valid_input3(String x){
        // System.out.println((x.equals("w")==false|| x.equals("a")==false|| x.equals("s")==false ||x.equals("d")==false||x.equals("q")==false||x.equals("t")==false||x.equals("e")==false));
        // System.out.println(true||false);
        while(x.equals("w")==false&&x.equals("a")==false&& x.equals("s")==false &&x.equals("d")==false&&x.equals("q")==false&&x.equals("t")==false&&x.equals("i")==false&& x.equals("r")==false){
        //  while(x.equals("a")==false||x.equals("w")==false){
            System.out.print("Invalid input! Enter your moves: ");

            x= scannername.nextLine();
            x=x.toLowerCase();
        }
        whattodonext(x);
    }

    public static void actual_game_wronginput(){
        System.out.print("Invalid move! Enter your moves again: ");
        String x;
        x= scannername.nextLine();
        x=x.toLowerCase();
        // System.out.println(x.equals("a"));
        valid_input3(x);
    }
    
    public static void actual_game(){
        // scannername.nextLine();
        System.out.print("Enter your moves: ");
        String x;
        
        // scannername.nextLine();
        x= scannername.nextLine();
        // System.out.println(x);
        x=x.toLowerCase();
        
        // System.out.println(x.equals("a"));
        valid_input3(x);


        


    }

    public static int isInt(){
        
        while (!scannername.hasNextInt()) {
            System.out.println("Input is not a number.");
            scannername.nextLine();
          }
          
          int number = scannername.nextInt();
         
          return number;
    
    }
    
    public static void introduction_setup(){
        System.out.println("The land of Java was taken over by the evil beasts.");
        System.out.println("In the middle of chaos, you were choosen to save the world.");
        System.out.println("You will lead the team of heroes to bring peace for the kingdom");
        System.out.println("Tell me us your name, before your adventure begins");
        System.out.println();
        
        System.out.print("Please enter your name:");
        String name = scannername.next();
        currentplayer= new Player(name,'O');
        System.out.println();
        System.out.println(name + " ,we have choosen some of the brightest strongest heroes in the whole kingdom to aid you!");
        System.out.println("Its important to know having too much teammates will lead to more monsters being alerted and attack your team!");
        System.out.println("You can choose to lead with 1,2,3 hero(s)");
        System.out.println( "Choose the number of heroes you want on your team(1-3): ");

        scannername.nextLine();
        num_hero =isInt();
        
        
        while (valid_input(num_hero)== false){
            System.out.print("Incorrect input! Please choose a number from 1-3: ");
            scannername.nextLine();
            num_hero= isInt();
        }
        System.out.println ("Great! You will now get to choose "+ num_hero +" companions!");
        System.out.println("You might want to decide who to pick with you base on their class!");
        System.out.println("Warriors are favored on the strength and the agility.");
        System.out.println("Sorcerers are favored on the dexterity and the agility.");
        System.out.println("Paladins are favored on the strength and the dexterity.");
        System.out.println("Here are the list of possible heros and their information! Remember you can choose at max " + num_hero + " hero(es)!");
        System.out.println();
        possiblehero.printlist();
        System.out.println();
        adding_hero_final(num_hero);
        System.out.println();
        System.out.println("Here is the current state of all your memebers of the team!");
        currentplayer.printlist();
        System.out.println();
        System.out.println("Your adventure will begin now!");
        System.out.println("Move using W(up),S(down),A(left),D(right)");
        System.out.println("You cannot move to terrain(X), but can visit market(M) to buy and sell stuff. All others are the wild that will have a chance to spawn monsters! Beware!");
        System.out.println("Check your hero stats using T");
        System.out.println("Check inventory to equip and use items using I");
        System.out.println("To be relocated to a new map use R");
        System.out.println("Close the game using Q");
        scannername.nextLine();
        playingboard= new Board(8,8);
        currentplayer.setCol(8/2);
        currentplayer.setRow(8/2);
        playingboard.SetPlayerPiece(currentplayer.return_playerpiece());
        playingboard.printBoard();
        actual_game();
        


        
    }
    
    // public static void main (String[] args){
    //     introduction_setup();
    // } 


    
}
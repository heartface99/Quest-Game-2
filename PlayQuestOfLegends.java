import java.util.*;

public class PlayQuestOfLegends extends Play {
    /*
        This class represents the logic of the Quest of legends game. It shares some qualities with the regular 
        quest game, which is why it extends it.
    */
	private static ArrayList<Character> heroes = new ArrayList<Character>();
    private static ArrayList<Monster> monsters = new ArrayList<Monster>();
    private static NexusBoard playingboard;
    protected static MainMarket market = new MainMarket();

    public static void start(){
        //introduction to set up game by choosing your heros. 
        num_hero = 3;
        System.out.println("Welcome to the Quest of Legend.");
        System.out.println("In the middle of chaos, you were choosen to save the world.");
        System.out.println("You will lead the team of heroes to bring peace for the kingdom");
        System.out.println("Tell me us your name, before your adventure begins");
        System.out.println();
        
        System.out.print("Please enter your name:");
        String name = scannername.next();
        currentplayer = new Player(name,'O');
        System.out.println();
        System.out.println(name + " ,we have choosen some of the brightest strongest heroes in the whole kingdom to aid you!");
        System.out.println("Since you have 3 nexus to defend from the monster, please choose your 3 heros!");
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
        System.out.println("To teleport to a new map use M");
        System.out.println("Close the game using Q");
        scannername.nextLine();
        //set up the board, monster, and heros
        playingboard= new NexusBoard(8,8); 
        monsters.addAll(possiblemoster.matchLevel(currentplayer.returnmaxlevel(),num_hero));
        heroes.addAll(currentplayer.returnHerolist());
        initalizeHeroPostion(8);
        initalizeMonsterPostion();
        playingboard.printBoard();
        play();
    }


    //after user choose what to do next, you elaborate on what their choices can be
    public static void chosenMove(Character curr, int i){
        //player choose to make a move. 
        boolean loop= true;
        switch (i) {
            case 1:
                while(loop){
                    System.out.println("Make a move(w,s,a,d): ");
                    String x= scannername.nextLine();
                    x=x.toLowerCase();
                    String instruction = valid_move(x);
                    loop = makeMove(x,curr);
                }
                return;
            case 2:
                attack(curr);
                curr.resettemp();
                return;
            case 3:
                cast_spell(curr);
                curr.resettemp();
                return;
            case 4:
                inventory(curr);
                return;
            case 5:
                return_base(curr);
                return;
            case 6:
                playingboard.printTeleportOptions(curr);
                while(loop){
                    System.out.println("Which cell do you want to transport to?");
                    int cell = isInt();
                    cell = validTeleport(cell, curr);
                    loop = teleport(curr, cell);
                }
                return;
            case 7:
                currentplayer.printlist();

                output_choice(curr);
            case 8:
                possiblemoster.printMonster(monsters);
                output_choice(curr);
                return;
            case 9:
                Marketplace(curr);

        }
    }
    //check if you type in the instructions that is allowed in game.
    public static String valid_move(String x) {
        while(x.equals("w")==false&&x.equals("a")==false&& x.equals("s")==false &&x.equals("d")==false){
        //  while(x.equals("a")==false||x.equals("w")==false){
            System.out.print("Invalid input! Enter your moves: ");

            x= scannername.nextLine();
            x=x.toLowerCase();
        }
        return x;
    }

    public static int validTeleport(int cell, Character currHero) {
        // validates the cell that the hero wants to teleport to: not in the same lane
        Tile t = playingboard.getTileFromTeleport(cell);
        while (cell < 0 || cell > 47 || t.getCol() == currHero.getcol()) {
             System.out.print("Invalid input! Make sure that you enter a number between 0 and 47 and that it is a valid cell to transport to: ");
             scannername.nextLine();
             cell = isInt();
             t = playingboard.getTileFromTeleport(cell);
        }
        return cell;
    }

    public static boolean teleport(Character curr, int cell) {
        //  Hero who is currently in one lane can teleport to another in order to help a fellow hero in need. 
        // A player chooses to teleport one of the heroes from one lane to another at any time.
    
        Tile t = playingboard.getTileFromTeleport(cell);
        if(playingboard.movingtoempyspace(t.getRow(),t.getCol(), curr)=="OK"){
            curr.row = t.getRow();
            curr.col = t.getCol();
            return false;
        } return true;
        
    }

    //allowing players to buy potion
    public static void buyPotion1(Character currentchar){
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
                Marketplace(currentchar);
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
                    buyPotion1(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace(currentchar);
                }
            }
        }

    //allowing players to buy weapon
    public static void buyWeapon1(Character currentchar){
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
                Marketplace(currentchar);
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
                    buyWeapon1(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace(currentchar);
                }
            }
        }



    //allowing players to buy armor
    public static void buyArmor1(Character currentchar){
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
                Marketplace(currentchar);
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
                    buyArmor1(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace(currentchar);
                }
            }
        }
        //allowing player to buy spell
        public static void buySpell1(Character currentchar){
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
                    Marketplace(currentchar);
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
                        buySpell1(currentchar);
                        }
                    else if (yesno.equals("n")){
                        Marketplace(currentchar);
                    }
                }
            }

    //allowing player to sell armor
    public static void sellArmor1(Character currentchar){
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
                Marketplace(currentchar);
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
                    sellArmor1(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace(currentchar);
                }
            }
        }

    //allowing player to sell potion
    public static void sellPotion1(Character currentchar){

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
                    Marketplace(currentchar);
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
                        sellPotion1(currentchar);
                        }
                    else if (yesno.equals("n")){
                        Marketplace(currentchar);
                    }
                }
            }

    //allowing player to sell weapon
    public static void sellWeapon1(Character currentchar){
                
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
                Marketplace(currentchar);
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
                    sellWeapon1(currentchar);
                    }
                else if (yesno.equals("n")){
                    Marketplace(currentchar);
                }
            }
        }

    //allowing player to sell spell
    public static void sellSpell1(Character currentchar){
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
                        Marketplace(currentchar);
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
                        sellSpell1(currentchar);
                            }
                    else if (yesno.equals("n")){
                            Marketplace(currentchar);
                        }
                    }
                }

    //players access the buy function 
    public static void buy1(int x,Character currentchar){

        if(x==0){ 
            buyArmor1(currentchar);
            }

        else if(x==1){
            buyWeapon1(currentchar);
        }
        else if(x==2){
            buyPotion1(currentchar);
        }
        else if(x==3){
            buySpell1(currentchar);
        }
        
    }

    //players access different inventory to sell their item
    public static void sell1(int x, Character currentchar){
        if(x==0){
            sellArmor1(currentchar);
        }
        else if(x==1){
            sellWeapon1(currentchar);
        }
        else if (x==2){
            sellPotion1(currentchar);
        }
        else if(x==3){
            sellSpell1(currentchar);
        }


    }
    //calls equiping method
    public static void Equip1(int x,Character currentchar){
        if (x==0){
            EquipArmor1(currentchar);

        }
        else if(x==1){
            EquipWeapon1(currentchar);

        }
        else if(x==2){
            usePotion1(currentchar);
        }
        else if (x==3){
            EquipSpell1(currentchar);
        }

        else if(x==4){
            
            currentchar.unequipArmor();
            inventory(currentchar);
            
        }
        else if (x==5){
            currentchar.unequipWeapon();
            inventory(currentchar);
        }
        else if(x==6){
            currentchar.unequipSpell();
            inventory(currentchar );
        }
    }
 


    //acessing the market place only if the hero is at the market!
    //player can choose to buy or sell in the market place. 
    public static void Marketplace(Character currentchar){
        if(playingboard.check_tile(currentchar.row,currentchar.col)=='N'){
        
        int x;
        System.out.println(currentchar.getName()+" is now looking at the shop! Is " + currentchar.getName()+ " interested in buying or selling?");
        System.out.println(currentchar.getName()+" currently has " + currentchar.getMoney() +" gold!");
        System.out.println("0) Buy");
        System.out.println("1) Sell");
        System.out.println("2) Quit");
        System.out.println("Enter a number from 0-2: ");
        
        x= isInt();
        while (valid_input2(x,2)== false){
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
            Marketplace(currentchar);
        }
        else{
        buy1(x,currentchar);
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
               output_choice(currentchar);;
            }
            else{
            sell1(x,currentchar);
        }
        }
        //if player quits they use up their move
        else if(x==2){
            return;
        }
        }
        else{
            System.out.println("You can only access market at the nexus! Choose another move!");
            output_choice(currentchar);
        }
    }

//allow user to equip weapons for a hero
public static void EquipWeapon1(Character currentchar){
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
            inventory(currentchar);
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
                EquipWeapon1(currentchar);
                }
            else if (yesno.equals("n")){
                inventory(currentchar);
            }
        }
}

//equip spell of a hero
public static void EquipSpell1(Character currentchar){
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
                        inventory(currentchar);
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
                        EquipSpell1(currentchar);
                            }
                    else if (yesno.equals("n")){
                            inventory(currentchar);
                        }
                    }
                }

//equip armor for a hero
public static void EquipArmor1(Character currentchar){
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
            inventory(currentchar);
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
                EquipArmor1(currentchar);
                }
            else if (yesno.equals("n")){
                inventory(currentchar);
            }
        }
}


//allow a hero to use potion
public static void usePotion1(Character currentchar){
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
            inventory(currentchar);
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
                usePotion1(currentchar);
                }
            else if (yesno.equals("n")){
               inventory(currentchar);
            }
        }
    }
    


//access the inventory of the character 
    public static void inventory(Character currentchar){
        System.out.println(currentchar.getName()+ " can now access the inventory.");
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
       
        int x;
        x=isInt();
        while (valid_input2(x,7)== false){
            System.out.println("Incorrect input! Please choose a correct number: ");
            scannername.nextLine();
            x= isInt();
        }
        if (x== 7){
            scannername.nextLine();
            return;
            // actual_game();
        }
        else{
            Equip1(x,currentchar);
        }
    }

    
    //ask player which monster they want to attack 
    public static void attack(Character curr){
        ArrayList <Monster> monster_inrange= new ArrayList <Monster> ();
        monster_inrange= playingboard.monster_in_range(curr.row,curr.col);
        if(monster_inrange.size() >0){
            System.out.println("Which enemey do you want to target?");
      
            possiblemoster.printMonster(monster_inrange);
            
            int x= isInt();
            scannername.nextLine();
            while (valid_input2(x,monster_inrange.size()-1)== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                // scannername.nextLine();
                x= isInt();
            }
            Monster choosenmonster = monsters.get(x);
            curr.attack(choosenmonster);

            //remove hero from board when its dead

            if(choosenmonster.get_hp()<= 0){
                monsters.remove(choosenmonster);
                playingboard.remove_piece(choosenmonster.row,choosenmonster.col);
                possiblehero.win_single(curr);
                possiblemoster.init_Monster();
                

            }
            
            

        }
        else{
            System.out.println("There is no monster to attack! Please choose a different move!");
            output_choice(curr);
        }
    }

     //ask player which monster they want to cast a spell on
     public static void cast_spell (Character curr){
        ArrayList <Monster> monster_inrange= new ArrayList <Monster> ();
        monster_inrange= playingboard.monster_in_range(curr.row,curr.col);
        if(monster_inrange.size() >0){
            System.out.println("Which enemey do you want to target?");
      
            possiblemoster.printMonster(monster_inrange);
            
            int x= isInt();
            scannername.nextLine();
            while (valid_input2(x,monster_inrange.size()-1)== false){
                System.out.println("Incorrect input! Please choose a correct number: ");
                // scannername.nextLine();
                x= isInt();
            }
            Monster choosenmonster = monsters.get(x);
            curr.magicattack(choosenmonster);
            if(choosenmonster.get_hp()<= 0){
                monsters.remove(choosenmonster);
                playingboard.remove_piece(choosenmonster.row,choosenmonster.col);
                possiblehero.win_single(curr);
                possiblemoster.init_Monster();
                return;

            }

        }
        else{
            System.out.println("There is no monster to attack! Please choose a different move!");
            output_choice(curr);
        }
    }

    //moves the player by w,a,s,d and check if that move is viable.
    public static boolean makeMove(String instruction, Character curr){
        // makes the move on the board according to the instructions and the current monster
        int currentrow=curr.row;
        int currentcol=curr.col;
        int nextrow;
        int nextcol;
        if(instruction.equals("a")){
            //check if there is a monster right by you, if there is you shall not pass unless 
            
          
            nextrow=currentrow-1;
            nextcol= currentcol;
        
            if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                curr.row=(nextrow);
                curr.col=(nextcol);
                return false;
            }

            else if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="X"){ 
                System.out.println("Cannot move there!");
                return true;

            }
        }
        
        
        if(instruction.equals("w")){
            nextrow=currentrow;
            nextcol= currentcol-1;
            if(playingboard.can_move_ahead(currentrow,currentcol)== true){
            // System.out.println((playingboard.movingtoempyspace(nextrow,nextcol,curr)));
            if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                curr.row=(nextrow);
                curr.col=(nextcol);
            return false;
            
            }
            else if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="X"){
                System.out.println("Cannot move there!");
                return true;
            }
            
        }
        else{
            System.out.println("You must defeat the monster before moving ahead!");
            output_choice(curr);
            return false;
        }
        }

        
        
        if(instruction.equals("s")){
            nextrow=currentrow;
            nextcol= currentcol+1;
        
            if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                curr.row=(nextrow);
                curr.col=(nextcol);
            return false;
            
            }
           

            
            else if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="X"){
                System.out.println("Cannot move there!");
                return true;
            }
        }
            
            if(instruction.equals("d")){
                nextrow=currentrow+1;
                nextcol= currentcol;
            
                if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                    curr.row=(nextrow);
                    curr.col=(nextcol);
                   
                return false;
                
                }
                else if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="X"){
                    System.out.println("Cannot move there!");
                    return true;
                }
            }

        return true;
        }
    
    //return the player to base(base on their lane). 
    public static void return_base(Character curr){
        int base_row = curr.row;
        int base_col = 7;

        if(playingboard.movingtoempyspace(base_row,base_col,curr)== "OK"){
            curr.row=(base_row);
            curr.col=(base_col);
            
            return;

        }
        else{
            System.out.println("There is someone at base already!");
            output_choice(curr);
        }


    }

    //output the move a player can choose
    public static void output_choice(Character curr){
        int x;
        System.out.println("Enter moves for "+ curr.getName()+ "("+curr.getPieceName()+")" +" : ");
        System.out.println("1) Move");
        System.out.println("2) Attack");
        System.out.println("3) Cast spell");
        System.out.println("4) Use inventory");
        System.out.println("5) Return to base");
        System.out.println("6) Teleport to another lane");
        System.out.println("7) Check hero stats ");
        System.out.println("8) Check monster stats ");
        System.out.println("9) Access Market");
        System.out.println("Choose a number from 1-9: ");
        x=isInt();

        while (valid_input2(x,9)== false){
        System.out.println("Incorrect input! Please choose a correct number: ");
        scannername.nextLine();
        x= isInt();
       
            } 
        scannername.nextLine();
        chosenMove(curr,x);
    }

    //monster makes a move.
    public static void monster_move(Monster curr){
        //monster will attack if there is a hero in range
    
        ArrayList<Character> hero_inrange= new ArrayList<Character>();
        Character heroattacked;
        hero_inrange=playingboard.hero_in_range(curr.row,curr.col);
        System.out.println("current hero size is"+ hero_inrange.size());
        if(hero_inrange.size()>0){
        
            Random rand = new Random();
            int val = rand.nextInt(hero_inrange.size());
            heroattacked=hero_inrange.get(val);
            curr.attack(heroattacked);
        
        //if hero is dead, he is reset to the base
        if(heroattacked.getHp()<= 0){
            heroattacked.resettemp();
            return_base(heroattacked);
        }
    }
        //if no hero in range, it moves forward one block
        else {
           
            playingboard.movingtoempyspace(curr.row, curr.col+1,curr);
            curr.col=curr.col+1;
        }

    }
    //give boost to character base on the tile
    public static void check_tile(Character curr){
        //cave increase agi by 10%
        if(playingboard.check_tile(curr.row,curr.col)=='C'){
            curr.tile_add(0,((int)0.10*curr.getagi_only()),0);
        }
         //bush increase dex by 10%
         if(playingboard.check_tile(curr.row,curr.col)=='B'){
            curr.tile_add(0,0,((int)0.10*curr.getdex_only()));
        }
        //kulou increase str by 10%
        if(playingboard.check_tile(curr.row,curr.col)=='K'){
            curr.tile_add(((int)0.10*curr.getstr_only()),0,0);
        }


    }

	public static void play(){
        // previously "actual_game"
        //ask the user for their instructions on what they would like to do next
        char celltype;
        int x;
        boolean won=false;
        int counter=0;

        while(playingboard.win()== false){
            
            for(int i = 0; i <heroes.size(); i++){
              Character curr= heroes.get(i);
                //clear tile boost and update it 
                curr.reset_tile();
                check_tile(curr);

                output_choice(curr);
                //clear tile boost and update it 
                curr.reset_tile();
                check_tile(curr);
                if(playingboard.win() == true){
                    won=true;
                    break;
                }
                playingboard.printBoard();
            }

            if(won== false){
                for(int i = 0; i <monsters.size(); i++){
                      Monster currmonster= monsters.get(i);
                      monster_move(currmonster);
                }
                playingboard.printBoard();
                
            }
            if (counter % 9==0) {
                // spawn new monsters
                ArrayList<Monster> newMonsters = possiblemoster.matchLevel(currentplayer.returnmaxlevel(), num_hero);
                monsters.addAll(newMonsters);
                respawnMonsters(newMonsters);
            }
            //recovers the heros after a round
            possiblehero.recover(heroes);
            counter++;
        }
    }


    //initialzie the starting postion of HERO
    public static void initalizeHeroPostion(int c){
        Character_monster curr;
        int counter=0;
        for(int i = 0; i <heroes.size(); i++){
          curr= heroes.get(i);
          curr.setpiecename("H"+ (i+1));
          curr.setrow(counter);
          curr.setcol(c-1);
          counter+=3;
          set_postion(curr,curr.row,curr.col);
    	}
	}

    //set the postion of monster and heros on the board given the row and col
    public static void set_postion(Character_monster current, int row, int col){
        playingboard.move(row,col,current);
    }

	//intitlize the starting positon of Monster 
    public static void initalizeMonsterPostion(){
        int counter=0;
        int lane=0;
        int start=0;
        Character_monster curr;

        for(int i = 0; i <monsters.size(); i++){
          curr = monsters.get(i);
          curr.setpiecename("M"+ (i+1));
          curr.setrow(counter);
          curr.setcol(lane);
          counter+=3;
          set_postion(curr, curr.row, curr.col);
    	}
	}

//intitlize the starting positon of Monster 
    public static void respawnMonsters(ArrayList<Monster> newMonsters){
        int r = 0;
        Character_monster m;
        for(int i=0; i<newMonsters.size(); i++){
          m = newMonsters.get(i);
          m.setpiecename("M"+ (monsters.size() - 2 + i));
          m.setrow(r);
          m.setcol(0);
          set_postion(m, m.row, m.col);
          r+=3;
        }
    }    
}

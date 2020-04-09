import java.util.*;

public class PlayQuestOfLegends extends Play{
	private static ArrayList<Character> heroes;
    private static ArrayList<Monster> monsters;
    private static NexusBoard playingboard;

    //introduction to set up game by choosing your heros. 
    public static void start(){
        //previously introduction setup
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
        monsters=possiblemoster.matchLevel(currentplayer.returnmaxlevel(),num_hero);
        heroes= currentplayer.returnHerolist();
        // initalizeMonsterPostion();
        initalizeHeroPostion(8);
        initalizeMonsterPostion();
        playingboard.printBoard();
        play();
    }

    //check if you type in the instructions that is allowed in game.
    public static String valid_move(String x) {
        // System.out.println((x.equals("w")==false|| x.equals("a")==false|| x.equals("s")==false ||x.equals("d")==false||x.equals("q")==false||x.equals("t")==false||x.equals("e")==false));
        // System.out.println(true||false);
        while(x.equals("w")==false&&x.equals("a")==false&& x.equals("s")==false &&x.equals("d")==false){
        //  while(x.equals("a")==false||x.equals("w")==false){
            System.out.print("Invalid input! Enter your moves: ");

            x= scannername.nextLine();
            x=x.toLowerCase();
        }
        return x;
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
                String instruction = valid_move(x);
                loop = makeMove(x,curr);
                }
                playingboard.printBoard();
                return;
            case 2:
                attack(curr);
                return;
            case 3:
                System.out.println("not implemented yet");
                return;
            case 4:
                inventory();
                return;
            case 5:
                System.out.println("not implemented yet");
                return;
            case 6:
                System.out.println("not implemented yet");
                return;
            case 7:
                currentplayer.printlist();
                return;
            case 8:
                possiblemoster.printMonster(monsters);
                return;
            case 9:
                Marketplace();
                return;
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

        }
        else{
            System.out.println("There is no monster to attack! Please choose a different move!");
            output_choice(curr);
        }
    }

    public static boolean makeMove(String instruction, Character curr){
        // makes the move on the board according to the instructions and the current monster
        int currentrow=curr.row;
        int currentcol=curr.col;
        int nextrow;
        int nextcol;
        if(instruction.equals("a")){
            nextrow=currentrow-1;
            nextcol= currentcol;
        
            if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                curr.row=(nextrow);
                curr.col=(nextcol);
                playingboard.printBoard();
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
            System.out.println(curr.row+" "+curr.col);
            System.out.println(nextrow+" "+ nextcol);
            // System.out.println((playingboard.movingtoempyspace(nextrow,nextcol,curr)));
            if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                curr.row=(nextrow);
                curr.col=(nextcol);
                playingboard.printBoard();
            return false;
            
            }
           

            
            else if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="X"){
                System.out.println("Cannot move there!");
                return true;
            }
            
        }
        if(instruction.equals("s")){
            nextrow=currentrow;
            nextcol= currentcol+1;
        
            if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="OK"){
                curr.row=(nextrow);
                curr.col=(nextcol);
                playingboard.printBoard();
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
                    playingboard.printBoard();
                   
                return false;
                
                }
               
    
                
                else if(playingboard.movingtoempyspace(nextrow,nextcol,curr)=="X"){
                    System.out.println("Cannot move there!");
                    return true;
                }
            }
        return true;
        }

    //output the move a player can choose
    public static void output_choice(Character curr){
        int x;
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
        hero_inrange=playingboard.hero_in_range(curr.row,curr.col);
        if(hero_inrange.size()>0){
        
            Random rand = new Random();
            int val = rand.nextInt(hero_inrange.size());
            Character heroattacked=hero_inrange.get(val);
            curr.attackhero(heroattacked);
        }
        //if no hero in range, it moves forward one block
        else {
          
           
            playingboard.movingtoempyspace(curr.row, curr.col+1,curr);
            curr.col=curr.col+1;
            playingboard.printBoard();
        }

    }
	public static void play(){
        // previously "actual_game"
        //ask the user for their instructions on what they would like to do next
        char celltype;
        int count = 1;
        int x;
        boolean won=false;
        while(playingboard.win()== false){
            for(int i = 0; i <heroes.size(); i++){
              Character curr= heroes.get(i);
                int row= curr.row;
                int col= curr.col;
            
            //check what tile the hero is at.
                celltype=playingboard.check_tile(row, col);
            //check if there is enemy ahead or beside 
                System.out.print("Enter moves for "+ curr.getName()+ " : \n");
                output_choice(curr);
                if(playingboard.win()==true){
                    won=true;
                    break;
                }
            }
            
            if(won== false){
                for(int i = 0; i <monsters.size(); i++){
                     System.out.println("here,");
                      Monster currmonster= monsters.get(i);
                      monster_move(currmonster);

            }
            playingboard.printBoard();
            }
    }}


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
    public static void set_postion(Character_monster current,int row, int col){

        playingboard.move(row,col,current);

    }

	//intitlize the starting positon of Monster 
    public static void initalizeMonsterPostion(){
        int counter=0;
        int lane=0;
        Character_monster curr;
        System.out.println(monsters.size());
        for(int i = 0; i <monsters.size(); i++){
          curr = monsters.get(i);
          curr.setpiecename("M"+ (i+1));
          curr.setrow(counter);
          curr.setcol(lane);
          counter+=3;
          set_postion(curr, curr.row, curr.col);
    	}
	}
}

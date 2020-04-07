import java.util.*;

public class PlayQuestOfLegends extends Play{
	private static  ArrayList<Character> heroes;
    private static ArrayList<Monster> monsters;
    private static NexusBoard playingboard;

    //introduction to set up game by choosing your heros. 
    public static void start(){
        num_hero = 3;
        System.out.println("Welcome to the Quest of Legend.");
        System.out.println("In the middle of chaos, you were choosen to save the world.");
        System.out.println("You will lead the team of heroes to bring peace for the kingdom");
        System.out.println("Tell me us your name, before your adventure begins");
        System.out.println();
        
        System.out.print("Please enter your name:");
        String name = scannername.next();
        currentplayer= new Player(name,'O');
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

	public static void play(){
        char celltype;
        int count = 1;
        int x;
        for(int i = 0; i <heroes.size(); i++){
            Character curr= heroes.get(i);
            int row= curr.row;
            int col= curr.col;
            
            //check what tile the hero is at.
            celltype = playingboard.check_tile(row, col);
            //check if there is enemy ahead or beside 
            System.out.print("Enter moves for "+ curr.getName()+ " : \n");
            if(celltype== 'N') {
                System.out.println("1) move");
                System.out.println("2) attack");
                System.out.println("3) cast spell");
                System.out.println("4) Use inventory");
                System.out.println("5) return to base");
                System.out.println("6) teleport to another lane");
                System.out.println("7) check hero stats ");
                System.out.println("8) check monster stats ");
                System.out.println("9) Acess Market");
                System.out.println("Choose a number from 1-9: ");
                x=isInt();
                while (valid_input2(x,9)== false){
	                System.out.println("Incorrect input! Please choose a correct number: ");
	                scannername.nextLine();
	                x= isInt();
                   }
                }
            else {
                System.out.println("1) move");
                System.out.println("2) attack");
                System.out.println("3) cast spell");
                System.out.println("4) Use inventory");
                System.out.println("5) return to base");
                System.out.println("6) teleport to another lane");
                System.out.println("7) check hero stats ");
                System.out.println("8) check monster stats ");
                System.out.println("Choose a number from 1-8: ");
                x=isInt();
                while (valid_input2(x,8)== false){
	                System.out.println("Incorrect input! Please choose a correct number: ");
	                scannername.nextLine();
	                x= isInt();
                }
            }

         //    if (x==0) {
	        //     choosen=whichenemy(current_monsters);
	        //     currentchar.attack(choosen);
	        // }
        }
    }

    //set the postion of monster and heros on the board given the row and col
    public static void set_postion(Character_monster current,int row, int col){
        String piece=current.pieceName;
        System.out.println(piece);
        playingboard.move(row,col,piece);
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

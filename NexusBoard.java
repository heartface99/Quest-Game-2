import java.util.Random;
import java.util.*;

class NexusBoard extends Board {
	//constructor which will set up board base on how many lane you would like	
	public NexusBoard(int dimensionr,int dimensionc ) {
		super(dimensionr, dimensionc);
	}
    public NexusBoard(int lane){	
        this((lane*2+lane-1),(lane*2+lane-1));	
    }

    //initialize the board
	public void intialize_board(){
	    set_regularlane(0);	    
	}


	//add the lane to to the board.
	public void set_regularlane(int curr_row){
	    int i;
	    int k;
	    int val;
	    Random rand = new Random(); 
	    for( i= curr_row; i< curr_row+2; i++){
	        for ( k= 0; k< dimensionc; k++){
	            if(k==0||k== dimensionc-1){
	                board[i][k]= new Tile(i,k,'N');
	            }    
	            else{
	                val = rand.nextInt(10);
	                if(val == 0 ){
	                    board[i][k]= new Tile(i, k,'B');
	                 }
	                if(val== 1){
	                    board[i][k]= new Tile(i,k,'C');
	                }
	                if(val==2 ){
	                	board[i][k]= new Tile(i,k,'K');
	                }
	                if (val >= 3){
	                	board[i][k]= new Tile(i,k,'P');
	                }
		        }
		    }
		} 
		if(i< dimensionr-1){
        	setnolane(i);
        }
	}

	//add the nonelane to board	
	public void setnolane(int curr_row){	
	    	
	    for (int k= 0; k< dimensionc;k++){	
	       	
	        board[curr_row][k]= new Tile(curr_row,k,'X');	
	    }	
	    if(curr_row+1 < dimensionr-1){	
	    set_regularlane(curr_row+1);	
	    }	
	}

	//check if there is a monster in range
	public ArrayList<Monster>  monster_in_range(int r,int c){
		int ahead_row= r;
		int ahead_col= c-1;
		ArrayList<Monster> monster_inrange=new ArrayList<Monster> ();
		//check if the row is in dimentison
		
		if(ahead_row>=this.dimensionr || ahead_col>=this.dimensionc|| ahead_row<0 || ahead_col<0){
	        
	    } 
	    else{
		Tile current= board[ahead_row][ahead_col];
		if(current.return_character() instanceof Monster){
			monster_inrange.add((Monster)current.return_character());
		}

		}

		int left_row= r-1;
		int left_col = c;

		if(left_row>=this.dimensionr || left_col>=this.dimensionc|| left_row<0 || left_col<0){
	        
	    } 
	    else{
		Tile current= board[left_row][left_col];
		if(current.return_character() instanceof Monster){
			monster_inrange.add((Monster)current.return_character());
		}

		}

		int right_row= r+1;
		int right_col= c;

		if(right_row>=this.dimensionr || right_col>=this.dimensionc|| right_row<0 || right_col<0){
	        
	    } 
	    else{
		Tile current= board[right_row][right_col];
		if(current.return_character() instanceof Monster){
			monster_inrange.add((Monster)current.return_character());
		}
		}
		return monster_inrange;
	}

	//check if a hero/monster has reach the nexus
	public boolean win(){
		//herowin
		for(int x=0;x<dimensionr;x++){
			Tile monsternexus =board[x][0];
			monsternexus.get_characterpiece();
			if(monsternexus.return_character() instanceof Character){
				System.out.println("Hero won!");
				return true;
			}
			
			else{
			
				Tile heronexus=board[x][dimensionc-1]; 
				if(heronexus.return_character() instanceof Monster){
					System.out.println("Monster won!");
					return true;
			
		}

		
		}
	}

		return false;

	}

	//check if there is a hero in range
	public ArrayList<Character> hero_in_range(int r,int c){
		int ahead_row= r;
		int ahead_col= c+1;
		ArrayList<Character> hero_inrange=new ArrayList<Character> ();
		//check if the row is in dimentison
		
		if(ahead_row>=this.dimensionr || ahead_col>=this.dimensionc|| ahead_row<0 || ahead_col<0){
	        
	    } 
	    else{
		Tile current= board[ahead_row][ahead_col];
		if(current.return_character() instanceof Character){
			hero_inrange.add((Character)current.return_character());
		}

		}

		int left_row= r-1;
		int left_col = c;

		if(left_row>=this.dimensionr || left_col>=this.dimensionc|| left_row<0 || left_col<0){
	        
	    } 
	    else{
		Tile current= board[left_row][left_col];
		if(current.return_character() instanceof Character){
			hero_inrange.add((Character)current.return_character());
		}

		}

		int right_row= r+1;
		int right_col= c;

		if(right_row>=this.dimensionr || right_col>=this.dimensionc|| right_row<0 || right_col<0){
	        
	    } 
	    else{
		Tile current= board[right_row][right_col];
		if(current.return_character() instanceof Character){
			hero_inrange.add((Character)current.return_character());
		}
	}

		return hero_inrange;
	}

	//move each hero/monser  to a position r c. 	
	public void move(int r,int c, Character_monster curr){
     Tile current = board[r][c];
     current.set_character_piece(curr);
	}

	public char check_tile(int r,int c){	
	    Tile curr= board[r][c];	
	    return curr.getDisplaytile();	
	}

	// print the board at current state
	public void printBoard(){
	    String String1;
	    String String2;
	    String String3;
	    for (int i = 0; i < dimensionr; i++) {
	        String1="";
	        String2="";
	        String3="";
	     for (int k = 0; k < dimensionc; k++) {
	      
	        Tile curr = board[k][i];
	        if(curr.getDisplaytile()=='N'){
	           String1+=" N - N - N ";
	           String2+=" |   "+curr.get_characterpiece()+"  | ";
	           String3+=" N - N - N ";
	        }
	        if(curr.getDisplaytile()=='B'){
	             String1+=" B - B - B ";
	             String2+=" |   "+curr.get_characterpiece()+"  | ";
	             String3+=" B - B - B ";

	        }
	        if(curr.getDisplaytile()=='C'){
	            String1+=" C - C - C ";
	            String2+=" |   "+curr.get_characterpiece()+"  | ";
	            String3+=" C - C - C " ;

	        }
	        if(curr.getDisplaytile()=='K'){

	            String1+=" K - K - K ";
	            String2+=" |   "+curr.get_characterpiece()+"  | ";
	            String3+=" K - K - K ";
	        }
	        if(curr.getDisplaytile()=='P'){

	            String1+=" P - P - P ";
	            String2+=" |   "+curr.get_characterpiece()+"  | ";
	            String3+=" P - P - P ";
	        }
	        if(curr.getDisplaytile()=='X'){
	            String1+=" I - I - I ";
	            String2+=" | X X X | ";
	            String3+=" I - I - I ";
	        }
	        
	     } System.out.println(String1);
	        System.out.println(String2);
	        System.out.println(String3);
	        System.out.println();
	     System.out.println();
	     
	    }
	}

	public Tile getTileFromTeleport(int cell) {
		// Get the tile associated with the cell picked on teleport
        ArrayList<Tile> teleportTiles= new ArrayList<Tile>();

        for (int i = 0; i < dimensionr; i++) {
            for (int k = 0; k < dimensionc; k++) {
            	Tile t = board[k][i];
            	if (t.getDisplaytile() !='X') {
            		teleportTiles.add(t);
            	}
            }
        } return teleportTiles.get(cell);
	}


	public void printTeleportOptions(Character hero) {
		// prints out the teleport options
		String String1;
	    String String2;
	    String String3;
	    String counter;
	    int c = 0;
	    for (int i = 0; i < dimensionr; i++) {
	        String1="";
	        String2="";
	        String3="";
	     for (int k = 0; k < dimensionc; k++) {
	     	Tile curr = board[k][i];
	       	if (c<10) {
	       		counter = Integer.toString(c) + " ";
	       	} else {
	       		counter = Integer.toString(c);
	       	}
	      
	        if(curr.getDisplaytile()=='N'){
	           String1+=" N - N - N ";
	           String2+=" |   "+counter+"  | ";
	           String3+=" N - N - N ";
	        }
	        if(curr.getDisplaytile()=='B'){
	             String1+=" B - B - B ";
	             String2+=" |   "+counter+"  | ";
	             String3+=" B - B - B ";

	        }
	        if(curr.getDisplaytile()=='C'){
	            String1+=" C - C - C ";
	             String2+=" |   "+counter+"  | ";
	            String3+=" C - C - C " ;

	        }
	        if(curr.getDisplaytile()=='K'){

	            String1+=" K - K - K ";
	             String2+=" |   "+counter+"  | ";
	            String3+=" K - K - K ";
	        }
	        if(curr.getDisplaytile()=='P'){

	            String1+=" P - P - P ";
	            String2+=" |   "+counter+"  | ";
	            String3+=" P - P - P ";
	        }
	        if(curr.getDisplaytile()=='X'){
	            String1+=" I - I - I ";
	            String2+=" | X X X | ";
	            String3+=" I - I - I ";
	        }
	       	if (curr.getDisplaytile() != 'X') {
	       		c++;
	       	}
	     } System.out.println(String1);
	        System.out.println(String2);
	        System.out.println(String3);
	        System.out.println();
	     	System.out.println();
	    }
	}
	//check if there is a monster right next to you, you cannot move ahead unless you defeat that monster.
	public boolean can_move_ahead(int r, int c){
		
		int left_row= r-1;
		int left_col = c;

		if(left_row>=this.dimensionr || left_col>=this.dimensionc|| left_row<0 || left_col<0){
			
	    } 
	    else{
		Tile current= board[left_row][left_col];
		if(current.return_character() instanceof Monster){
			return false;
		}

		}

		int right_row= r+1;
		int right_col= c;

		if(right_row>=this.dimensionr || right_col>=this.dimensionc|| right_row<0 || right_col<0){
	      
	    } 
	    else{
		Tile current= board[right_row][right_col];
		if(current.return_character() instanceof Monster){
			return false;
		}
		
	}
	
	return true;
}

		
	

	//checking if you are moving to an available space
	public String movingtoempyspace(int row,int col,Character_monster curr){
	   
	    if(row>=this.dimensionr || col>=this.dimensionc|| row<0 || col<0){
	        
	    
	        return "X";
	    } 
	    
	    Tile current= board[row][col];

	    if(current.get_characterpiece().equals("  ")==false|| current.getDisplaytile()=='X'){
	        
	      
	        return "X";
		}
	
	    else{
	    Tile previous = board[curr.row][curr.col];
	    previous.reset_characterpiece();
	    move(row, col, curr);
	    return "OK";
	    }
	   
	}


	//remove monster/character from board
	public void remove_piece(int row,int col){
		Tile previous = board[row][col];
		previous.reset_characterpiece();

	}

	public static void main(String[] args){	
        NexusBoard b1= new NexusBoard(3);
        b1.printBoard();
    }
}	
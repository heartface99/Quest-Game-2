import java.util.Random;

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
	        for ( k= 0; k< dimensionc;k++){
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

	//move each hero/monser  to a position r c. 	
	public void move(int r,int c, String piece){
		System.out.println(r+" "+c);
		Tile current = board[r][c];	
		current.set_character_piece(piece);
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
	public static void main(String[] args){	
        NexusBoard b1= new NexusBoard(3);	
        b1.move(0, 7, "H1");	
        b1.move(3, 7, "H2");	
        b1.move(6, 7, "H3");	
        b1.printBoard();
    }
}	
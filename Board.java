import java.util.Random;

public class Board {
    /*
    The base class for the playing board, it is made up of tiles and contains functions for accessing the board and moving
    */
    protected Tile[][] board;
    protected int dimensionr;
    protected int dimensionc;

    //constructor 
    public Board(int dimensionr,int dimensionc ){
        this.dimensionr= dimensionr;
        this.dimensionc= dimensionc;

        //make a board of dimension by dimension size
        board= new Tile[dimensionr][dimensionc];

        intialize_board();
        
    }

    public int getrow(){
        return this.dimensionr;
    }

    public int getcol(){
        return this.dimensionc;
    }

    public void intialize_board(){
        Random rand = new Random();
        int val;
        for (int i = 0; i < dimensionr; i++) {
            for (int k = 0; k < dimensionc; k++) {
                val = rand.nextInt(10);
                // System.out.println(val);
                if(val == 0 || val==1){
                    board[i][k]= new Tile(i, k,'X');
                }
                if(val== 2||val==3 ){
                    board[i][k]= new Tile(i,k,'M');
                    
                }
                if (val > 3){
                    board[i][k]= new Tile(i,k,' ');
                }
            }
        }
    }
    
    //checking if you are moving to an available space
    public String movingtoempyspace(int row,int col,char piece){
       
        if(row>=this.dimensionr || col>=this.dimensionc|| row<0 || col<0){
            return "X";
        } 
        
        Tile current= board[row][col];
        if(current.getDisplaytile()=='X'){
            // System.out.println(current.getDisplaytile()=='X');
          
            return "X";
        }
        if(current.getDisplaytile()=='M'){
            
            return "M";
            
        }
       
        setPiece(row, col, piece);
        return "OK";
    }

    public void setPiece(int r, int c,char piece){
        
        Tile current= board[r][c];
        current.setDisplaytile(piece);
    }
    
    public void SetPlayerPiece(char piece){
        //  System.out.println(this.dimensionr/2);
         int r= this.dimensionr/2;
       
        int c= this.dimensionc/2;
        setPiece(r, c,piece);
    }

    //print the board at current state
    public void printBoard(){
        //set the seperator +--

        
        String seperator ="";
        for(int n = 0; n< this.dimensionr; n++){
            seperator += "+--";
            if(n==this.dimensionr-1){
                seperator += "+";
            }
        }
       
        //print the value in the board
        System.out.println(seperator); 
        for (int i = 0; i < this.dimensionr; i++) {
           
            for (int k = 0; k < this.dimensionc; k++) {
            
                Tile current = board[i][k];
                
        
                System.out.print("|" + current.getDisplaytile()+ ' ');
                if(k == this.dimensionc-1){
                    System.out.print("|");
                    System.out.println();
                    System.out.println(seperator);
                }
            }
        }
    }

    //return the market on the playing field
    // public MainMarket getMarket(){
    //     return this.market;
    // }

    public static void main(String[] args){
        Board b1= new Board(8,8);
        b1.printBoard();
       
    }
}
//class of tile that has the property of column, row, what type of tile it is
// and the piece of hero/monster if its on tile.
public class Tile{
    private int col;
    private int row;
    private char display_tile;
    private String characterhere="  ";

    public Tile(int row, int col,char display_tile){
        this.col = col;
        this.row = row;
        this.display_tile= display_tile;

    }
    public int getCol(){
        return this.col;
    }
    public int getRow(){
        return this.row;
    }

    public void set_character_piece(String character_piece){
        this.characterhere= character_piece;
    }

    public String get_characterpiece(){
        return this.characterhere;
    }

    public void reset_characterpiece(){
        this.characterhere="  ";
    }
  

    public void setDisplaytile(char newtile){
        this.display_tile = newtile;

    }

    public char getDisplaytile(){
        return this.display_tile;
    }


}
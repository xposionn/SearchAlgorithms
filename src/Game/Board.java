package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    Tile[][] board;
    Dimension dim;
    private HashMap<Integer,Color> _colorHashMap;

    public Board(int rows,int columns){
        dim = new Dimension(columns,rows);
        board = new Tile[rows][columns];
        _colorHashMap = new HashMap<>();
    }
    public void setColors(ArrayList<Integer> numbers,Color color){
        for (Integer num: numbers) {
            this._colorHashMap.put(num,color);
        }
    }
    public void buildRow(int rowNumber,String rowData){
        String[] parsed = rowData.split(",");
        for(int i=0;i<parsed.length;++i){
            String strVal = parsed[i];
            Color tileColor = _colorHashMap.get(strVal);
            Tile tile = new Tile(strVal,tileColor);
            board[rowNumber][i] = tile;
        }
    }

    @Override
    public String toString() {
        String output="";
        output+= "Board:\n\t";
        for(int i = 0; i< board.length; ++i){
            for(int j = 0; j< board[0].length; ++j){
                output+=""+ board[i][j];
                if(j!= board.length){
                    output+=",";
                }
            }
            output+="\n\t";
        }
        return output;
    }

    public Tile getTile(int i,int j){
        return board[i][j];
    }


    public boolean equals(Board board) {
        for(int i=0;i<dim.getHeight();++i){
            for(int j=0;j<dim.getWidth();++j){
                if(!this.getTile(i,j).equals(board.getTile(i,j)))
                    return false;
            }
        }
        return true;
    }

}

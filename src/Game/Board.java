package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {
    Tile[][] _board;
    private HashMap<Integer,Color> _colorHashMap;

    public Board(int rows,int columns){
        _board = new Tile[rows][columns];
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
            _board[rowNumber][i] = tile;
        }
    }

    @Override
    public String toString() {
        String output="";
        output+= "Board:\n\t";
        for(int i=0;i<_board.length;++i){
            for(int j=0;j<_board[0].length;++j){
                output+=""+_board[i][j];
                if(j!=_board.length){
                    output+=",";
                }
            }
            output+="\n\t";
        }
        return output;
    }
}

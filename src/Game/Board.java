package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    Tile[][] _board;
    private HashMap<Integer,Color> _colorHashMap;

    Board(int rows,int columns, String board){
        _board = new Tile[rows][columns];
    }
    void setColors(ArrayList<Integer> numbers,String scolor){
        Color color = Color.getColor(scolor);
        for (Integer num: numbers) {
            this._colorHashMap.put(num,color);
        }
    }
    void buildRow(int rowNumber,String rowData){
        String[] parsed = rowData.split(",");
        for(int i=0;i<parsed.length;++i){
            Integer value = Integer.parseInt(parsed[i]);
            Color tileColor = _colorHashMap.get(value);
            Tile tile = new Tile(value,tileColor);
            _board[rowNumber][i] = tile;
        }
    }


}

package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public class Manhattan extends Heuristic {
    private BoardState eState;
    public Manhattan(BoardState eState){
        this.eState=eState;
    }


    //calculate with colors!!
    @Override
    public int getH(BoardState boardState){
        int h = 0;
        int rows = boardState.getRows();
        int columns = boardState.getColumns();
        for(int i=0;i<rows;++i){
            for(int j=0;j<columns;++j){
                int value = boardState.getBoard()[i][j];
                int cRow = value/columns;
                int cColumn = value%columns;
                if(cColumn==0){
                    cColumn = columns-1;
                    cRow--;
                }else{
                    cColumn--;
                }
                h+=(Math.abs(i-cRow)+Math.abs(j-cColumn))*boardState.getPriceOfValue(value);
            }
        }
        return h;
    }

}

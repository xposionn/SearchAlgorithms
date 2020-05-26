package Heuristics;

import Common.BoardState;

import java.awt.*;
import java.util.Comparator;

public class Manhattan implements Comparator<BoardState>,IHeuristic {
    private BoardState eState;
    public Manhattan(BoardState eState){
        this.eState=eState;
    }
/*    @Override
    public int compare(BoardState s1, BoardState s2) {
        int dEstimate1 = 0;
        int dEstimate2 = 0;
        for(int i = 0; i< eState.getBoard().length; i++) { //calculate unsolved tiles
            for(int j = 0; j< eState.getBoard()[0].length; j++){
                if (eState.getBoard()[i][j] != s1.getBoard()[i][j]&&s1.getBoard()[i][j]!=0) {
                    dEstimate1++;
                }
                if (eState.getBoard()[i][j] != s2.getBoard()[i][j]&&s2.getBoard()[i][j]!=0) {
                    dEstimate2++;
                }
            }
        }
        return Integer.compare(dEstimate1, dEstimate2);
    }*/

    @Override
    public int compare(BoardState s1, BoardState s2) {
            if(getF(s1)>getF(s2))
                return 1;
            if(getF(s1)<getF(s2)){
                return -1;
            }else{
                return 0;
            }
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

    @Override
    public int getF(BoardState boardState) {
        return boardState.getPaid()+ getH(boardState);
    }

}

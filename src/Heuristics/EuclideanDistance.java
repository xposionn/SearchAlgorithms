package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public class EuclideanDistance implements Comparator<BoardState>,IHeuristic {
    private BoardState eState;
    public EuclideanDistance(BoardState eState){
        this.eState=eState;
    }

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
                h+=Math.sqrt(Math.pow(Math.abs(i-cRow),2)+Math.pow(Math.abs(j-cColumn),2))*boardState.getPriceOfValue(value);
            }
        }
        return h;

    }

    @Override
    public int getF(BoardState boardState) {
        return boardState.getPaid()+ getH(boardState);
    }

}

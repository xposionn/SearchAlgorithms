package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public class MissPlace implements Comparator<BoardState>,IHeuristic {
    private BoardState eState;
    public MissPlace(BoardState eState){
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
                if(boardState.getBoard()[i][j] != eState.getBoard()[i][j])
                    h+=boardState.getPriceOfValue(boardState.getBoard()[i][j]);
            }
        }
        return h;

    }

    @Override
    public int getF(BoardState boardState) {
        return boardState.getPaid()+ getH(boardState);
    }

}

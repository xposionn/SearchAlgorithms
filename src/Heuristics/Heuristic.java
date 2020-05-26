package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public abstract class Heuristic<T> implements Comparator<BoardState> {

    public int compare(BoardState s1, BoardState s2) {
        if(getF(s1)>getF(s2))
            return 1;
        if(getF(s1)<getF(s2)){
            return -1;
        }else{
            return 0;
        }
    }


    public int getF(BoardState boardState) {
        return boardState.getPaid()+ getH(boardState);
    }

    public abstract int getH(BoardState boardState);

}
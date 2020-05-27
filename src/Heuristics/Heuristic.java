package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public abstract class Heuristic<T> implements Comparator<BoardState> {

    public int compare(BoardState s1, BoardState s2) {
        /*if (getF(s1) > getF(s2))
            return 1;
        if (getF(s1) < getF(s2)) {
            return -1;
        } else {
            if (s1.getDepth() != s2.getDepth()) {
                if (s1.getDepth() > s2.getDepth()) {
                    return 1;
                }
                if (s1.getDepth() < s2.getDepth()) {
                    return -1;
                }
            } else {
                if (s1.getDirection().ordinal() < s2.getDirection().ordinal())
                    return 1;
                else if (s1.getDirection().ordinal() > s2.getDirection().ordinal())
                    return -1;
            }
        }
        return 0;*/
        if(getF(s1)-getF(s2)!=0){
            return getF(s1)-getF(s2);
        }else
        if(s1.getDepth()-s2.getDepth()!=0){
            return s1.getDepth()-s2.getDepth();
        }
        return s1.getDirection().ordinal()-s2.getDirection().ordinal();
    }


    public int getF(BoardState boardState) {
        return boardState.getPaid() + getH(boardState);
    }

    public abstract int getH(BoardState boardState);

}
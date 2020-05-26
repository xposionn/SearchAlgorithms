package Heuristics;

import Common.BoardState;

public interface IHeuristic<T> {

    public int getH(BoardState boardState);
    public int getF(BoardState boardState);
    public int compare(BoardState s1, BoardState s2);

}

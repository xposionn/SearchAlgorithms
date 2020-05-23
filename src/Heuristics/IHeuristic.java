package Heuristics;

import Common.BoardState;

public interface IHeuristic<T> {

    public int getH(BoardState boardState);

}

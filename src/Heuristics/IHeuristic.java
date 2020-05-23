package Heuristics;

import Common.BoardState;

public interface IHeuristic {
    int getH(BoardState state,BoardState goal);
}

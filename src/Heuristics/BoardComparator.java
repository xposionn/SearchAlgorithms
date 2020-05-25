package Heuristics;

import Common.BoardState;

import java.util.Comparator;


public class BoardComparator implements Comparator<BoardState> {
        private IHeuristic<BoardState> heuristic;

    @Override
    public int compare(BoardState s1, BoardState s2) {
            if (f(s1) > f(s2))
                return -1;
            if (f(s1) < f(s2)) {
                return 1;
            } else {
                return 0;
            }
        }

    private int f(BoardState boardState) {
        return boardState.getPaid() + heuristic.getH(boardState);
    }
}

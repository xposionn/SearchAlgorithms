package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar implements Algorithm {

    private Problem p;
    private BoardState sState, eState; //start and goal states
    private int nodesExpanded = 1; //number of nodes explored first included
    private double totalTime;
    private PriorityQueue<BoardState> L;
    private HashSet<BoardState> H;
    private Comparator<BoardState> comparator;
    private int minF;

    public AStar(Problem p, Comparator<BoardState> comparator) {
        //L is sorted with manhatthan distance.
        L = new PriorityQueue<>(comparator);
        H = new HashSet<>();
        sState = p.getStartBoard();
        eState = p.getGoalBoard();
        this.p = p;
        this.comparator = comparator;
    }


    @Override
    public void solve() {
        int t = sState.getPaid();
        while (t != Integer.MAX_VALUE) {
            L.add(sState);
            H.add(sState);
            while (!L.isEmpty()) {
                BoardState n = L.poll();
                if (n.isOut()) {
                    H.remove(n);
                } else {
                    n.isOut(true);
                    L.add(n);
                    for (Direction direction : Direction.values()) {
                        BoardState g = new BoardState(n, direction);
                        if (g.isMoved()) {
                            nodesExpanded++;
                         //ToDO
                        }
                    }
                }
            }
        }
    }
}
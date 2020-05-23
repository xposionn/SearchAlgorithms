package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Heuristics.IHeuristic;
import Printers.Printer;

import java.util.*;
import java.util.function.Predicate;

public class AStar implements Algorithm {

    private Problem p;
    private BoardState sState, eState; //start and goal states
    private int nodesExpanded = 1; //number of nodes explored first included
    private double totalTime;
    private PriorityQueue<BoardState> L;
    private Set<BoardState> H;
    private int minF;
    private IHeuristic heuristic;

    public AStar(Problem p, IHeuristic heuristic) {
        this.heuristic = heuristic;
        L = new PriorityQueue<>((s1, s2) -> {
            if (f(s1) > f(s2))
                return -1;
            if (f(s1) < f(s2)) {
                return 1;
            } else {
                return 0;
            }
        });
        H = new HashSet<>();
        sState = p.getStartBoard();
        eState = p.getGoalBoard();
        this.p = p;
    }


    @Override
    public void solve() {
        double startTime, endTime, totalTime; //for time keeping
        startTime = System.currentTimeMillis();
        L.add(sState);
        while (!L.isEmpty()) {
            BoardState n = L.poll();
            H.add(n);
            if (n.equals(eState)) {
                endTime = System.currentTimeMillis();
                totalTime = (endTime - startTime) * 1.0 / 1000;
                Printer.exportToOutput(p, n, nodesExpanded, totalTime);
                return;
            }
            for (Direction direction : Direction.values()) {
                BoardState x = new BoardState(n, direction);
                if (x.isMoved()) {
                    if(!H.contains(x) && !L.contains(x)){
                        L.add(x);
                    //ADD ELSE
                    }
                }
            }
        }
        return;
    }

    private int f(BoardState boardState) {
        return boardState.getPaid() + heuristic.getH(boardState);
    }
}
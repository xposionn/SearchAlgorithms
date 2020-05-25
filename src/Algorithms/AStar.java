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
    private HashMap<BoardState, BoardState> H, C;
    private int minF;
    private Comparator<BoardState> comparator;

    public AStar(Problem p, IHeuristic heuristic) {
        L = new PriorityQueue<>((s1, s2) -> {
            if (heuristic.getH(s1) > heuristic.getH(s2))
                return 1;
            if (heuristic.getH(s1) < heuristic.getH(s2)) {
                return -1;
            } else {
                return 0;
            }
        });
        H = new HashMap<>();
        C = new HashMap<>();
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
            if(p.withOpen()){
                System.out.println(n);
            }
            H.remove(n);
            if (n.equals(eState)) {
                endTime = System.currentTimeMillis();
                totalTime = (endTime - startTime) * 1.0 / 1000;
                Printer.exportToOutput(p, n, nodesExpanded, totalTime);
                return;
            }
            C.put(n, n);
            for(BoardState x:n.getAllowedChildrens()){
                nodesExpanded++;
                if(!C.containsKey(x) && !L.contains(x)){
                    L.add(x);
                    H.put(x,x);
                }else if(L.contains(x) && H.get(x).getPaid()>x.getPaid()){
                    L.remove(x);
                    L.add(x);
                    H.put(x,x);
                }
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime) * 1.0 / 1000;
        Printer.exportToOutput(p, eState, false,nodesExpanded, totalTime);
    }

}
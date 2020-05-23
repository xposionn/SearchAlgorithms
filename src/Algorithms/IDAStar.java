package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Heuristics.IHeuristic;
import Printers.Printer;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class IDAStar implements Algorithm{
    private Problem p;
    private BoardState sState, eState; //start and goal states
    private int nodesExpanded = 1; //number of nodes explored first included
    private double totalTime;
    private PriorityQueue<BoardState> L;
    private Hashtable<BoardState,BoardState> H;
    private int minF;
    private int t;
    private IHeuristic heuristic;


    public IDAStar(Problem p, Comparator<BoardState> comparator, IHeuristic heuristic){
        this.p = p;
        this.sState = p.getStartBoard();
        this.eState = p.getGoalBoard();
        this.heuristic = heuristic;
        this.t = heuristic.getH(sState);
        L = new PriorityQueue<>((t1, t2) -> {
            if(f(t1)>f(t2))
                return 1;
            if(f(t1)<f(t2)){
                return -1;
            }else{
                return 0;
            }
        });
        H = new Hashtable<BoardState,BoardState>();
    }

    @Override
    public void solve() {
        double startTime, endTime, totalTime; //for time keeping
        startTime = System.currentTimeMillis();
        while(t!=Integer.MAX_VALUE){
            minF = Integer.MAX_VALUE;
            sState.isOut(false);
            L.add(sState);
            H.put(sState,sState);
            while(!L.isEmpty()){
                BoardState n = L.poll();
                if(p.withOpen()){
                    System.out.println(n);
                }
                if(n.isOut()){
                    H.remove(n);
                }else{
                    n.isOut(true);
                    L.add(n);
                    for(Direction direction:Direction.values()){
                        BoardState child = new BoardState(n,direction);
                        if(child.isMoved()){
                            nodesExpanded++;
                            int fOnChild = f(child);
                            if(fOnChild>t){
                                minF = Math.min(minF,fOnChild);
                                continue;
                            }
                            if(H.contains(child) && H.get(child).isOut()){
                                continue;
                            }if(H.contains(child) && !H.get(child).isOut()){
                                if(f(H.get(child))>f(child)){
                                    H.remove(child);
                                    L.remove(child);
                                }else{
                                    continue;
                                }
                            }
                            if(child.equals(eState)){
                                System.out.println("found solution");
                                endTime = System.currentTimeMillis();
                                totalTime = (endTime - startTime)*1.0/1000;
                                Printer.exportToOutput(p,child,true,nodesExpanded,totalTime);
                                return;
                            }
                            L.add(child);
                            H.put(child,child);
                        }
                    }
                }
                t = minF;
            }

        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime)*1.0/1000;
        Printer.exportToOutput(p,eState,false,nodesExpanded,totalTime);

    }

    private int f(BoardState boardState){
        return boardState.getPaid()+ heuristic.getH(boardState);
    }
}

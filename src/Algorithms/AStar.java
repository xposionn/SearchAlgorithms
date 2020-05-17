package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Heuristics.Heuristic;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar implements Algorithm{

    private Problem p;
    private BoardState sState, eState; //start and goal states
    private int nodesExpanded = 1; //number of nodes explored first included
    private double totalTime;
    private PriorityQueue<BoardState> L;
    private HashSet<BoardState> H;
    private Heuristic heuristic;
    private int minF;
    public AStar(Problem p,Heuristic h) {
        L = new PriorityQueue<>(h);
        H = new HashSet<>();
        sState = p.getStartBoard();
        eState = p.getGoalBoard();
        this.p = p;
        }


    @Override
    public void solve() {
        int t = sState.getPaid();
        while(t!=Integer.MAX_VALUE){
            L.add(sState);
            H.add(sState);
            while(!L.isEmpty()){
                BoardState n = L.poll();
                if(n.isOut()){
                    H.remove(n);
                }else{
                    n.isOut(true);
                    L.add(n);
                    for (Direction direction: Direction.values()) {
                        BoardState g = new BoardState(n,direction);
                        if(g.isMoved()){
                            nodesExpanded++;
                          /*
                            if(f(g)>t){
                            //Todo: right minF should be the f function on g
                                minF = Math.min(minF,minF);
                                continue;
                        }else if(H.contains(g) && g.isOut()){
                                continue;
                            }else if(H.contains(g) && !g.isOut()){
                                if(f(g))
*/                            }

                    }
                }
            }
        }
    }
}

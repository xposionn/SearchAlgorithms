package Algorithms;

import Common.BoardState;
import Common.Problem;
import Heuristics.Heuristic;
import Printers.Printer;

import java.util.*;

public class DFBnB implements Algorithm{

    private Problem p;
    private BoardState sState, eState; //start and goal states
    private int nodesExpanded = 1; //number of nodes explored first included
    private double totalTime;
    private Stack<BoardState> L;
    private Hashtable<BoardState,BoardState> H;
    private int t;
    private Heuristic<BoardState> heuristic;

    public DFBnB(Problem p,Heuristic heuristic)
    {
        this.p = p;
        this.heuristic = heuristic;
    }


    @Override
    public void solve() {
        BoardState start = p.getStartBoard();
        BoardState goal = p.getGoalBoard();
        int t = Integer.MAX_VALUE;
        ArrayList<BoardState> result = new ArrayList<>();
        L = new Stack<>();
        H = new Hashtable<>();
        L.add(start);
        H.put(start,start);
        while(!L.isEmpty()){
            BoardState n = L.pop();
            if(n.isOut()){
                H.remove(n);
            }else{
                n.isOut(true);
                L.add(n);
                ArrayList<BoardState> allChilds = n.getAllowedChildrens();
                allChilds.sort(heuristic::compare);
//                Collections.reverse(allChilds);
                ArrayList<BoardState> copyAllChilds = new ArrayList<>(allChilds);
                for(BoardState child:copyAllChilds){
                    nodesExpanded++;
                    if(heuristic.getF(child)>=t){
                        for(BoardState children:copyAllChilds){
                            if(heuristic.getF(children)>=t){
                                allChilds.remove(children);
                            }
                        }
                    }else if(H.contains(child) && !H.get(child).isOut()){
                        if(heuristic.getF(H.get(child))<=heuristic.getF(child)){
                            allChilds.remove(child);
                        }else{
                            L.remove(H.get(child));
                            H.remove(child);
                        }
                    }else if(child.equals(goal)){
                        t = heuristic.getF(child);
                        result.clear();
                        for(BoardState state:L){
                            if(state.isOut()){
                                result.add(state);
                            }
                        }
                        result.add(child);
                        for(BoardState boardState:result){
                            if(boardState.isOut())
                                System.out.println(Printer.printBoard(boardState));
                        }
                        allChilds.removeIf(boardState -> allChilds.indexOf(child)<allChilds.indexOf(boardState));
                        allChilds.remove(child);
                    }
                    Collections.reverse(allChilds);
                    L.addAll(allChilds);
                    for (BoardState boardState:allChilds) {
                        H.put(boardState,boardState);
                    };
                }
            }
        }


    }
}

package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Printers.Printer;

import java.util.HashSet;

public class DFID implements Algorithm{
    private HashSet<BoardState> H;
    private Problem p;
    private BoardState sState, eState; //start and goal states
    private int nodesExpanded = 1; //number of nodes explored first included
    private double totalTime;

    public DFID(Problem p){
        this.sState = p.getStartBoard();
        this.eState = p.getGoalBoard();
        this.p = p;
    }

    @Override
    public void solve() {
        this.totalTime = System.currentTimeMillis();
        //get Time
        for(int depth=1;depth<Integer.MAX_VALUE;++depth) {
            H = new HashSet<>();
            String result = Limited_DFS(sState,eState,depth,H);
            if(!result.equals("cut off")) {
                return;
            }
        }
    }
    public String Limited_DFS(BoardState currentState, BoardState eState, int limit, HashSet H){
        if(currentState.equals(eState)){
            totalTime = (System.currentTimeMillis() - totalTime)/1000;
            Printer.exportToOutput(p,currentState,true,nodesExpanded,totalTime);
            return "found";
        }else if(limit == 0){
            return "cut off";
        }else{
            H.add(currentState);
            boolean isCufOff = false;
            for(Direction direction:Direction.values()){
                BoardState child = new BoardState(currentState,direction);
                if(H.contains(child)){
                    continue;
                }
                nodesExpanded++;
                String result = Limited_DFS(child,eState,limit-1,H);
                if(result.equals("cut off")){
                    isCufOff=true;
                }else if(!result.equals("faild")){
                    return result;
                }
            }
            H.remove(currentState);
            if (isCufOff){
                return "cut off";
            }else{
                return "faild";
            }
        }
    }
}

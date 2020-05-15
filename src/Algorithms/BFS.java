package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Printers.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS implements Algorithm {
    private Problem p;
    private BoardState sState, eState; //start and goal states
    private ArrayList<BoardState> visitedList; //list of explored nodes
    private LinkedBlockingQueue<BoardState> frontierList; //list of nodes to explore
    private long nodesExpanded =0; //number of nodes explored


    public BFS(Problem p) {
        this.p = p;
        sState=p.getStartBoard();
        eState=p.getGoalBoard();
    }
    /** Method to solve **/
    public void solve(){
        BoardState cState = sState;
        visitedList = new ArrayList<>();
        frontierList = new LinkedBlockingQueue<BoardState>();
        long startTime, endTime, totalTime; //for time keeping
        startTime = System.nanoTime();
        while (cState != null && !cState.equals(eState)){ //stop if all nodes explored or found solution
            if (!visitedList.contains(cState)) { //expand node if not already expanded
                for (Direction d : Direction.values()) {
                    frontierList.add(new BoardState(cState, d));
                }
                visitedList.add(cState); //add to explored list
                nodesExpanded++;
            }
            cState = frontierList.poll(); //get new node from frontier
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        if (cState == null) {
            System.out.println("Could not find solution after expanding " + nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms.");
        }
        else {
            System.out.println("Solution found after expanding "+ nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms. Depth: " + cState.getDepth());
           Printer.printSolution(cState);
        }
    }

}

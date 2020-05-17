package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Printers.Printer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS implements Algorithm {
    private Problem p;
    private BoardState sState, eState; //start and goal states
    private HashSet<BoardState> closedList,openList; //list of explored nodes
    private LinkedBlockingQueue<BoardState> queue; //list of nodes to explore
    private long nodesExpanded = 1; //number of nodes explored first included


    public BFS(Problem p) {
        queue = new LinkedBlockingQueue<>();
        closedList = new HashSet<>();
        openList = new HashSet<>();
        sState = p.getStartBoard();
        eState = p.getGoalBoard();
        this.p = p;
        queue.add(sState);
        openList.add(sState);
    }

    /**
     * Method to solve
     **/
    public void solve() {
        boolean foundSolution  = false;
        BoardState cState = sState;
        long startTime, endTime, totalTime; //for time keeping
        startTime = System.nanoTime();
       while(!queue.isEmpty()){
            cState = queue.poll();
            openList.remove(cState);
            closedList.add(cState);
           for (Direction direction:Direction.values()) {
               BoardState child = new BoardState(cState,direction);
               if(!closedList.contains(child) && !openList.contains(child) && child.isMoved()){
                   nodesExpanded++;
                   if(child.equals(eState)){
                       cState = child;
                       foundSolution = true;
                       break;
                   }else{
                       queue.add(child);
                       openList.add(child);
                   }
               }

           }
           if(foundSolution)
               break;
       }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        if (cState == null) {
            System.out.println("Could not find solution after expanding " + nodesExpanded + " nodes. Searched for " + totalTime * 1E-6 + "ms.");
        } else {
            System.out.println("Solution found after expanding " + nodesExpanded + " nodes. Searched for " + totalTime * 1E-6 + "ms. Depth: " + cState.getDepth());
            Printer.printSolution(cState);
            for (BoardState board: openList) {
                System.out.println(Printer.printBoard(board));

            }
        }
    }

}

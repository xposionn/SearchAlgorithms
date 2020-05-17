package Algorithms;

import Common.BoardState;
import Common.Direction;
import Common.Problem;
import Printers.Printer;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS implements Algorithm {
    private Problem p;
    private BoardState sState, eState; //start and goal states
    private HashSet<BoardState> closedList,openList; //list of explored nodes
    private LinkedBlockingQueue<BoardState> queue; //list of nodes to explore
    private int nodesExpanded = 1; //number of nodes explored first included


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
        double startTime, endTime, totalTime; //for time keeping
        startTime = System.currentTimeMillis();
       while(!queue.isEmpty()){
            cState = queue.poll();
            if(p.withOpen()){
                System.out.println(cState);
            }
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
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime)*1.0/1000;
        Printer.exportToOutput(p,cState,foundSolution,nodesExpanded,totalTime);
        }
    }


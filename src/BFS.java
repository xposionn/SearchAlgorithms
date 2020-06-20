import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS extends Algorithm {
    private HashSet<BoardState> closedList,openList; //list of explored nodes
    private LinkedBlockingQueue<BoardState> queue; //list of nodes to explore


    public BFS(Problem p) {
        this.sState = p.getStartBoard();
        this.eState = p.getGoalBoard();
        this.p = p;
        queue = new LinkedBlockingQueue<>();
        closedList = new HashSet<>();
        openList = new HashSet<>();
        queue.add(sState);
        openList.add(sState);
    }

    /**
     * Method to solve
     **/
    public void solve() {
        BoardState n = sState;
        double startTime, endTime, totalTime; //for time keeping
        startTime = System.currentTimeMillis();
       while(!queue.isEmpty()) {
           n = queue.poll();
           if (p.withOpen()) {
               System.out.println(n);
           }
           openList.remove(n);
           closedList.add(n);
           for (Direction d : Direction.values()) {
               BoardState x = new BoardState(n,d);
               if(x.isMoved()){

               if (!closedList.contains(x) && !openList.contains(x)) {
                   nodesExpanded++;
                   if (x.equals(eState)) {
                       n = x;
                       foundSolution = true;
                       break;
                   } else {
                       queue.add(x);
                       openList.add(x);
                   }
               }

           }
       }


           if(foundSolution)
               break;
       }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime)*1.0/1000;
        finish(n,totalTime);
//        Printer.exportToOutput(p,n,foundSolution,nodesExpanded,totalTime);
        }
    }


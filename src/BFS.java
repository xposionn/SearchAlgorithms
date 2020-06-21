import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
/**
 * BFS Algorithm implementation
 */
public class BFS extends Algorithm {
    //Private variables
    private Hashtable<BoardState,BoardState> closedList,openList; //list of explored nodes
    private LinkedList<BoardState> queue; //list of nodes to explore


    public BFS(Problem p) {
        this.sState = p.getStartBoard();
        this.eState = p.getGoalBoard();
        this.p = p;
        queue = new LinkedList<>();
        closedList = new Hashtable<>();
        openList = new Hashtable<>();
        queue.add(sState);
        openList.put(sState,sState);
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
           closedList.put(n,n);
           for (Direction d : Direction.values()) {
               BoardState x = new BoardState(n,d);
               if(x.isMoved()){
                   nodesExpanded++;
                   if (!closedList.contains(x) && !openList.contains(x)) {
                   if (x.equals(eState)) {
                       n = x;
                       foundSolution = true;
                       break;
                   } else {
                       queue.add(x);
                       openList.put(x,x);
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


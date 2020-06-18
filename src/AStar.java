import java.util.*;

public class AStar extends Algorithm {

    private PriorityQueue<BoardState> L;
    private HashMap<BoardState, BoardState> H, C;
    private int minF;
    private Comparator<BoardState> comparator;
    private Heuristic<BoardState> heuristic;

    public AStar(Problem p, Heuristic heuristic) {
        this.heuristic = heuristic;
        L = new PriorityQueue<>(heuristic::compare);
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
                foundSolution = true;
                finish(n,totalTime);
                return;
            }
            C.put(n, n);
            for (Direction direction:Direction.values()) {
                BoardState child = new BoardState(n,direction);
                if (child.isMoved()) {
                    nodesExpanded++;
                    if (!C.containsKey(child) && !L.contains(child)) {
                        L.add(child);
                        H.put(child, child);
                    } else if (L.contains(child) && H.get(child) != null && H.get(child).getPaid() > child.getPaid()) {
                        L.remove(child);
                        L.add(child);
                        H.put(child, child);
                    }
                }
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime) * 1.0 / 1000;
        finish(null,totalTime);
    }


}
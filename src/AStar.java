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
            for(BoardState x:n.getAllowedChildrens()){
                nodesExpanded++;
                if(!C.containsKey(x) && !L.contains(x)){
                    L.add(x);
                    H.put(x,x);
                }else if(L.contains(x) && H.get(x)!=null && H.get(x).getPaid()>x.getPaid()){
                    L.remove(x);
                    L.add(x);
                    H.put(x,x);
                }
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime) * 1.0 / 1000;
        finish(null,totalTime);
    }


}
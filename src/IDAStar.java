import java.util.Hashtable;
import java.util.Stack;

public class IDAStar extends Algorithm {
    private Stack<BoardState> L;
    private Hashtable<BoardState, BoardState> H;
    private int minF;
    private int t;
    private Heuristic<BoardState> heuristic;


    public IDAStar(Problem p, Heuristic heuristic) {
        this.p = p;
        this.sState = p.getStartBoard();
        this.eState = p.getGoalBoard();
        this.heuristic = heuristic;
        this.t = heuristic.getH(sState);
        L = new Stack<>();
        H = new Hashtable<BoardState, BoardState>();
    }

    @Override
    public void solve() {
        double startTime, endTime, totalTime; //for time keeping
        startTime = System.currentTimeMillis();
        while (t != Integer.MAX_VALUE) {
            sState.isOut(false);
            minF = Integer.MAX_VALUE;
            L.add(sState);
            H.put(sState, sState);
            while (!L.isEmpty()) {
                BoardState n = L.pop();
                if (p.withOpen()) {
                    System.out.println(n);
                }
                if (n.isOut()) {
                    H.remove(n);
                } else {
                    n.isOut(true);
                    L.add(n);

                    //TODO: iterate each instead of taking all children.
//                    for (Direction direction:Direction.values()) {
                    for (BoardState child:n.getAllowedChildrens()) {
//                        BoardState child = new BoardState(n,direction);
                        if (child.isMoved()) {
                            nodesExpanded++;
                            int fOnChild = heuristic.getF(child);
                            if (fOnChild > t) {
                                minF = Math.min(minF, fOnChild);
                                continue;
                            }
                            if (H.contains(child) && H.get(child).isOut()) {
                                continue;
                            }
                            if (H.contains(child) && !H.get(child).isOut()) {
                                if (heuristic.getF(H.get(child)) > heuristic.getF(child)) {
                                    H.remove(child);
                                    L.remove(child);
                                } else {
                                    continue;
                                }
                            }
                            if (child.equals(eState)) {
                                endTime = System.currentTimeMillis();
                                totalTime = (endTime - startTime) * 1.0 / 1000;
                                foundSolution = true;
                                finish(child, totalTime);
                                return;
                            }
                            L.add(child);
                            H.put(child, child);
                        }
                    }
                }
            }
            t = minF;

        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime) * 1.0 / 1000;
        finish(null, totalTime);

    }
}

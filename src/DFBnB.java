import java.util.*;

public class DFBnB extends Algorithm {

    private Stack<BoardState> L;
    private Hashtable<BoardState, BoardState> H;
    private int t;
    private Heuristic<BoardState> heuristic;

    public DFBnB(Problem p, Heuristic heuristic) {
        this.p = p;
        this.heuristic = heuristic;
    }


    @Override
    public void solve() {
        double startTime, endTime, totalTime; //for time keeping
        startTime = System.currentTimeMillis();
        BoardState resultState = null;
        BoardState start = p.getStartBoard();
        BoardState goal = p.getGoalBoard();
        int t = Integer.MAX_VALUE;
        ArrayList<BoardState> result = new ArrayList<>();
        L = new Stack<>();
        H = new Hashtable<>();
        L.add(start);
        H.put(start, start);
        while (!L.isEmpty()) {
            BoardState n = L.pop();
            if(p.withOpen()){
                System.out.println(n);
            }
            if (n.isOut()) {
                H.remove(n);
            } else {
                n.isOut(true);
                L.add(n);
                ArrayList<BoardState> allChilds = n.getAllowedChildrens();
                allChilds.sort(heuristic::compare);
                ArrayList<BoardState> copyAllChilds = new ArrayList<>(allChilds);
                nodesExpanded +=copyAllChilds.size();
                for (BoardState child : copyAllChilds) {
                    if (heuristic.getF(child) >= t) {
                        //delete everyone after him. include him
                        for (BoardState children : copyAllChilds) {
                            if (heuristic.getF(children) >= t) {
                                allChilds.remove(children);
                            }
                        }
                    } else if (H.contains(child) && H.get(child).isOut()) {
                        allChilds.remove(child);
                    } else if (H.contains(child) && !H.get(child).isOut()) {
                        if (heuristic.getF(H.get(child)) <= heuristic.getF(child)) {
                            allChilds.remove(child);
                        } else {
                            L.remove(H.get(child));
                            H.remove(child);
                        }
                    } else if (child.equals(goal)) {
                        foundSolution = true;
                        t = heuristic.getF(child);
                        result.clear();
                        resultState = child;
                        for (BoardState state : L) {
                            if (state.isOut()) {
                                result.add(state);
                            }
                        }
                        allChilds.removeIf(boardState -> allChilds.indexOf(child) < allChilds.indexOf(boardState));
                        allChilds.remove(child);
                    }
                    Collections.reverse(allChilds);
                    L.addAll(allChilds);
                    for (BoardState boardState : allChilds) {
                        H.put(boardState, boardState);
                    }
                }
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime) * 1.0 / 1000;
        finish(resultState,totalTime);
    }
}

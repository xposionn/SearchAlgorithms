/**
 * This abstract class represents an Algorithm
 */
public  abstract class Algorithm {

    protected Problem p;
    protected BoardState sState, eState; //start and goal states
    protected int nodesExpanded = 1; //number of nodes explored first included
    protected boolean foundSolution = false;

    public abstract void solve();

    public void finish(BoardState finalState,double totalTime){
        Printer.exportToOutput(p,finalState,foundSolution,nodesExpanded,totalTime);
    }

}

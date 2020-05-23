package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public class Manhattan implements Comparator<BoardState> {
    private BoardState eState;
    public Manhattan(BoardState eState){
        this.eState=eState;
    }
    @Override
    public int compare(BoardState s1, BoardState s2) {
        int dEstimate1 = 0;
        int dEstimate2 = 0;
        for(int y = 0; y< eState.getBoard().length; y++) { //calculate unsolved tiles
            for(int x = 0; x< eState.getBoard()[0].length; x++){
                if (eState.getBoard()[x][y] != s1.getBoard()[x][y]&&s1.getBoard()[x][y]!=0) {
                    dEstimate1++;
                }
                if (eState.getBoard()[x][y] != s2.getBoard()[x][y]&&s2.getBoard()[x][y]!=0) {
                    dEstimate2++;
                }
            }
        }
        return Integer.compare(dEstimate1, dEstimate2);
    }
}

package Heuristics;

import Common.BoardState;

import java.util.Comparator;

public class Heuristic implements Comparator<BoardState> {
    private BoardState eState;
    private byte heuristic;

    public Heuristic(byte h, BoardState eState) {
        this.eState = eState;
        heuristic = h;
    }
    @Override
    public int compare(BoardState s1, BoardState s2) {
        long dEstimate1 = s1.getPaid(), dEstimate2 = s2.getPaid(); //get path cost so far
        if (heuristic == 0){ //for mode 0
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
        }
        else if (heuristic == 1){ //for mode 1
            for (int stY = 0; stY< eState.getBoard().length; stY++){ //calculate Manhattan distance
                for (int stX = 0; stX< eState.getBoard()[0].length; stX++){
                    int value1=s1.getBoard()[stX][stY], value2=s2.getBoard()[stX][stY];
                    for (int enY = 0; enY< eState.getBoard().length; enY++){
                        for (int enX = 0; enX< eState.getBoard()[0].length; enX++){
                            if (value1!=0&&value1== eState.getBoard()[enX][enY]){
                                dEstimate1 = dEstimate1 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                            if (value2!=0&&value2== eState.getBoard()[enX][enY]){
                                dEstimate2 = dEstimate2 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                        }
                    }
                }
            }

        }
        if (dEstimate1 > dEstimate2){
            return 1;
        }
        else if (dEstimate1 < dEstimate2){
            return -1;
        }
        else{
            return 0; //do nothing if the mode is invalid
        }
    }
}

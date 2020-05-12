package Problem;

import Algorithms.Algorithm;
import Builders.Graph;
import Game.Board;

import java.awt.*;
import java.util.ArrayList;

public class Problem {
    private static int insertedRows = 0;
    Algorithm algorithm;
    boolean printTime;
    boolean toOpen;
    ArrayList black;
    ArrayList red;
    Dimension dimension;
    Board _board;
    Board _goal;

    public ArrayList getBlack() {
        return black;
    }

    public void setBlack(ArrayList black) {
        this.black = black;
    }

    public ArrayList getRed() {
        return red;
    }

    public void setRed(ArrayList red) {
        this.red = red;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public boolean isPrintTime() {
        return printTime;
    }

    public void setPrintTime(boolean printTime) {
        this.printTime = printTime;
    }

    public boolean isToOpen() {
        return toOpen;
    }

    public void setToOpen(boolean toOpen) {
        this.toOpen = toOpen;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        this._board = new Board((int)dimension.getHeight(),(int)dimension.getWidth());
        this._goal = new Board((int)dimension.getHeight(),(int)dimension.getWidth());
        this._board.setColors(black,Color.BLACK);
        this._board.setColors(red,Color.RED);
        buildSolution(this._goal);
    }

    private void buildSolution(Board goal) {
        int rows = (int)dimension.getHeight();
        int columns = (int)dimension.getWidth();
        int totalSize = rows*columns;
        for(int i=0;i<rows;++i){
            String row = "";
            for(int j=0;j<columns;++j){
                if(i*columns+j+1==totalSize)
                    row+="_";
                else
                    row+=i*columns+j+1;
                if(j!=columns-1)
                    row+=",";
                _goal.buildRow(i,row);
            }
        }
    }

    public void insetRow(String data){
        _board.buildRow(insertedRows++,data);
    }

    @Override
    public String toString() {
        return "\nProblem{" +
                "algorithm=" + algorithm +
                ",\n printTime=" + printTime +
                ",\n toOpen=" + toOpen +
                ",\n black=" + black +
                ",\n red=" + red +
                ",\n dimension=" + dimension +
                ",\n _board=" + _board +
                "\n goal=" + _goal +
                '}';
    }

    public Graph buildGraph(){
        return null;
    }
}


package Problem;

import Algorithms.Algorithm;
import Builders.Graph;
import Game.Board;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

public class Problem {
    private static int insertedRows = 0;
    Algorithm algorithm;
    boolean printTime;
    boolean toOpen;
    ArrayList black;
    ArrayList red;
    Dimension dimension;
    Board board;

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
        this.board = new Board((int)dimension.getHeight(),(int)dimension.getWidth());
        this.board.setColors(black,Color.BLACK);
        this.board.setColors(red,Color.RED);
    }

   public void insetRow(String data){
        board.buildRow(insertedRows++,data);
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
                ",\n board=" + board +
                '}';
    }

    public Graph buildGraph(){
        return null;
    }
}


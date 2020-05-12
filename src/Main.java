import Algorithms.BFS;
import Problem.Problem;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Problem p = new Problem();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Timor\\IdeaProjects\\SearchAlgos\\Ex_1\\input.txt"));
            String line = null;
            line = bufferedReader.readLine();
            int i = 0;
            while (line != null) {
                switch (i) {
                    case 0:
                        if(line.equals("BFS"))
                            p.setAlgorithm(new BFS());
                        break;
                    case 1:
                        if(line.equals("with time"))
                            p.setPrintTime(true);
                        else
                            p.setPrintTime(false);
                        break;
                    case 2:
                        if(line.equals("with open"))
                            p.setToOpen(true);
                        else
                            p.setToOpen(false);
                        break;
                    case 3:
                        String cut = line.trim().substring(line.indexOf(":")+1);
                        String[] splitted = cut.split(",");
                        ArrayList<Integer> black = new ArrayList<>();
                        p.setBlack(black);
                        if(cut.length()==0){
                            break;
                        }
                        for (String str:splitted) {
                            black.add(Integer.parseInt(str.trim()));
                        }
                        break;
                    case 4:
                        String cut2 = line.trim().substring(line.indexOf(":")+1);
                        ArrayList<Integer> red = new ArrayList<>();
                        p.setRed(red);
                        if(cut2.length()==0){
                            break;
                        }
                        String[] splitted2 = cut2.split(",");
                        for (String str:splitted2) {
                            red.add(Integer.parseInt(str.trim()));
                        }
                        break;
                    case 5:
                        String[] dim = line.split("x");
                        int rows = Integer.parseInt(dim[0]);
                        int columns = Integer.parseInt(dim[1]);
                        Dimension dimention = new Dimension(columns,rows);
                        p.setDimension(dimention);
                        break;
                    default:
                        p.insetRow(line);
                }
                System.out.println(line);
                line = bufferedReader.readLine();
                ++i;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(p);
    }
}

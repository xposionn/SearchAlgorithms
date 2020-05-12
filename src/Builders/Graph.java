package Builders;

import java.util.LinkedList;

public class Graph {
    private int V;
    private LinkedList[] adj;

    Graph(int v){
        adj = new LinkedList[v];
        for(int i=0;i<v;++i){
            adj[i]=new LinkedList();
        }
    }

}

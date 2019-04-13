package com;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowNetwork  {
    private final int V; // number of vertices
    private List<FlowEdge>[] adj; //List of edges

    public FlowNetwork(int V) {
        this.V = V-1;
        adj = (List<FlowEdge>[]) new List[V];
        for (int v=0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    public void addEdge(FlowEdge e){
        int v = e.getFrom();
        int w = e.getTo();
        adj[v].add(e);   //add forward edge
        adj[w].add(e);  //add backward edge
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public int getV(){
        return V;
    }

    @Override
    public String toString() {
        return "FlowNetwork{" +
                "V = " + V +
                ", adj = " + Arrays.toString(adj) +
                '}';
    }

}

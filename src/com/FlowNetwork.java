package com;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowNetwork  {
    private final int V; // number of vertices
    private List<FlowEdge>[] adj;

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
                "V=" + V +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }

//    public static void main(String[] args) {
//        FlowEdge e1 = new FlowEdge(1,2,5.0);
////        FlowEdge e2 = new FlowEdge(1,3,2.0);
//        FlowEdge e3 = new FlowEdge(4,5,6.0);
//       // FlowEdge e4 = new FlowEdge(9,7,4.0);
//       // FlowEdge e5 = new FlowEdge(1,9,1.0);
//
//        FlowNetwork flow = new FlowNetwork(6);
//
//        flow.addEdge(e1);
////        flow.addEdge(e2);
//        flow.addEdge(e3);
//        //flow.addEdge(e4);
//        //flow.addEdge(e5);
//
//        System.out.println(flow.toString());
//
//    }



}

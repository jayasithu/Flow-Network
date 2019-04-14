package com;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    private final int V;
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork G, int s, int t){
        V = G.getV();


        if (s == t)
            throw new IllegalArgumentException("Source equals sink");


        value = 0.0;
        while (hasArgumentingPath(G, s, t)){
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v!=s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }

            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;

        }

    }

    private boolean hasArgumentingPath(FlowNetwork G, int s,int t){
        edgeTo = new FlowEdge[G.getV()];
        marked = new boolean[G.getV()];

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        while(!q.isEmpty() && !marked[t]){
            int v = ((LinkedList<Integer>) q).pop();

            for (FlowEdge e : G.adj(v)){
                int w = e.other(v);
                //found path from s to w in the residual network
                if(e.residualCapacityTo(w) > 0 && !marked[w]){
                    edgeTo[w] = e;            //save last edge on path w
                    marked[w] = true;
                    q.add(w);
                }
            }
        }

        return marked[t];
    }

    public double value(){
        return value;
    }

    public boolean inOut(int v){
        return marked[v];
    }

}
